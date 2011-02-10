
function fromHex(hex) {
  	return [
  		parseInt(hex.substr(1,2),16),
  		parseInt(hex.substr(3,2),16),
  		parseInt(hex.substr(5,2),16) ];
};

function toHex(arr) {
	r = arr[0].toString(16); if (arr[0].length == 1) r = '0' + r;
	g = arr[1].toString(16); if (arr[1].length == 1) g = '0' + g;
	b = arr[2].toString(16); if (arr[2].length == 1) b = '0' + b;
	return "#" + r + g + b;
};

function computeBackground(element) {
	o = element;
	while(o) {
		var c;
		if (window.getComputedStyle) c = window.getComputedStyle(o,null).getPropertyValue("background-color");
		if (o.currentStyle) c = o.currentStyle.backgroundColor;
		if ((c != "" && c != "transparent") || o.tagName == "BODY") { break; }
		o = o.parentNode;
	}
	if (c == undefined || c == "" || c == "transparent") c = "#FFFFFF";
	var rgb = c.match(/rgb\s*\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*,\s*(\d{1,3})\s*\)/);
	if (rgb) c = this.toHex([parseInt(rgb[1]),parseInt(rgb[2]),parseInt(rgb[3])]);
	return c;
}

var Fader = function(element, from, to, fps, duration) {
	if (element.__fader__) {
		element.__fader__.frame=1;
		return;
	}
	element.__fader__ = this;

	this.element = element;
	currBg = computeBackground(element);
	if (!fps) fps = 24;
	if (!duration) duration = 1000;
	if (!from) from = "#FFFF33";
	if (from=="#") from = currBg;
	if (!to || to=="#") to = currBg;

	this.f = fromHex(from);
	this.t = fromHex(to);

	this.frames = Math.round(fps * (duration / 1000));
	this.delay = duration / this.frames;
	this.frame = 1.0;
		
	this.current = this.start;
	this.fade();
};

Fader.prototype.fade = function() {
	if (this.isFinished()) {
		this.element.__fader__ = null;
		return;
	}
	if (this.timer) clearTimeout(this.timer);
	color = toHex([this.step(0),this.step(1),this.step(2)]);
	this.element.style.backgroundColor = color;
	this.frame++;
	this.timer = setTimeout(this.fade.bind(this), this.delay);
};

Fader.prototype.step = function(i) {
	return Math.floor(this.f[i] * ((this.frames-this.frame)/this.frames) + this.t[i] * (this.frame/this.frames));
};

Fader.prototype.isFinished = function() {
	return this.frame > this.frames;
};
