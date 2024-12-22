package Tubes.DPBO.Proyek.Java.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Tubes.DPBO.Proyek.Java.Entity.jadwalPelajar;
import Tubes.DPBO.Proyek.Java.Repository.jadwalPelajarRepository;
import Tubes.DPBO.Proyek.Java.Entity.jadwalTutor;
import Tubes.DPBO.Proyek.Java.Repository.jadwalTutorRepository;

@Controller
public class JadwalPembelajaranController 
{
    @Autowired
    private jadwalPelajarRepository jadwalPelajarRepository;
    
    @Autowired
    private jadwalTutorRepository jadwalTutorRepository;
    
    @Autowired
    private Session session; // for JMS listener

    @Autowired
    private Map<String, Object> jmsTemplate; // for JMS sender

    @RequestMapping("/")
    public String Home() {
        return "redirect:/Dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<jadwalPelajar> jadwalPelajar = jadwalPelajarRepository.findAll();
        List<jadwalTutor> jadwalTutor = jadwalTutorRepository.findAll();
        
        model.addAttribute("jadwalPelajar", jadwalPelajar);
        model.addAttribute("jadwalTutor", jadwalTutor);
        
        return "Dashboard";
    }
    
    @GetMapping("/jadwalPelajar/{id}")
    public String viewJadwalPelajar(@PathVariable("id") int id, Model model) {
        Optional<jadwalPelajar> jadwalPelajar = jadwalPelajarRepository.findById(id);
        model.addAttribute("jadwalPelajar", jadwalPelajar.get());
        
        return "TabelJadwal";
    }
    
    @GetMapping("/jadwalTutor/{id}")
    public String viewJadwalTutor(@PathVariable("id") int id, Model model) {
        Optional<jadwalTutor> jadwalTutor = jadwalTutorRepository.findById(id);
        model.addAttribute("jadwalTutor", jadwalTutor.get());
        
        return "TabelJadwal";
    }
}
