package excecoes;

public class DataFimAntesInicioException extends ValidacaoException {
    public DataFimAntesInicioException() {
        super("A data/hora de fim não pode ser anterior à de início.");
    }
}