/**
 * Doruk Evcimik - 240053028
 * Müşteri Veri Modeli
 * Not: Arayüz kodundaki 'numara' çağırma hatasını düzeltmek için
 * değişken adı 'numara' olarak güncellenmiştir.
 */
public class Musteri {
    public String ad;
    public int numara; // Arayüzle uyum için 'siraNo' yerine 'numara' yapıldı

    public Musteri(String ad, int numara) {
        this.ad = ad;
        this.numara = numara;
    }

    @Override
    public String toString() {
        return "Sıra No: " + numara + " | İsim: " + ad;
    }
}