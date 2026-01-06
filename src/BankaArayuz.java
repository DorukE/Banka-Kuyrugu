import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ad Soyad: Doruk Evcimik - 240053028
 * Bölüm: Bilgisayar Programcılığı
 * Proje: Profesyonel Banka Sıramatik Paneli (Yeşil-Beyaz Tema)
 */
public class BankaArayuz extends JFrame {
    private BankaKuyrugu banka = new BankaKuyrugu();
    private int siraSayaci = 1;

    // --- RENK VE FONT PALETİ ---
    Color anaYesil = new Color(34, 139, 34);
    Color acikYesil = new Color(240, 255, 240);
    Color beyaz = Color.WHITE;
    Font baslikFont = new Font("Segoe UI", Font.BOLD, 24);
    Font altBaslikFont = new Font("Segoe UI", Font.BOLD, 16);
    Font metinFont = new Font("Segoe UI", Font.PLAIN, 14);

    private JTextField isimAlani = new JTextField();
    private JTextArea listeAlani = new JTextArea();
    private JLabel bekleyenSayisiLabel = new JLabel("Bekleyen Sayısı: 0");
    private JLabel ilkUcLabel = new JLabel("<html><b>Sırası Yaklaşanlar:</b> - </html>");

    public BankaArayuz() {
        setTitle("ALANYA BANK | Sıramatik Sistemi");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(acikYesil);
        setLayout(new BorderLayout(15, 15));

        JPanel ustPanel = new JPanel(new GridLayout(2, 1));
        ustPanel.setBackground(anaYesil);
        ustPanel.setPreferredSize(new Dimension(500, 100));

        JLabel baslik = new JLabel("ALANYA BANK", SwingConstants.CENTER);
        baslik.setFont(baslikFont);
        baslik.setForeground(beyaz);
        ustPanel.add(baslik);

        bekleyenSayisiLabel.setFont(altBaslikFont);
        bekleyenSayisiLabel.setForeground(beyaz);
        bekleyenSayisiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ustPanel.add(bekleyenSayisiLabel);
        add(ustPanel, BorderLayout.NORTH);

        JPanel ortaPanel = new JPanel(new BorderLayout(15, 15));
        ortaPanel.setOpaque(false);
        ortaPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        JPanel girisPaneli = new JPanel(new GridLayout(4, 1, 8, 8));
        girisPaneli.setOpaque(false);

        JLabel label = new JLabel("Müşteri Adı Soyadı:");
        label.setFont(metinFont);
        girisPaneli.add(label);

        isimAlani.setFont(new Font("Arial", Font.BOLD, 16));
        isimAlani.setBorder(new LineBorder(anaYesil, 2));
        girisPaneli.add(isimAlani);

        JButton btnSiraAl = new JButton("➕ SIRA AL");
        btnSiraAl.setBackground(anaYesil);
        btnSiraAl.setForeground(beyaz);
        btnSiraAl.setFont(altBaslikFont);
        btnSiraAl.setFocusPainted(false);

        JButton btnCagir = new JButton("🔔 SIRADAKİNİ ÇAĞIR");
        btnCagir.setBackground(beyaz);
        btnCagir.setForeground(anaYesil);
        btnCagir.setFont(altBaslikFont);
        btnCagir.setBorder(new LineBorder(anaYesil, 2));
        btnCagir.setFocusPainted(false);

        girisPaneli.add(btnSiraAl);
        girisPaneli.add(btnCagir);
        ortaPanel.add(girisPaneli, BorderLayout.NORTH);

        listeAlani.setFont(new Font("Consolas", Font.PLAIN, 15));
        listeAlani.setEditable(false);
        listeAlani.setBorder(new LineBorder(anaYesil, 1));
        JScrollPane scroll = new JScrollPane(listeAlani);
        ortaPanel.add(scroll, BorderLayout.CENTER);

        ilkUcLabel.setOpaque(true);
        ilkUcLabel.setBackground(beyaz);
        ilkUcLabel.setForeground(anaYesil);
        ilkUcLabel.setFont(metinFont);
        ilkUcLabel.setBorder(new LineBorder(anaYesil, 1));
        ilkUcLabel.setPreferredSize(new Dimension(0, 50));
        ortaPanel.add(ilkUcLabel, BorderLayout.SOUTH);

        add(ortaPanel, BorderLayout.CENTER);

        // --- GÜNCELLENMİŞ BUTON FONKSİYONU ---
        btnSiraAl.addActionListener(e -> {
            String ad = isimAlani.getText().trim();
            // Regex: Sadece harf, Türkçe karakter ve boşluk izni verir
            if (!ad.isEmpty() && ad.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$")) {
                banka.siraAl(new Musteri(ad, siraSayaci++));
                guncelle();
                isimAlani.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Hata: Lütfen sadece harflerden oluşan geçerli bir isim giriniz!",
                        "Giriş Geçersiz",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCagir.addActionListener(e -> {
            Musteri cagirilan = banka.musteriyiCagir();
            guncelle();
            if (cagirilan != null) {
                JOptionPane.showMessageDialog(this,
                        "Sıradaki Müşteri Gişeye Bekleniyor:\n" + cagirilan.ad,
                        "GİŞE ÇAĞRISI", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void guncelle() {
        List<Musteri> kuyruk = new ArrayList<>(banka.getTumKuyruk());
        bekleyenSayisiLabel.setText("Bekleyen Müşteri Sayısı: " + kuyruk.size());

        StringBuilder ilkUc = new StringBuilder("<html>&nbsp;&nbsp;<b>Sırası Yaklaşanlar:</b> ");
        for (int i = 0; i < Math.min(3, kuyruk.size()); i++) {
            ilkUc.append(kuyruk.get(i).ad).append(i < Math.min(3, kuyruk.size()) - 1 ? ", " : "");
        }
        if (kuyruk.isEmpty()) ilkUc.append("-");
        ilkUc.append("</html>");
        ilkUcLabel.setText(ilkUc.toString());

        StringBuilder sb = new StringBuilder("\n  --- GÜNCEL BEKLEME LİSTESİ ---\n\n");
        for (Musteri m : kuyruk) {
            sb.append("  [No: ").append(m.numara).append("] - ").append(m.ad).append("\n");
        }
        listeAlani.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankaArayuz().setVisible(true));
    }
}
