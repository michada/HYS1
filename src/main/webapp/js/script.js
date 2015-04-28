$(document).ready(function() {
	$('.filters').on({
	    "shown.bs.dropdown": function() { this.closable = true; },
	    "click":             function() { this.closable = false; },
	    "hide.bs.dropdown":  function() { this.aux = this.closable; this.closable = true; return this.aux; }
	});
});