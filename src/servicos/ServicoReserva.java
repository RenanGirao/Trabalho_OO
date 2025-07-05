package servicos;

import entidades.*;
import excecoes.*;
import persistencia.Persistencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicoReserva {
    private static List<Reserva> reservas = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void carregarReservas() {
        List<Reserva> carregadas = reservas = Persistencia.carregarReservas();
        if (carregadas != null) {
            reservas = carregadas;
        }
    }

    public static void salvarReservas() {
        Persistencia.salvarReservas(reservas);
    }

    public static void mostrarDisponibilidade() {
        carregarReservas();

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }

        System.out.println("===== TODAS AS RESERVAS =====");
        for (Reserva r : reservas) {
            System.out.println("Espaço: " + r.getEspaco().getNome());
            System.out.println("Reservado por: " + r.getUsuario().getMatricula());
            System.out.println("Início: " + r.getInicio().format(formatter));
            System.out.println("Fim:    " + r.getFim().format(formatter));
            System.out.println("---------------------------");
        }
    }

    public static void agendarEspaco(Scanner scanner, Usuario usuarioLogado, List<EspacoFisico> espacos) {
        if (espacos.isEmpty()) {
            System.out.println("Não há espaços cadastrados.");
            return;
        }

        System.out.print("Nome do espaço: ");
        String nomeEspaco = scanner.nextLine();

        EspacoFisico espaco = espacos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEspaco))
                .findFirst()
                .orElse(null);

        try {
            if (espaco == null) {
                throw new EspacoNaoEncontradoException();
            }
            System.out.print("Data e hora de início (Ano-Mes-Dia hora:minuto |||| exemplo: 2025-07-23 12:30 ):");
            LocalDateTime inicio = lerDataHora(scanner);

            System.out.print("Data e hora de início (Ano-Mes-Dia hora:minuto |||| exemplo: 2025-07-23 12:30 ):");
            LocalDateTime fim = lerDataHora(scanner);

            if (fim.isBefore(inicio)) {
                throw new DataFimAntesInicioException();
            }

            if (!usuarioLogado.podeReservarPorMaisDeUmDia()) {
                if (!inicio.toLocalDate().equals(fim.toLocalDate())) {
                    throw new DiasExcedidosException("Alunos só podem reservar por um único dia.");
                }
            }

            ServicoReserva.carregarReservas();

            for (Reserva r : reservas) {
                boolean x = r.getEspaco().equals(espaco);
                if (r.getEspaco().equals(espaco)) {
                    boolean conflito = r.getInicio().isBefore(fim) && inicio.isBefore(r.getFim());
                    if (conflito) {
                        throw new HorarioIndisponivelException("Espaço já reservado neste período.");
                    }
                }
            }

            Reserva novaReserva = new Reserva(usuarioLogado, espaco, inicio, fim);
            reservas.add(novaReserva);
            salvarReservas();

            System.out.println("Reserva realizada com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data/hora inválido. Use 'Ano-Mes-Dia hora:minuto'.");
        } catch (EspacoNaoEncontradoException | DiasExcedidosException | HorarioIndisponivelException
                | DataFimAntesInicioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static LocalDateTime lerDataHora(Scanner scanner) throws DateTimeParseException {
        String input = scanner.nextLine();
        return LocalDateTime.parse(input, formatter);
    }

    public static List<Reserva> getReservasPorUsuario(Usuario usuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getUsuario().getMatricula().equals(usuario.getMatricula())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public static void setReservas(List<Reserva> novasReservas) {
        reservas = (novasReservas == null) ? new ArrayList<>() : novasReservas;
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }

}
