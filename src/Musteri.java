/**
 * Doruk Evcimik - 240053028
 * Müşteri Veri Modeli
 */
public class Musteri {
    public String ad;
    public int numara;

    public Musteri(String ad, int numara) {
        this.ad = ad;
        this.numara = numara;
    }

    @Override
    public String toString() {
        return "Sıra No: " + numara + " | İsim: " + ad;
    }
}