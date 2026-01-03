import javax.swing.*;
import java.awt.*;

public class BankaArayuz extends JFrame {
    private BankaKuyrugu banka = new BankaKuyrugu();
    private int siraSayaci = 1;

    // Görsel bileşenler
    private JTextField isimAlani = new JTextField(15);
    private JTextArea listeAlani = new JTextArea(10, 20);
    private JLabel durumEtiketi = new JLabel("Hoşgeldiniz! Lütfen sıra alınız.");

    public BankaArayuz() {
        setTitle("Banka Sıra Takip Sistemi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Arayüz elemanlarını ekleyelim
        add(new JLabel("Müşteri Adı:"));
        add(isimAlani);

        JButton btnSiraAl = new JButton("Sıra Al");
        JButton btnCagir = new JButton("Sıradakini Çağır");
        add(btnSiraAl);
        add(btnCagir);

        listeAlani.setEditable(false);
        add(new JScrollPane(listeAlani));
        add(durumEtiketi);

        // BUTON İŞLEMLERİ
        btnSiraAl.addActionListener(e -> {
            String isim = isimAlani.getText();
            if (!isim.isEmpty()) {
                banka.siraAl(new Musteri(isim, siraSayaci++));
                guncelle();
                isimAlani.setText("");
            }
        });

        btnCagir.addActionListener(e -> {
            Musteri cagirilan = banka.musteriyiCagir();
            if (cagirilan != null) {
                durumEtiketi.setText("Şu an gişede: " + cagirilan.ad);
                guncelle();
            } else {
                durumEtiketi.setText("Bekleyen kimse yok!");
            }
        });
    }

    private void guncelle() {
        // Listeyi her işlemde yeniler
        StringBuilder sb = new StringBuilder("--- Bekleyenler ---\n");
        // Not: BankaKuyrugu sınıfına sirayiGetir metodunu eklememiz gerekecek
        // Şimdilik basitçe burayı konsoldan kopyaladığın mantıkla doldurabilirsin
        listeAlani.setText("Sıra güncellendi (Konsolu kontrol et)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankaArayuz().setVisible(true);
        });
    }
}