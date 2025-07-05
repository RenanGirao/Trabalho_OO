package servicos;

import entidades.*;
import persistencia.Persistencia;

import java.util.List;

public class AutenticacaoServico {
    private static final List<Usuario> usuarios = Persistencia.carregarUsuarios();

    public static Usuario autenticar(String matricula, String senha) {
        for (Usuario u : usuarios) {
            if (u instanceof Aluno a && a.getMatricula().equals(matricula) && a.getSenha().equals(senha)) return a;
            if (u instanceof Servidor s && s.getMatricula().equals(matricula) && s.getSenha().equals(senha)) return s;
        }
        return null;
    }
}
