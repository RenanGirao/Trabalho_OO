package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reserva implements Serializable {
    private Usuario usuario;
    private EspacoFisico espaco;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Reserva(Usuario usuario, EspacoFisico espaco, LocalDateTime inicio, LocalDateTime fim) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public EspacoFisico getEspaco() {
        return espaco;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public boolean conflitaCom(Reserva outra) {
    if (this.espaco.getNome().equals(outra.espaco.getNome())) {
        if (!(this.fim.isBefore(outra.inicio) || this.inicio.isAfter(outra.fim))) {
            return true; 
        }
    }
    return false; 
}

    @Override
    public String toString() {
        return "Reserva [Espaço: " + espaco.getNome() +
               ", Início: " + inicio +
               ", Fim: " + fim +
               ", Usuário: " + usuario.getNome() + "]";
    }
}
