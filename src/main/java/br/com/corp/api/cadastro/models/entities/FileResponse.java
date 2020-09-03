package br.com.corp.api.cadastro.models.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tagnos-commom
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: FileResponse.java
 * Criado por : Wender Galan Benage
 * Data da criação : 20/03/19
 * Observação :
 * ***********************************************
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {


    private String fileName;


    private String fileType;


    private Long fileSize;


    private byte [] file;

    private String publicKey;
}
