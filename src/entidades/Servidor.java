package entidades;

import java.io.Serializable;

public abstract class Servidor extends Usuario implements Serializable {
    public Servidor(String nome, String email, String telefone, String senha, String matricula) {
        super(nome, email, telefone, senha, matricula);
    }

    @Override
    public boolean podeReservarPorMaisDeUmDia() {
        return true;
    }
}
