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

import Tubes.DPBO.Proyek.Java.Diskusi;

@Controller
public class DiskusiController 
{
    @Autowired
    private Diskusi diskusi;
    
    @GetMapping("/diskusi")
    public String viewDiskusi(Model model)
    {
        List<Map<String, Object>> diskusis = diskusi.getDiskusi();
        model.addAttribute("diskusis", diskusis);
        return "redirect:/postDiskusi";
    }
    
    @GetMapping("/diskusi/{id}")
    public String viewDetailDiskusi(@PathVariable("id") int id, Model model)
    {
        Optional<Map<String, Object>> diskusi = diskusi.getDiskusiById(id);
        model.addAttribute("diskusi", diskusi.orElse(null));
        return "detailDiskusi";
    }

    @GetMapping("/diskusi/add")
    public String addDiskusi()
    {
        return "addDiskusi";
    }
    
    @PostMapping("/diskusi/add")
    public String addDiskusiSubmit(Diskusi diskusi)
    {
        diskusi.save(diskusi);
        return "redirect:/postDiskusi";
    }
    
    @GetMapping("/diskusi/{id}/edit")
    public String editDiskusi(@PathVariable("id") int id, Model model)
    {
        Optional<Diskusi> diskusi = diskusi.findById(id);
        model.addAttribute("diskusi", diskusi.orElse(null));
        return "editDiskusi";
    }
    
    @PostMapping("/diskusi/{id}/edit")
    public String editDiskusiSubmit(@PathVariable("id") int id, Diskusi diskusi)
    {
        diskusi.setId(id);
        diskusi.save(diskusi);
        return "redirect:/postDiskusi";
    }
    
    @GetMapping("/diskusi/{id}/delete")
    public String deleteDiskusi(@PathVariable("id") int id)
    {
        diskusi.deleteById(id);
        return "redirect:/postDiskusi";
    }
}
