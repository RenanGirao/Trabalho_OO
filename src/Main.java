import entidades.*;
import servicos.*;
import excecoes.*;
import persistencia.Persistencia;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        ServicoReserva.carregarReservas();

        System.out.println("=== SISTEMA DE RESERVAS ===");
        while (usuarioLogado == null) {
            CadastroServico.inicializar();
            System.out.println("\n1. Login");
            System.out.println("2. Cadastrar novo usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1" -> {
                    System.out.print("Matrícula: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    usuarioLogado = AutenticacaoServico.autenticar(matricula, senha);
                    if (usuarioLogado == null) {
                        throw new LoginException();
                    }
                }
                case "2" -> CadastroServico.cadastrarUsuario(scanner);
                case "3" -> {
                    System.out.println("Encerrando o sistema.");
                    System.exit(0);
                }
                default -> throw new TipoInvalidoException();
            }
        }

        boolean executando = true;
        while (executando) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cadastro de Usuário");
            System.out.println("2. Cadastro de Espaço Físico");
            System.out.println("3. Agendar Espaço");
            System.out.println("4. Ver Reservas / Disponibilidade");
            System.out.println("5. Ver Histórico de Reservas");
            System.out.println("6. Exportar Histórico (.csv)");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> CadastroServico.cadastrarUsuario(scanner);
                case 2 -> {
                    if (usuarioLogado instanceof ServidorAdministrativo) {
                        CadastroServico.inicializar();
                        CadastroServico.cadastrarEspaco(scanner);
                    } else {
                        throw new PermissaoNegadaException();
                    }
                }
                case 3 -> ServicoReserva.agendarEspaco(scanner, usuarioLogado, CadastroServico.getEspacos());
                case 4 -> ServicoReserva.mostrarDisponibilidade();
                case 5 -> HistoricoServico.exibirHistorico(usuarioLogado);
                case 6 -> HistoricoServico.exportarHistoricoCSV(usuarioLogado);
                case 7 -> {
                    CadastroServico.salvarTodos();
                    ServicoReserva.salvarReservas();
                    executando = false;
                }
                default -> throw new TipoInvalidoException();
            }
        }

        scanner.close();
    }
}
