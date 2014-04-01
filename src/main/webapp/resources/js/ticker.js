var appCtx = window.location.pathname.split('/')[1];
var webSocket = new WebSocket('ws://' + window.location.host + '/' + appCtx + '/ticker');

webSocket.onmessage = function(event) {
	document.getElementById('coolNewFeature').value = event.data;
};
