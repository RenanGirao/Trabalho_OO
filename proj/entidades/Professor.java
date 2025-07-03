package entidades;

import java.io.Serializable;

public class Professor extends Servidor implements Serializable {
    private String cursoMinistrado;
    private String cargoAcademico;

    public Professor(String nome, String email, String telefone, String senha, String matricula,
                     String cursoMinistrado, String cargoAcademico) {
        super(nome, email, telefone, senha, matricula);
        this.cursoMinistrado = cursoMinistrado;
        this.cargoAcademico = cargoAcademico;
    }
}
