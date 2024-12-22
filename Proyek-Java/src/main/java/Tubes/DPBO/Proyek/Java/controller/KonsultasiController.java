//TODO: import all java library, entity classes and repository classes related to student and tutor consultation
package Tubes.DPBO.Proyek.Java.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Tubes.DPBO.Proyek.Java.Entity.JadwalPelajar;
@Controller
public class KonsultasiController {
    @Autowired
    private KonsultasiRepository konsultasiRepository;

    @GetMapping("/konsultasi")
    public String viewKonsultasi(Model model) {
        List<JadwalPelajar> konsultasi = konsultasiRepository.findAll();
        model.addAttribute("konsultasi", konsultasi);
        return "konsultasi";
    }
    
    @GetMapping("/konsultasi/create")
    public String create(Model model) {
        JadwalPelajar konsultasi = new JadwalPelajar();
        model.addAttribute("konsultasi", konsultasi);
        return "konsultasi_form";
    }
    
    @PostMapping("/konsultasi/store")
    public String store(@ModelAttribute JadwalPelajar konsultasi) {
        konsultasiRepository.save(konsultasi);
        return "redirect:/konsultasi";
    }
    
    @GetMapping("/konsultasi/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<JadwalPelajar> konsultasi = konsultasiRepository.findById(id);
        if (konsultasi.isPresent()) {
            model.addAttribute("konsultasi", konsultasi.get());
            return "konsultasi_form";
        } else {
            return "redirect:/konsultasi";
        }
    }
    
    @PostMapping("/konsultasi/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute JadwalPelajar konsultasi) {
        konsultasiRepository.save(konsultasi);
        return "redirect:/konsultasi";
    }
    
    @GetMapping("/konsultasi/{id}/delete")
    public String delete(@PathVariable Long id) {
        konsultasiRepository.deleteById(id);
        return "redirect:/konsultasi";
    }
}
