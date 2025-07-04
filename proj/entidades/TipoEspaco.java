package entidades;

public enum TipoEspaco {
    SALA_DE_AULA("sala de aula"),
    LABORATORIO("laboratório"),
    SALA_DE_ESTUDO("sala de estudo");

    private final String descricao;

    TipoEspaco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEspaco fromString(String texto) throws IllegalArgumentException {
        for (TipoEspaco tipo : TipoEspaco.values()) {
            if (tipo.descricao.equalsIgnoreCase(texto.trim())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + texto);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
