const home = {

    showRegistrarConcesionaria : function () {

        window.location.href = Action.SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT;
    },

    showLogin : function () {

        window.location.href = Action.SHOW_LOGIN_ENDPOINT;
    },

    goToHome : function () {

        window.location.href = Action.HOME_ENDPOINT;
    },

    goToProfile : function() {

        window.location.href = Action.LOGIN_ENDPOINT;
    }
};
