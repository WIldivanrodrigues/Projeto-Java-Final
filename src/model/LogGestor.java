package model;

public class LogGestor {
    private final String emailGestor = "gestao@belezapura.com";
    private final String senhaGestor = "admin@123";

    public boolean validarGestor (String email, String senha){
        return this.emailGestor.equals(email) && this.senhaGestor.equals(senha);

    }
}
