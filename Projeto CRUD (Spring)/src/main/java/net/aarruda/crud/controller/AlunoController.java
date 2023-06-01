package net.aarruda.crud.controller;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import net.aarruda.crud.configure.FileUploadUtil;
import net.aarruda.crud.entity.Aluno;
import net.aarruda.crud.service.AlunoService;



import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


@Controller
public class AlunoController {

    @Autowired
    private AlunoService service;

    @RequestMapping("/")
    public String verHomePage(Model model, @RequestParam(required = false) String search) {
        List<Aluno> listAlunos = service.search(search);
        model.addAttribute("listAlunos", listAlunos);
        model.addAttribute("count", listAlunos.size());
        return "index";
    }
    

    @RequestMapping("/novo")
    public String verNovoAluno(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        // TODO: Mostrar foto ao escolher imagem (Feito)
        return "novo_aluno";
    }

    @RequestMapping(value = "/guarda", method = RequestMethod.POST)
    public String guardaAluno(@ModelAttribute("aluno") Aluno aluno, @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        aluno.setFoto(fileName);
        service.save(aluno);
        String uploadDir = "alunos-fotos/" + aluno.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/";
    }

    @RequestMapping("/edita/{id}")
    public String verEditaAluno(@PathVariable(name = "id") int id, Model model) {
        Aluno aluno = service.get(id);
        model.addAttribute("aluno", aluno);
        // TODO: A foto atual do aluno é apagada(?) na edição (Feito)
        return "edita_aluno";
    }

    @PostMapping("/guarda/{id}")
    public String guardarAluno(@PathVariable("id") Integer id, Aluno aluno,
            @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
    
        Aluno existingAluno = service.get(id);
        String oldImagePath = "alunos-fotos/" + existingAluno.getId() + "/" + existingAluno.getFoto();
        
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    
        if (!multipartFile.isEmpty()) {
            aluno.setFoto(fileName);
            String uploadDir = "alunos-fotos/" + aluno.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            
        } else {
            aluno.setFoto(existingAluno.getFoto()); 
        }
    
        service.save(aluno);
        model.addAttribute("edited", true);
        
        
    
        if (!multipartFile.isEmpty()) {
            // Delete old image file
            Files.deleteIfExists(Paths.get(oldImagePath));
        }
    
        return "redirect:/";
    }
    

    @RequestMapping("/apaga/{id}")
    public String apagaAluno(@PathVariable(name = "id") int id) {
        service.delete(id);
    
        // TODO: Apagar pasta das fotos dos alunos
        Path uploadDir = Paths.get("alunos-fotos/" + id);
        try {
            if (Files.exists(uploadDir)) {
                try (Stream<Path> entries = Files.walk(uploadDir)) {
                    entries.sorted(Comparator.reverseOrder())
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                } catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                }
                            });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return "redirect:/";
    }

}

