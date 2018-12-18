/* Singleton */
const jUtils = {

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
    },

    isValidEmail: function (mail) {

        if ( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail) ) {
            return true;
        }

        alert("Ingresaste un email invalido!");

        return false;
    },

    inputsAreOk : function (form) {
        return form.find("input").reduce((acc, elem) => {
            console.log(this.name, this.value);
            if ($.trim(this.value) === "") result = acc && false;
            else acc && true;
        }, true);
    },

    validNum: function (){
        var array = [48,49,50,51,52,53,54,55,56,57];
        return $.inArray(event.keyCode, array) !== -1;
    },

    moveLocationTo: (location) => {
            window.location.href = location;
        },
        ajaxCall: (action, method, data, success) => (evt) => $.ajax({
            url: action,
            type: method,
            data: data,
            dataType: "html",
            error: function(hr){
                console.log("AJAX RESULT ERROR %o", hr.responseText);
                /* TODO : CREATE GENERAL ERROR FN */
            },
            success: function(html) {
                console.log("AJAX RESULT SUCCESS %o", html);
                success(html);
            }
    }),
    ajaxCallGet: (action, data, success) => (evt) => this.ajaxCall(action, "get", data, success),
    ajaxCallPost: (action, data, success) => (evt) => this.ajaxCall(action, "post", data, success)
};