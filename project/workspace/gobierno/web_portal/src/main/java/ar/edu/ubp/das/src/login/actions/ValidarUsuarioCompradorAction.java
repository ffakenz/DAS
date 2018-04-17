package ar.edu.ubp.das.src.login.actions;

public class ValidarUsuarioCompradorAction extends AValidarUsuario{
    @Override
    String getTipo() {
        return "comprador";
    }
}
