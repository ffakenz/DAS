package ar.edu.ubp.das.src.login.actions;

public class ValidarUsuarioGobiernoAction extends AValidarUsuario {
    @Override
    String getTipo() {
        return "gobierno";
    }
}
