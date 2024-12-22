//TODO: import all java library, entity classes and repository classes related to exercises
package Tubes.DPBO.Proyek.Java.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import Tubes.DPBO.Proyek.Java.Entity.LatihanSoal;
import Tubes.DPBO.Proyek.Java.Repository.LatihanSoalRepository;

@Controller
public class LatihanSoalController
{
    @Autowired
    private LatihanSoalRepository latihanSoalRepository;

    @GetMapping("/latihanSoal")
    public List<LatihanSoal> getAllLatihanSoal(){
        return latihanSoalRepository.findAll();
    }
    
    @GetMapping("/latihanSoal/{id}")
    public Optional<LatihanSoal> getLatihanSoalById(@PathVariable Long id){
        return latihanSoalRepository.findById(id);
    }
    
    @PostMapping("/latihanSoal")
    public LatihanSoal createLatihanSoal(@RequestBody LatihanSoal latihanSoal){
        return latihanSoalRepository.save(latihanSoal);
    }
    
    @PutMapping("/latihanSoal/{id}")
    public ResponseEntity<LatihanSoal> updateLatihanSoal(@PathVariable Long id, @RequestBody LatihanSoal updatedLatihanSoal){
        Optional<LatihanSoal> latihanSoalOptional = latihanSoalRepository.findById(id);
        if(latihanSoalOptional.isPresent()){
            updatedLatihanSoal.setId(id);
            return ResponseEntity.ok(latihanSoalRepository.save(updatedLatihanSoal));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/latihanSoal/{id}")
    public ResponseEntity<Void> deleteLatihanSoal(@PathVariable Long id) throws ResourceNotFoundException {
        LatihanSoal latihanSoal = latihanSoalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Latihan Soal not found for this id :: " + id));
                
        latihanSoalRepository.delete(latihanSoal);
        return ResponseEntity.noContent().build();
    }
}
