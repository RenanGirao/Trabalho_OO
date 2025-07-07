package servicos;

import entidades.Reserva;
import entidades.Usuario;

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
        System.out.println("Ainda não temos a função exportar csv");
    }
}
