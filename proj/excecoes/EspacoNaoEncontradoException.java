package excecoes;

public class EspacoNaoEncontradoException extends Exception {
    public EspacoNaoEncontradoException() {
        super("Espaço não encontrado.");
    }
}