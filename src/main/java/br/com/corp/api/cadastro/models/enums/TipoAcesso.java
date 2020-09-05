package br.com.corp.api.cadastro.models.enums;

public enum TipoAcesso {
    USUARIO(0),
    ADM(1);

    private int value;

    TipoAcesso(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
