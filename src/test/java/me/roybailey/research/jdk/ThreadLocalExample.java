package me.roybailey.research.jdk;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Simulation to show use of ThreadLocal to manage transaction identifiers in thread names
 * and periodic refreshing of thread local configuration cache.
 */
public class ThreadLocalExample {

    public static BlockingQueue<String> queueTransactions = new ArrayBlockingQueue<>(100);

    public static class SomethingToRun implements Runnable {

        // keeps a backup copy of the original thread name
        private ThreadLocal<String> tlThreadName = new ThreadLocal<>();
        // keeps a local timestamp when the config was last refreshed for this thread
        private ThreadLocal<Long> tlRefresh = new ThreadLocal<>();
        // keeps a local config version (simulating periodically refreshing thread configuration)
        private ThreadLocal<Long> sharedConfigVersion = new ThreadLocal<>();

        @Override
        public void run() {

            String transaction = null;
            do {
                try {
                    // load the latest version of the config if we've never loaded it or our cached config is stale...
                    if (tlRefresh.get() == null || (System.currentTimeMillis() - tlRefresh.get()) > TimeUnit.SECONDS.toMillis(5)) {
                        // simulate checking/loading the latest config (Optional comes in handy here)...
                        sharedConfigVersion.set(Optional.ofNullable(sharedConfigVersion.get()).orElse(0L) + 1L);
                        tlRefresh.set(System.currentTimeMillis());
                        System.out.println(Thread.currentThread().getName() + " config refreshed @" + tlRefresh.get());
                    }

                    // simulate our task context, such as processing a messages...
                    transaction = queueTransactions.poll(1, TimeUnit.SECONDS);

                    if (transaction != null) {
                        try {
                            // save the default thread name in the thread local variable
                            // by doing it here every time we ensure even thread naming changes outside this
                            // will be properly reflected
                            tlThreadName.set(Thread.currentThread().getName());
                            // append some transaction identifier to the thread name for the duration of the transaction processing
                            Thread.currentThread().setName(Thread.currentThread().getName() + "-" + transaction);
                            // logging of thread name would now include this transaction identifier
                            System.out.println(Thread.currentThread().getName() + " with config v" + sharedConfigVersion.get());

                            try {
                                // simulate the worker processing the transaction...
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                            }
                        } finally {
                            // restore the thread-local value back to the default using thread local
                            Thread.currentThread().setName(tlThreadName.get());
                            tlThreadName.set("");
                        }
                    }

                } catch (Exception err) {
                    err.printStackTrace();
                }
            } while (transaction != null);
            // logging of thread name would now show the default value without any transaction identifier
            System.out.println(Thread.currentThread().getName() + " with config v" + sharedConfigVersion.get());
        }
    }


    public static void main(String[] args) {
        SomethingToRun sharedRunnableInstance = new SomethingToRun();

        "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().forEach(it -> {
            try {
                char letter = (char) it;
                queueTransactions.put(String.valueOf(letter) + letter + letter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        // start threads at slightly different times...
        thread1.start();
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
        }
        thread2.start();

        // threads will need to finish for the program to exit
    }

}