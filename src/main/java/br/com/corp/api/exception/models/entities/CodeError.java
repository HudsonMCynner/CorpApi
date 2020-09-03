package br.com.corp.api.exception.models.entities;

public enum CodeError {

    CPF_JA_CADASTRADO(1000),
    RESGISTRO_NAO_ENCONTRADO(1001),
    SENHA_ICORRETA(1002),
    REGISTRO_NAO_PODE_SER_EXCLUIDO(1003);

    private int code;

    CodeError(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
