jQuery(function() {
			
    jQuery( '#footer_photostream' ).gridrotator( {
    	rows : 1,
		columns : 10,
		interval : 2000,
		w1024 : {
		    rows : 1,
		    columns : 9
		},
		w768 : {
		    rows : 1,
		    columns : 8
		},
		w480 : {
		    rows : 1,
		    columns : 6
		},
		w320 : {
		    rows : 1,
		    columns : 6
		},
		w240 : {
		    rows : 1,
		    columns : 5
		},
    } );

});
