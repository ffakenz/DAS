<div class="container">
    <fieldset>

        <form id="loginForm" action="javascript:login.validarUsuario();" method="post">

            <div class="form-group">
                <input type="text" name="username" id="username" class="form-control form-control-lg" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
            </div>

            <div class="form-group">
                <input type="password" name="password" id="password" class="form-control form-control-lg"  required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-outline-primary btn-lg btn-block" > <fmt:message key="login_ingresar" bundle="${etq}" /> </button>
            </div>

        </form>

    </fieldset>
</div>
