/* Singleton */
const jUtils = {

    executing: function(divId) {
        $('#' + divId).html("<img src=\"/web_portal/img/spinnerMD.gif\" border=\"0\"/>").show();
    },

    showing: function(divId, html) {
        $('#' + divId).html(html !== null ? html : '').show();
    },

    hiding: function(divId, clean) {
        $('#' + divId).hide();

        if(clean) {
            $('#' + divId).html('&nbsp;');
        }
    },

    isValidEmail: function (mail) {

        if ( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail) ) {
            return true;
        }

        alert("Ingresaste un email invalido!");

        return false;
    },

    inputsAreOk : function (inputs) {
        return Array.from(inputs).reduce((acc, elem) => {
            if ($.trim(elem.value) === "") acc = acc && false;
            else acc = acc && true;
            return acc;
        }, true);
    },

    validNum: function (){
        var array = [48,49,50,51,52,53,54,55,56,57];
        return $.inArray(event.keyCode, array) !== -1;
    },

    moveLocationTo: (location) => {
        window.location.href = location;
    },

    toIdentifier: (key) => {
        const _key = key.toUpperCase();
        if(Id[_key] !== undefined) {
            return "#"+key;
        } else if(Class[_key] !== undefined) {
            return "."+key;
        }
        return key;
    },

    loadModule: (module) => {
        module.getEventHandlers().forEach(evt => {
            evt.cnfg.forEach(cnfg => {
                /* click is the default event type */
                const event_type = cnfg.event_type || "click";
                console.log("Handle Click For CTX = %o ; CNFG = %o ; EVT_TYPE = %o", evt.ctx, cnfg, event_type);
                $(jUtils.toIdentifier(evt.ctx)).delegate(jUtils.toIdentifier(cnfg.delegate), event_type, cnfg.handler);
            });
        });
    },

    dataToJson: (data) => {
        const elems = data.split("&");
        return elems.reduce((acc, elem) => {
            const values = elem.split("=");
            const key = values[0];
            const value = values[1];
            acc[key] = value;
            return acc;
        }, {});
    },

    addTitle: (title) => {
        return `<div class="card">
            <div class="card-header">
                <h2 class="title" >${title}</h2>
            </div>
        </div>`;
    },

    changeLang: (filenamme, lang, root) => {
        root = root == undefined ? '' : root;
        $.i18n.properties({
            name: filenamme,
            path: root + "/web_portal/js/properties",
            mode: 'map',
            language: lang,
            callback: function() { 
                $("#msg_welcome").text($.i18n.prop('msg_welcome')); 
                $("#msg_selLang").text($.i18n.prop('msg_selLang', lang)); 
            }
        });
    }
};