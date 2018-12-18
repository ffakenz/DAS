/* Singleton */
const Utils = {
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
