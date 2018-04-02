jUtils = {

    executing: function(divId, mini) {
    	type = mini == undefined || mini == false ? "" : "little";
        $('#' + divId).html("<img src=" + type + "\"/web_portal/img/iloader.gif\" border=\"0\"/>").show();
    },

    showing: function(divId, html) {
        $('#' + divId).html(html !== null ? html : '').show();
    },

    hiding: function(divId, clean) {
        clean = (clean === false ? false : true);
        $('#' + divId).hide();
        if(clean) {
            $('#' + divId).html('&nbsp;');
        }
    },

    changeLang: function(filename, lang, root) {
    	root = root == undefined ? '' : root;
        jQuery.i18n.properties({
            name: filename,
            path:  root + '/web_portal/js/properties/',
            mode: 'map',
            language: lang
        });
    }
};


