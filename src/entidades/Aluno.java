package entidades;

import java.io.Serializable;

public class Aluno extends Usuario implements Serializable {
    private String curso;
    private int semestre;

    public Aluno(String nome, String email, String telefone, String senha, String matricula, String curso, int semestre) {
        super(nome, email, telefone, senha, matricula);
        this.curso = curso;
        this.semestre = semestre;
    }

    @Override
    public boolean podeReservarPorMaisDeUmDia() {
        return false;
    }
}
