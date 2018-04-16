    <div id="login-form">
    
            <h1><fmt:message key="login_cabecera" bundle="${etq}" /></h1>
    
            <fieldset>
    
                <form id="formulario" action="javascript:login.validarUsuario();" method="post">

                    <div class="md-form">
                      <input type="text" name="usuario" id="usuario" required placeholder="<fmt:message key="login_usuario" bundle="${etq}" />">
                    </div>

                    <div class="md-form">
                         <input type="password" name="clave" id="clave" required placeholder="<fmt:message key="login_clave" bundle="${etq}" />">
                    </div>

                                 <div class="text-center">
                                    <button type="submit" class="btn btn-default" > <fmt:message key="login_ingresar" bundle="${etq}" /> </button>

                                 </div>

                    <!-- 
    
                    <footer class="clearfix">
    
                        <p> <span class="info"> ? </span>
                        
                        <a href="#"> ¿Olvidó su contraseña? </a> </p>
    
                    </footer>
                    
                    -->
    
                </form>
    
            </fieldset>
        
        </div>
        
        <div id="resultado">
        
	        <div id="mensaje"></div>
	        
	        <div id="error"></div>
        
        </div>
