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

var testJavaCollections = function(caller) {
    var javaList = caller.getListOfStrings();
    var jslist = Java.from(javaList);
    LOG(JSON.stringify(javaList));
    LOG(JSON.stringify(jslist));
    LOG("^Java list appears as undefined but can be converted to javascript map");
    LOG(JSON.stringify(javaList[0]));
    LOG(JSON.stringify(jslist[0]));
    LOG("^Java list can be accessed directly");

    var javaMap = caller.getMapOfValues();
    var jsmap = javaMap;
    LOG(JSON.stringify(javaMap));
    LOG(JSON.stringify(jsmap));
    LOG("^Java maps appears as undefined");
    LOG(JSON.stringify(javaMap.testString));
    LOG(JSON.stringify(jsmap.testString));
    LOG(JSON.stringify(javaMap.testNumber));
    LOG(JSON.stringify(jsmap.testNumber));
    LOG(JSON.stringify(javaMap.testBoolean));
    LOG(JSON.stringify(jsmap.testBoolean));
    LOG("^Java maps can be accessed");

    var nashornObject = { nashorn : 'hello from nashorn', response: 'Nashorn wants a response!'};
    caller.getScriptObject(nashornObject);
    LOG(JSON.stringify(nashornObject));
    LOG("^Java enriching passed in javascript object using ScriptObjectMirror class in Java8");
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