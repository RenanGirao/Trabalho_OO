package servicos;

import entidades.*;
import excecoes.*;
import persistencia.Persistencia;

import java.util.*;
import java.util.stream.Collectors;

public class CadastroServico {
    private static List<Usuario> usuarios;
    private static List<EspacoFisico> espacos;
    private static List<Reserva> reservas;

    public static void inicializar() {
        usuarios = Persistencia.carregarUsuarios();
        espacos = Persistencia.carregarEspacos();
        reservas = Persistencia.carregarReservas();
        ServicoReserva.setReservas(reservas);
    }

    public static void cadastrarUsuario(Scanner scanner) {
        try {
            System.out.println("\nTipo de usuário:");
            System.out.println("1. Aluno");
            System.out.println("2. Professor");
            System.out.println("3. Servidor Administrativo");
            System.out.print("Escolha uma opção: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome completo: ");
            String nome = scanner.nextLine();
            System.out.print("Email institucional: ");
            String email = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            String matricula;
            if (tipo == 1) {
                System.out.print("Curso: ");
                String curso = scanner.nextLine();
                System.out.print("Matrícula: ");
                matricula = scanner.nextLine();
                verificarDuplicidade(matricula, email);
                System.out.print("Semestre: ");
                int semestre = scanner.nextInt();
                scanner.nextLine();
                usuarios.add(new Aluno(nome, email, telefone, senha, matricula, curso, semestre));
                System.out.println("Aluno cadastrado com sucesso!");
            } else if (tipo == 2) {
                System.out.print("Matrícula institucional: ");
                matricula = scanner.nextLine();
                verificarDuplicidade(matricula, email);
                System.out.print("Curso ministrado: ");
                String curso = scanner.nextLine();
                System.out.print("Cargo acadêmico: ");
                String cargo = scanner.nextLine();
                usuarios.add(new Professor(nome, email, telefone, senha, matricula, curso, cargo));
                System.out.println("Professor cadastrado com sucesso!");
            } else if (tipo == 3) {
                System.out.print("Matrícula institucional: ");
                matricula = scanner.nextLine();
                verificarDuplicidade(matricula, email);
                System.out.print("Função/cargo: ");
                String funcao = scanner.nextLine();
                System.out.print("Departamento: ");
                String departamento = scanner.nextLine();
                usuarios.add(new ServidorAdministrativo(nome, email, telefone, senha, matricula, funcao, departamento));
                System.out.println("Servidor administrativo cadastrado com sucesso!");
            } else {
                throw new TipoInvalidoException();
            }
            Persistencia.salvarUsuarios(usuarios);
        } catch (TipoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (MatriculaDuplicadaException | EmailDuplicadoException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    public static void cadastrarEspaco(Scanner scanner) {
        try {
            System.out.print("Nome do espaço: ");
            String nome = scanner.nextLine();
            System.out.print("Capacidade: ");
            int capacidade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Localização: ");
            String localizacao = scanner.nextLine();
            List<String> tiposValidos = Arrays.asList(
                "sala de aula",
                "laboratório",
                "sala de estudo"
            );

            TipoEspaco tipoEspaco = null;
            while (tipoEspaco == null) {
                System.out.print("Tipo (sala de aula / laboratório / sala de estudo): ");
                String entradaTipo = scanner.nextLine();
                try {
                    tipoEspaco = TipoEspaco.fromString(entradaTipo);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo inválido. Informe um dos tipos válidos.");
                }
            }

            for (EspacoFisico e : espacos) {
                if (e.getNome().equalsIgnoreCase(nome) &&
                    e.getTipo() == tipoEspaco &&
                    e.getLocalizacao().equalsIgnoreCase(localizacao)) {
                    throw new EspacoDuplicadoException("Espaço já cadastrado com esse nome, tipo e localização.");
                }
            }

            System.out.print("Quantos equipamentos deseja adicionar? ");
            int qtdEquipamentos = scanner.nextInt();
            scanner.nextLine();
            List<String> equipamentos = new ArrayList<>();
            for (int i = 0; i < qtdEquipamentos; i++) {
                System.out.print("Equipamento " + (i + 1) + ": ");
                equipamentos.add(scanner.nextLine());
            }
            espacos.add(new EspacoFisico(nome, capacidade, localizacao, tipoEspaco, equipamentos));
            System.out.println("Espaço cadastrado com sucesso!");
            Persistencia.salvarEspacos(espacos);
        } catch (EspacoDuplicadoException e) {
            System.out.println("Erro no cadastro de espaço: " + e.getMessage());
        }
    }

    public static void salvarTodos() {
        Persistencia.salvarUsuarios(usuarios);
        Persistencia.salvarEspacos(espacos);
        Persistencia.salvarReservas(ServicoReserva.getReservas());
    }

    private static void verificarDuplicidade(String matricula, String email)
            throws MatriculaDuplicadaException, EmailDuplicadoException {
        for (Usuario u : usuarios) {
            if (u instanceof Aluno a && a.getMatricula().equals(matricula)) {
                throw new MatriculaDuplicadaException("Já existe um aluno com essa matrícula.");
            }
            if (u instanceof Servidor s && s.getMatricula().equals(matricula)) {
                throw new MatriculaDuplicadaException("Já existe um servidor com essa matrícula.");
            }
            if (u.getEmail().equalsIgnoreCase(email)) {
                throw new EmailDuplicadoException("Já existe um usuário com esse email.");
            }
        }
    }

    public static List<EspacoFisico> getEspacos() {
        return espacos;
    }
}
