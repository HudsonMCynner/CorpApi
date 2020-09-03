package br.com.corp.api.cadastro.controllers;

import br.com.corp.api.cadastro.models.entities.AuthenticationDto;
import br.com.corp.api.cadastro.models.entities.Pessoa;
import br.com.corp.api.cadastro.repositories.PessoaRepository;
import br.com.corp.api.exception.models.entities.CodeError;
import br.com.corp.api.exception.models.entities.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {


    private final PessoaRepository pessoaRepository;


    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthenticationDto authenticationDto){
        Pessoa pessoa = pessoaRepository.buscarPessoaPorCpf(authenticationDto.getCpf());

        if (pessoa == null)
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.RESGISTRO_NAO_ENCONTRADO, "CPF nçao encontrado"));

        if (!pessoa.getSenha().equals(authenticationDto.getSenha()))
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.SENHA_ICORRETA, "A Senha informada está incorreta"));

        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping
    public ResponseEntity salvar (@RequestBody Pessoa pessoa) {
        Pessoa pessoas = pessoaRepository.buscarPessoaPorCpf(pessoa.getCpf());

        if (pessoas != null)
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.CPF_JA_CADASTRADO, "CPF já Cadastrado"));

        return ResponseEntity.ok().body(pessoaRepository.save(pessoa));
    }

    @PutMapping
    public ResponseEntity alterar (@RequestBody Pessoa pessoa) {
        Pessoa pessoas = pessoaRepository.buscarPessoaPorCpf(pessoa.getCpf());

        if (pessoas != null)
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.CPF_JA_CADASTRADO, "CPF já Cadastrado"));

        return ResponseEntity.ok().body(pessoaRepository.save(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        return ResponseEntity.ok().body(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        if (!pessoaRepository.existsById(id))
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.RESGISTRO_NAO_ENCONTRADO, "Registro não foi encontrado"));

        return ResponseEntity.ok().body(pessoaRepository.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirPorId(@PathVariable Long id) {
        if (!pessoaRepository.existsById(id))
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.RESGISTRO_NAO_ENCONTRADO, "Registro não foi encontrado"));

        try {
            pessoaRepository.deleteById(id);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ResponseError(CodeError.REGISTRO_NAO_PODE_SER_EXCLUIDO, ex.getMessage()));
        }

        return ResponseEntity.ok().build();
    }
}
