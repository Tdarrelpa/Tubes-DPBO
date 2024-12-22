//TODO: import all java library, entity classes and repository classes related to transaction between student and tutor
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

import Tubes.DPBO.Proyek.Java.Entity.UserPelajar;
import Tubes.DPBO.Proyek.Java.Repository.UserPelajarRepository;
import Tubes.DPBO.Proyek.Java.Entity.UserTutor;
import Tubes.DPBO.Proyek.Java.Repository.UserTutorRepository;
import Tubes.DPBO.Proyek.Java.Entity.Pembayaran;
import Tubes.DPBO.Proyek.Java.Repository.PembayaranRepository;
import Tubes.DPBO.Proyek.Java.Entity.Refund;
import Tubes.DPBO.Proyek.Java.Repository.RefundRepository;


@Controller
public class TransaksiController{
    @Autowired
    private UserPelajarRepository userPelajarRepository;
    @Autowired
    private UserTutorRepository userTutorRepository;
    @Autowired
    private PembayaranRepository pembayaranRepository;
    @Autowired
    private RefundRepository refundRepository;
    
    @PostMapping("/bayar")
    public String bayar(@RequestParam("idPelajar") String idPelajar, @RequestParam("uang") int uang){
        UserPelajar userPelajar = userPelajarRepository.findById(idPelajar).orElse(null);
        if(userPelajar!= null){
            Pembayaran pembayaran = new Pembayaran(uang, "Belum Bayar", userPelajar.getNama(), userPelajar.getAlamat(), "Bukti Pembayaran");
            pembayaranRepository.save(pembayaran);
            return "redirect:/Transaksi";
        }
        return "redirect:/Transaksi";
    }
    
    @PostMapping("/refund")
    public String refund(@RequestParam("idPelajar") String idPelajar, @RequestParam("uang") int uang, @RequestParam("keterangan") String keterangan){
        UserPelajar userPelajar = userPelajarRepository.findById(idPelajar).orElse(null);
        if(userPelajar!= null){
            Refund refund = new Refund(uang, keterangan, userPelajar.getNama(), userPelajar.getAlamat());
            refundRepository.save(refund);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/cetak-bukti-pembayaran/{idPembayaran}")
    public ResponseEntity<byte[]> cetakBuktiPembayaran(@PathVariable("idPembayaran") Long idPembayaran){
        Pembayaran pembayaran = pembayaranRepository.findById(idPembayaran).orElse(null);
        if(pembayaran!= null){
            String filename = "Bukti Pembayaran " + pembayaran.getId();
            byte[] fileBytes = pembayaran.getBuktiBayar();
            return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_PDF)
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".pdf\"")
                   .body(fileBytes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cetak-bukti-refund/{idRefund}")
    public ResponseEntity<byte[]> cetakBuktiRefund(@PathVariable("idRefund") Long idRefund){
        Refund refund = refundRepository.findById(idRefund).orElse(null);
        if(refund!= null){
            String filename = "Bukti Refund " + refund.getId();
            byte[] fileBytes = refund.getBuktiRefund();
            return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_PDF)
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".pdf\"")
                   .body(fileBytes);
        }
        return ResponseEntity.notFound().build();
    }

}
