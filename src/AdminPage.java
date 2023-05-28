import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AdminPage extends JFrame implements ActionListener {

    private Utilisateurs users;

    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel4 = new JPanel();

    private final JLabel label1 = new JLabel();
    private final JLabel label2 = new JLabel();
    private final JLabel label3 = new JLabel();

    private final JComboBox<String> comboBox1 = new JComboBox<>();
    private final JComboBox<String> comboBox2 = new JComboBox<>();

    private final JButton button1 = new JButton();
    private final JButton button2 = new JButton();
    private final JButton button3 = new JButton();

    private final JTextField textField = new JTextField();

    public AdminPage(Utilisateurs users){
        super("Administration");
        this.users = users;

        this.setSize(600,200); // On initialise la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre

        GridLayout grillePrincipale = new GridLayout(4,1); // Grille principale de la fenêtre
        GridLayout grilleSecondaire = new GridLayout(1,3); // Grille secondaire (pour les Panel dans la Frame principale)
        GridLayout grillePanel4 = new GridLayout(1,4); // Grille tertiaire (pour le panel4 seulement)

        // Initialisation des layout pour la fenêtre et les panel
        this.setLayout(grillePrincipale);
        panel1.setLayout(grilleSecondaire);
        panel2.setLayout(grilleSecondaire);
        panel3.setLayout(grilleSecondaire);
        panel4.setLayout(grillePanel4);

        // Initialisation des comboBox
        Set<String> pseudos = users.getUsers().keySet();
        for(String pseudo : pseudos) comboBox1.addItem(pseudo);
        String[] champs = {"prenom", "nom", "age", "pseudo", "MdP", "admin", "suspendu"};
        for(String champ : champs) comboBox2.addItem(champ);

        // Initialisation des label
        label1.setText("Utilisateur à modifier :");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setText("Champs à modifier :");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        // Initialisation des boutons
        button1.setText("Confirmer");
        button1.addActionListener(this);

        // On remplie le panel2
        panel2.add(label1);
        panel2.add(comboBox1);
        panel2.add(new JLabel(""));
        // On remplie le panel3
        panel3.add(label2);
        panel3.add(comboBox2);
        panel3.add(button1);

        // On ajoute les panel à la fenêtre
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            button2.setText("Confirmer");
            button2.addActionListener(this);
            button3.setText("Annuler");
            button3.addActionListener(this);
            label3.setText("Nouvelle valeur de " + comboBox2.getItemAt(comboBox2.getSelectedIndex()) + " :");
            label3.setHorizontalAlignment(SwingConstants.CENTER);

            button1.setVisible(false); // On arrête d'afficher le bouton 1
            button1.removeActionListener(this); // On enlève son ActionListener
            panel3.revalidate(); // On actualise le panel3
            // On ajoute les éléments au panel3
            panel4.add(label3);
            panel4.add(textField);
            panel4.add(button2);
            panel4.add(button3);
            panel4.revalidate(); // On actualise le panel4

            this.revalidate();
            this.repaint();
        }
    }
}
