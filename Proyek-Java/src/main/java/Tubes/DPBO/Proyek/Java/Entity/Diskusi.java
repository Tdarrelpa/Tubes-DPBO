package Tubes.DPBO.Proyek.Java.Entity;

import com.mycompany.datadiri.UserPelajar;
import java.util.Arrays;
import Tubes.DPBO.Proyek.Java.local_entity.Aktivitas;

public class Diskusi extends Aktivitas
{
    private String ruang;
    private int waktuDiskusi;

    public Diskusi(String jadwal, int durasi, String status, String ruang, int waktuDiskusi)
    {
        super(jadwal, durasi, status);
        this.ruang = ruang;
        this.waktuDiskusi = waktuDiskusi;
    }

    public void MulaiDiskusi()
    {
        boolean x = MulaiAktivitas();
        if(x == true)
        {
            try 
            {
                System.out.printf("Diskusi dilaksanakan tanggal %s, ruang %s, dan waktu %d jam\n", jadwal, ruang, waktuDiskusi);
            } 
            catch (Exception e) 
            {
                System.err.println(e.fillInStackTrace());
                System.err.println(Arrays.toString(e.getStackTrace()));
                System.err.println(e.getCause());
                System.err.println("Error terjadi: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Diskusi tidak dimulai");
        }
    }

    public void TutupDiskusi()
    {
        boolean x = SelesaiAktivitas();
        if(x == true)
        {
            try 
            {
                System.out.printf("Diskusi berakhir pada tanggal %s, di ruang %s, dan waktu %d jam, dengan durasi selama %d\n", jadwal, ruang, waktuDiskusi, durasi);
            } 
            catch (Exception e) 
            {
                System.err.println(e.fillInStackTrace());
                System.err.println(Arrays.toString(e.getStackTrace()));
                System.err.println(e.getCause());
                System.err.println("Error terjadi: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Diskusi belum selesai");
        }
    }

    public void MenjawabPertanyaan()
    {
        try 
        {
            UserPelajar up = null;
            System.out.printf("Selama %d jam, %s bisa menjawab pertanyaan", waktuDiskusi, up.getNama());
        } 
        catch (Exception e) 
        {
            System.err.println(e.fillInStackTrace());
            System.err.println(Arrays.toString(e.getStackTrace()));
            System.err.println(e.getCause());
            System.err.println("Error terjadi: " + e.getMessage());
        }
    }
}
