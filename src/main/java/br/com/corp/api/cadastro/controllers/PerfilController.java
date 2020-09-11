package br.com.corp.api.cadastro.controllers;

import br.com.corp.api.cadastro.models.entities.FileResponse;
import br.com.corp.api.cadastro.models.entities.Perfil;
import br.com.corp.api.cadastro.models.entities.Pessoa;
import br.com.corp.api.cadastro.repositories.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilRepository perfilRepository;

    private final Environment environment;

    @PostMapping("/{id}/{cfgNumero}/upload")
    public ResponseEntity salvarArquivo (@RequestParam("file") MultipartFile file, @PathVariable("id") Long id, @PathVariable("cfgNumero") Integer cfgNumero) throws IOException {
        String diretorio = environment.getProperty("file.directory");

        File folder = new File(diretorio);
        if (!folder.exists()) {
            folder.mkdir();
        }
        folder = new File(diretorio + "\\" + id);
        if (!folder.exists()) {
            folder.mkdir();
        }
        folder = new File(diretorio + "\\" + id + "\\" + file.getOriginalFilename());
        file.transferTo(folder);

        Perfil perfil = perfilRepository.getOne(id);
        perfil.setCfg(diretorio + "\\" + id + "\\" + file.getOriginalFilename());
        perfilRepository.save(perfil);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/avatar/upload")
    public ResponseEntity salvarAvatar (@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        String diretorio = environment.getProperty("file.directory");

        File folder = new File(diretorio);
        if (!folder.exists()) {
            folder.mkdir();
        }
        folder = new File(diretorio + "\\" + id);
        if (!folder.exists()) {
            folder.mkdir();
        }
        folder = new File(diretorio + "\\" + id + "\\" + file.getOriginalFilename());
        file.transferTo(folder);

        Perfil perfil = perfilRepository.getOne(id);
        perfil.setAvatar(diretorio + "\\" + id + "\\" + file.getOriginalFilename());
        perfilRepository.save(perfil);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/avatar/download")
    public ResponseEntity downloadArquivo (@RequestParam("id") Long id, @RequestParam("fileName") String fileName) throws IOException {
        String diretorio = environment.getProperty("file.directory");
        File file = new File(diretorio + "\\" + id + "\\" +fileName);

        return ResponseEntity.ok(new FileResponse(file.getName(), "cfg", file.length(), Files.readAllBytes(file.toPath()), null));
    };
}
