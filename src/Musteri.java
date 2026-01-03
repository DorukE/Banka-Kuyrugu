public class Musteri {
    String ad;
    int siraNo;

    public Musteri(String ad, int siraNo) {
        this.ad = ad;
        this.siraNo = siraNo;
    }

    @Override
    public String toString() {
        return "Sıra No: " + siraNo + " | İsim: " + ad;
    }
}