package entidades;

public abstract class Servidor extends Usuario {
    public Servidor(String nome, String email, String telefone, String senha, String matricula) {
        super(nome, email, telefone, senha, matricula);
    }

    @Override
    public boolean podeReservarPorMaisDeUmDia() {
        return true;
    }
}

