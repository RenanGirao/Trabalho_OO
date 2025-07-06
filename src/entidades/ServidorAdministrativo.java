package entidades;

public class ServidorAdministrativo extends Servidor {
    private String funcao;
    private String departamento;

    public ServidorAdministrativo(String nome, String email, String telefone, String senha, String matricula,
                                   String funcao, String departamento) {
        super(nome, email, telefone, senha, matricula);
        this.funcao = funcao;
        this.departamento = departamento;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getDepartamento() {
        return departamento;
    }
}
