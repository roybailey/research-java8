var LOG = function(message) {
    print("NASHORN: " + message);
};

var helloNashornFromJava = function(name) {
    LOG('Hi there from Javascript, ' + name);
    return "greetings from nashorn!";
};

var javaTypesInNashorn = function (object) {
    LOG("JS Class Definition: " + Object.prototype.toString.call(object));
    LOG("JSON: " + JSON.stringify(object));
};

var thereAndBackAgain = function (object) {
    LOG("JS Class Definition: " + Object.prototype.toString.call(object));
    var javadata = object.thereAndBackAgainJavaObject();
    LOG("JS called Java object instance for return data: " + javadata.StringValue);
    var jsdata = {};
    object.thereAndBackAgainScriptObject(jsdata);
    LOG("JS called Java object instance for script data: " + JSON.stringify(jsdata));
};

var testGlobalDependencyInjection = function() {
    LOG("ioc=" +ioc);
    LOG("ioc.globalNumber=" +ioc.globalNumber);
    ioc.service.run();
};

var MyJavaClass = Java.type('me.roybailey.research.nashorn.NashornTest');

var result = MyJavaClass.nashornWillCallThis('Nashorn test script evaluated');
LOG(result);

MyJavaClass.nashornTypesInJava(123);
// class java.lang.Integer

MyJavaClass.nashornTypesInJava(49.99);
// class java.lang.Double

MyJavaClass.nashornTypesInJava(true);
// class java.lang.Boolean

MyJavaClass.nashornTypesInJava("hi there")
// class java.lang.String

MyJavaClass.nashornTypesInJava(new Number(23));
// class jdk.nashorn.internal.objects.NativeNumber

MyJavaClass.nashornTypesInJava(new Date());
// class jdk.nashorn.internal.objects.NativeDate

MyJavaClass.nashornTypesInJava(new RegExp());
// class jdk.nashorn.internal.objects.NativeRegExp

MyJavaClass.nashornTypesInJava({foo: 'bar'});
// class jdk.nashorn.internal.scripts.JO4