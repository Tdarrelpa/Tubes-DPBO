//TODO: import all java library, entity classes and repository classes related to classes
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

import Tubes.DPBO.Proyek.Java.Entity.Pembelajaran;
import Tubes.DPBO.Proyek.Java.repository.PembelajaranRepository;

@Controller
public class PembelajaranController{
    @Autowired
    private PembelajaranRepository pembelajaranRepository;
    
    @GetMapping("/pembelajaran")
    public List<Pembelajaran> getAllPembelajaran(){
        return pembelajaranRepository.findAll();
    }
    
    @GetMapping("/pembelajaran/{id}")
    public Optional<Pembelajaran> getPembelajaranById(@PathVariable Long id){
        return pembelajaranRepository.findById(id);
    }
    
    @PostMapping("/pembelajaran")
    public Pembelajaran createPembelajaran(@RequestBody Pembelajaran pembelajaran){
        return pembelajaranRepository.save(pembelajaran);
    }
    
    @PutMapping("/pembelajaran/{id}")
    public Pembelajaran updatePembelajaran(@PathVariable Long id, @RequestBody Pembelajaran updatedPembelajaran){
        return pembelajaranRepository.findById(id)
               .map(pembelajaran -> {
                    pembelajaran.setJudul(updatedPembelajaran.getJudul());
                    pembelajaran.setDeskripsi(updatedPembelajaran.getDeskripsi());
                    return pembelajaranRepository.save(pembelajaran);
                })
               .orElseGet(() -> {
                    updatedPembelajaran.setId(id);
                    return pembelajaranRepository.save(updatedPembelajaran);
                });
            }
    
    @DeleteMapping("/pembelajaran/{id}")
    public void deletePembelajaran(@PathVariable Long id){
        pembelajaranRepository.deleteById(id);
    }
}
