package entidades;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nome;
    protected String email;
    protected String telefone;
    protected String senha;
    protected String matricula;

    public Usuario(String nome, String email, String telefone, String senha, String matricula) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.matricula = matricula;
    }

    public abstract boolean podeReservarPorMaisDeUmDia();
    public String getNome() { return nome; }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getMatricula() {
        return matricula;
    }
}
