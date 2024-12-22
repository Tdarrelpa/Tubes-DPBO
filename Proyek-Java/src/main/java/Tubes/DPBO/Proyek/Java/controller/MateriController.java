package Tubes.DPBO.Proyek.Java.controller;

import java.util.concurrent.ConcurrentLinkedBlockingQueue;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import Tubes.DPBO.Proyek.Java.Entity.Materi;
import Tubes.DPBO.Proyek.Java.Repository.MateriRepository;
@Controller
public class MateriController {
    @Autowired
    private MateriRepository materiRepository;
    
    @GetMapping("/materi")
    public String index(Model model) {
        List<Materi> materis = materiRepository.findAll();
        model.addAttribute("materis", materis);
        return "materi/index";
    }
    
    @GetMapping("/materi/create")
    public String create(Model model) {
        Materi materi = new Materi();
        model.addAttribute("materi", materi);
        return "materi/create";
    }
    
    @PostMapping("/materi/store")
    public String store(@Valid @ModelAttribute Materi materi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "materi/create";
        }
        materiRepository.save(materi);
        return "redirect:/materi";
    }
    
    @GetMapping("/materi/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Materi materi = materiRepository.findById(id).orElse(null);
        if (materi == null) {
            return "redirect:/materi";
        }
        model.addAttribute("materi", materi);
        return "materi/edit";
    }
    
    @PostMapping("/materi/{id}/update")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Materi materi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "materi/edit";
        }
        Materi materiToUpdate = materiRepository.findById(id).orElse(null);
        if (materiToUpdate == null) {
            return "redirect:/materi";
        }
        materiToUpdate.setJudul(materi.getJudul());
        materiToUpdate.setDeskripsi(materi.getDeskripsi());
        materiRepository.save(materiToUpdate);
        return "redirect:/materi";
    }
    
    @GetMapping("/materi/{id}/delete")
    public String delete(@PathVariable Long id) {
        materiRepository.deleteById(id);
        return "redirect:/materi";
    }
}
