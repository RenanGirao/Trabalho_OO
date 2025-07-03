package excecoes;

public class LoginException extends Exception {
    public LoginException() {
        super("Usuário não encontrado ou senha incorreta. Tente novamente.");
    }
}