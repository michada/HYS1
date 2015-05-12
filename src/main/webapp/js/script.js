function setSameHeight() {
	if ($(window).width() > 415) {
		var counter = 0;
		var maxHeight = 0;
		var cards = [];
		$('.thumbnail').each(function() {
			cards.push($(this));
			$(this).css("height", "");
			var currentHeight = $(this).height();
			if (maxHeight < currentHeight) {
				maxHeight = currentHeight;
			}
			if (counter == 2) {
				cards[0].css("height", maxHeight);
				cards[1].css("height", maxHeight);
				cards[2].css("height", maxHeight);
				counter = 0;
				cards = [];
			} else {
				counter++;
			}
		});
	} else {
		$('.thumbnail').each(function() {
			$(this).css("height", "");
		});
	}
}

$(document).ready(function() {
	$('.filters').on({
		"shown.bs.dropdown" : function() {
			this.closable = true;
		},
		"click" : function() {
			this.closable = false;
		},
		"hide.bs.dropdown" : function() {
			this.aux = this.closable;
			this.closable = true;
			return this.aux;
		}
	});
});

$(window).resize(function() {
	setSameHeight();
});