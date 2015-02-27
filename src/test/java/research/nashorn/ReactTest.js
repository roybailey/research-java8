var MyComponent = React.createClass({
	render: function() {
		return React.DOM.div(null, this.props.text);
	}
});

var renderFromServer = function (text) {
    //var data = Java.from(text);
    //console.log(text);
    return React.renderToString(
        React.createElement(MyComponent, {text: text})
    );
};
