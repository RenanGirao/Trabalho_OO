package excecoes;

public class TipoInvalidoException extends ValidacaoException {
    public TipoInvalidoException() {
        super("Tipo inválido.");
    }
}