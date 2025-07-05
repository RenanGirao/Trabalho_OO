package entidades;

import java.io.Serializable;
import java.util.List;

public class EspacoFisico implements Serializable {
    private String nome;
    private int capacidade;
    private String localizacao;
    private TipoEspaco tipo;
    private List<String> equipamentos;

    public EspacoFisico(String nome, int capacidade, String localizacao, TipoEspaco tipo, List<String> equipamentos) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.tipo = tipo;
        this.equipamentos = equipamentos;
    }

    public String getNome() {
        return nome;
    }

    public TipoEspaco getTipo() {
        return tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EspacoFisico that = (EspacoFisico) o;

        return this.getNome().equalsIgnoreCase(that.getNome()) &&
            this.getTipo().name().equalsIgnoreCase(that.getTipo().name()) &&
            this.getLocalizacao().equalsIgnoreCase(that.getLocalizacao());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
            getNome().toLowerCase(),
            getTipo().name().toLowerCase(),
            getLocalizacao().toLowerCase()
        );
    }
}
