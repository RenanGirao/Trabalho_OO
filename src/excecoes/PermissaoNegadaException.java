package excecoes;

public class PermissaoNegadaException extends Exception {
    public PermissaoNegadaException() {
        super("Apenas servidores administrativos podem cadastrar espaços.");
    }
}
