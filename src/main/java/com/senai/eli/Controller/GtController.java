package com.senai.eli.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.senai.eli.Model.GrupoTrabalho;
import com.senai.eli.Repository.GtRepository;



@Controller
public class GtController {

    @Autowired
    private GtRepository er;
    
    @GetMapping("/grupo/criar")
    public String criar(){
        return "grupo/criar";
    }

    @PostMapping("/grupo/criar")
    public String criar(GrupoTrabalho ev){
        er.save(ev);
        return "redirect:/grupo";
    }

    @GetMapping("/grupo")
    public String listar(Model view){
        List<GrupoTrabalho> listaGrupoTrabalhos = er.findAll();

        view.addAttribute("listaGruposNoFront", listaGrupoTrabalhos);

        return "grupo/listar";
    }

    @GetMapping("/grupo/alterar/{id}")
    public String alterar(@PathVariable Long id, Model model) {
        GrupoTrabalho grupoTrabalho;
        grupoTrabalho = er.findById(id).orElseThrow();

        model.addAttribute("grupo", grupoTrabalho);
        return "grupo/alterar";
    }    

    @PostMapping("/grupo/alterar/{id}")
    public String alterar(@PathVariable Long id, GrupoTrabalho ev) {
        ev.setId(id);

        er.save(ev);
        
        return "redirect:/grupo";
    }
    

}
