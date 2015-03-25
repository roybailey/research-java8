var fun1 = function(name) {
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};

var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
    print("JSON: " + JSON.stringify(object));
};

var thereAndBackAgain = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
    var javadata = object.thereAndBackAgain();
    print("JS called Java object instance for return data: " + javadata.StringValue);
    var jsdata = {};
    object.thereAndBackAgainScriptObject(jsdata);
    print("JS called Java object instance for script data: " + JSON.stringify(jsdata));
};


var MyJavaClass = Java.type('me.roybailey.research.nashorn.NashornTest');

var result = MyJavaClass.jsWillCallThis('John Doe');
print(result);

MyJavaClass.jsTypesInJava(123);
// class java.lang.Integer

MyJavaClass.jsTypesInJava(49.99);
// class java.lang.Double

MyJavaClass.jsTypesInJava(true);
// class java.lang.Boolean

MyJavaClass.jsTypesInJava("hi there")
// class java.lang.String

MyJavaClass.jsTypesInJava(new Number(23));
// class jdk.nashorn.internal.objects.NativeNumber

MyJavaClass.jsTypesInJava(new Date());
// class jdk.nashorn.internal.objects.NativeDate

MyJavaClass.jsTypesInJava(new RegExp());
// class jdk.nashorn.internal.objects.NativeRegExp

MyJavaClass.jsTypesInJava({foo: 'bar'});
// class jdk.nashorn.internal.scripts.JO4