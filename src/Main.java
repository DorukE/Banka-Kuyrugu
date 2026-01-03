public class Main {
    public static void main(String[] args) {
        BankaKuyrugu banka = new BankaKuyrugu();

        // Test için birkaç kişi ekleyelim
        banka.siraAl(new Musteri("Ahmet", 1));
        banka.siraAl(new Musteri("Ayşe", 2));
        banka.siraAl(new Musteri("Mehmet", 3));

        System.out.println("--- Bekleyenler ---");
        banka.sirayiGoster();

        System.out.println("\n--- Gişeye Çağrılıyor ---");
        Musteri cagirilan = banka.musteriyiCagir();
        System.out.println("İşlem gören: " + cagirilan.ad);

        System.out.println("\n--- Kalan Sıra ---");
        banka.sirayiGoster();
    }
}