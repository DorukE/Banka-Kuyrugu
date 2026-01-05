import java.util.LinkedList;
import java.util.Queue;

/**
 * Doruk Evcimik - 240053028
 * Bilgisayar Programcılığı - Veri Yapıları Final Projesi
 * Banka Kuyruk Yönetim Mantığı (FIFO)
 */
public class BankaKuyrugu {
    // Java'nın hazır Queue (Kuyruk) yapısını kullanıyoruz
    // Bu yapı doğal olarak FIFO (İlk giren ilk çıkar) çalışır.
    private Queue<Musteri> kuyruk;

    public BankaKuyrugu() {
        this.kuyruk = new LinkedList<>();
    }

    // Sıraya yeni bir müşteri ekler (Enqueue)
    public void siraAl(Musteri yeniMusteri) {
        kuyruk.add(yeniMusteri);
        System.out.println(yeniMusteri.ad + " sıraya girdi.");
    }

    // Sıradaki müşteriyi gişeye çağırır (Dequeue)
    public Musteri musteriyiCagir() {
        if (kuyruk.isEmpty()) {
            System.out.println("Sırada bekleyen kimse yok!");
            return null;
        }
        return kuyruk.poll(); // En baştakini alır ve listeden siler
    }

    // Arayüzün (GUI) kuyruktaki herkesi görebilmesi için gereken metot
    public Queue<Musteri> getTumKuyruk() {
        return this.kuyruk;
    }

    // Konsol (Terminal) çıktısı için bekleyenlerin listesini verir
    public void sirayiGoster() {
        System.out.println("--- Güncel Sıra ---");
        for (Musteri m : kuyruk) {
            System.out.println(m);
        }
    }
}