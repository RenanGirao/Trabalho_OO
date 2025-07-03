package servicos;

import entidades.Reserva;
import entidades.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HistoricoServico {

    public static void exibirHistorico(Usuario usuario) {
        List<Reserva> reservas = ServicoReserva.getReservasPorUsuario(usuario);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }

        System.out.println("=== HISTÓRICO DE RESERVAS ===");
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    public static void exportarHistoricoCSV(Usuario usuario) {
        List<Reserva> reservas = ServicoReserva.getReservasPorUsuario(usuario);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para exportar.");
            return;
        }

        String nomeArquivo = "historico_" + usuario.getEmail().replaceAll("[^a-zA-Z0-9]", "_") + ".csv";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("Espaco;DataInicio;DataFim\n");
            for (Reserva r : reservas) {
                writer.write(
                    r.getEspaco().getNome() + ";" +
                    r.getInicio() + ";" +
                    r.getFim() + "\n"
                );
            }
            System.out.println("Histórico exportado para: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao exportar: " + e.getMessage());
        }
    }
}
