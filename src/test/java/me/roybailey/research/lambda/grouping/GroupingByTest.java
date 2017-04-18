package me.roybailey.research.lambda.grouping;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Sample from http://www.novixys.com/blog/java-streams-groupingby-examples/
 */
public class GroupingByTest {


    List<Player> players = new ArrayList<>();

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setupData() {

        players.add(new Player(1980, "Arsenal", "Division 1", "Alan Armpit", 50000));
        players.add(new Player(1980, "Birmingham", "Division 1", "Bobby Brownback", 55000));
        players.add(new Player(1990, "Coventry", "Division 2", "Colin Collarbone", 25000));
        players.add(new Player(1990, "Everton", "Division 1", "Edward Earmuffs", 30000));
        players.add(new Player(1990, "Ipswich", "Division 2", "Ivan Inkface", 30000));
        players.add(new Player(1990, "Ipswich", "Division 2", "Igor Inkfoot", 25000));

    }

    @Test
    public void groupingBySingleField()
    {
        System.out.println(testName.getMethodName());

        Map<Integer,List<Player>> grouped = players.stream()
                .collect(Collectors.groupingBy(x->x.getYear()));
        grouped
                .entrySet()
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void groupingByMultipleFields()
    {
        System.out.println(testName.getMethodName());

        Map<List<String>,List<Player>> grouped = players.stream()
                .collect(Collectors.groupingBy(x ->
                        new ArrayList<>(Arrays.asList(Integer.toString(x.getYear()), x.getTeam()))
                ));
        grouped
                .entrySet()
                .stream()
                .forEach(x -> {
                    System.out.println(x.getKey());
                    x.getValue().stream()
                            .forEach(p -> System.out.printf(" ( %2s %-10s %-10d )%n", p.getLeague(), p.getPlayer(), p.getSalary()));
                });
    }

    @Test
    public void groupingByIntoSet() {
        System.out.println(testName.getMethodName());

        Map<List<String>,Set<Player>> grouped = players.stream()
                .collect(Collectors.groupingBy(x -> new ArrayList<>(Arrays.asList(x.getTeam(), x.getLeague())), Collectors.toSet()));

        grouped.forEach((k,v) -> System.out.println(k+" : "+v));
    }

    @Test
    public void groupingAndSumming() {
        System.out.println(testName.getMethodName());

        Map<List<String>,Integer> grouped = players.stream()
                .collect(Collectors.groupingBy(x -> new ArrayList<>(Arrays.asList(x.getTeam(), x.getLeague())), Collectors.summingInt(Player::getSalary)));

        grouped.forEach((k,v) -> System.out.println(k+" : "+v));
    }

    public static class Player
    {
        private int year;
        private String team;
        private String league;
        private String player;
        private int salary;

        public Player(int year, String team, String league, String player, int salary) {
            this.year = year;
            this.team = team;
            this.league = league;
            this.player = player;
            this.salary = salary;
        }

        public int getYear() {
            return year;
        }

        public String getTeam() {
            return team;
        }

        public String getLeague() {
            return league;
        }

        public String getPlayer() {
            return player;
        }

        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "year=" + year +
                    ", team='" + team + '\'' +
                    ", league='" + league + '\'' +
                    ", player='" + player + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
