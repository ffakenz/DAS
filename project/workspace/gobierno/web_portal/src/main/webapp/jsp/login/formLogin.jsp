<fieldset>

    <form id="loginForm" action="javascript:login.validarUsuario();" method="post">

        <div class="md-form">
            <input type="text" name="username" id="username" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
        </div>

        <div class="md-form">
            <input type="password" name="password" id="password" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-default" > <fmt:message key="login_ingresar" bundle="${etq}" /> </button>
        </div>

    </form>

</fieldset>