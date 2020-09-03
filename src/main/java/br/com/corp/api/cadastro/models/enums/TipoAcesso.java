package br.com.corp.api.cadastro.models.enums;

public enum TipoAcesso {
    USUARIO(0),
    ADM(1),
    GERAL(2);

    private int value;

    TipoAcesso(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
