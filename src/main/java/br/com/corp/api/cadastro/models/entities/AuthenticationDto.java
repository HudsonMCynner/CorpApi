package br.com.corp.api.cadastro.models.entities;

import javax.validation.constraints.NotBlank;

public class AuthenticationDto {

    @NotBlank(message = "O CPF não foi informado.")
    private String cpf;

    @NotBlank(message = "A senha não foi informada.")
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
