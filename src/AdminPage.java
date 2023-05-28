import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
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

        this.setSize(600,250); // On initialise la taille de la fenêtre
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
        label3.setVisible(false);

        // Initialisation des boutons
        button1.setText("Confirmer");
        button1.addActionListener(this);
        button2.setText("Confirmer");
        button2.addActionListener(this);
        button2.setVisible(false);
        button3.setText("Annuler");
        button3.addActionListener(this);
        button3.setVisible(false);

        // On rend invisible le textfield
        textField.setVisible(false);

        // On remplie le panel2
        panel2.add(label1);
        panel2.add(comboBox1);
        panel2.add(new JLabel(""));
        // On remplie le panel3
        panel3.add(label2);
        panel3.add(comboBox2);
        panel3.add(button1);
        // On remplie le panel4
        panel4.add(label3);
        panel4.add(textField);
        panel4.add(button2);
        panel4.add(button3);

        // On ajoute les panel à la fenêtre
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection1 = comboBox1.getItemAt(comboBox1.getSelectedIndex());
        String selection2 = comboBox2.getItemAt(comboBox2.getSelectedIndex());
        String text = textField.getText();

        if(e.getSource() == button1){
            label3.setVisible(true);
            textField.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);

            label3.setText("<html><span>" +
                            "Nouvelle valeur de " + selection2 + " pour " + selection1 + " :"
                            + "</html></span>");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            button1.setVisible(false); // On arrête d'afficher le bouton 1
            button1.removeActionListener(this); // On enlève son ActionListener
            comboBox1.setEnabled(false);
            comboBox2.setEnabled(false);
            panel3.revalidate(); // On actualise le panel3
            // On ajoute les éléments au panel3
            panel4.revalidate(); // On actualise le panel4

            this.revalidate();
            this.repaint();
        } else if(e.getSource() == button3){
            button1.setVisible(true);
            button1.addActionListener(this);
            comboBox1.setEnabled(true);
            comboBox2.setEnabled(true);
            panel3.revalidate();

            label3.setVisible(false);
            textField.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
            panel4.revalidate();
            this.revalidate();
            this.repaint();
        } else if(e.getSource() == button2){
            // On affiche un popup pour prévenir l'utilisateur que l'action est importante
            String[] options = {"Oui", "Non"};
            if((selection2 == "suspendu" || selection2 == "admin")
                    && ((text != "true") && (text != "false"))){
                JOptionPane.showMessageDialog(this, "Veuillez saisir true ou false");
                textField.setText("");
            } else if((selection2 == "pseudo")
                    && (users.getUsers().keySet().contains(text))){
                JOptionPane.showMessageDialog(this, "Pseudo déjà existant");
                textField.setText("");
            } else {
                int choix = JOptionPane.showOptionDialog(null,
                        "Êtes-vous sûr de vouloir changer le " + selection2
                                + " de " + selection1 + " en : " + text,
                        "Confirmation", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (choix == JOptionPane.YES_OPTION) { // Si l'utilisateur clique sur "oui"
                    HashMap<String, Utilisateur> hm = users.getUsers(); // On récupère la hashmap de users
                    Utilisateur user = hm.get(selection1); // On récupère l'utilisateur sélectionné
                    hm.remove(selection1); // On l'enlève de la hashmap
                    switch (selection2){ // Selon le choix fait, on modifie le user
                        case "prenom" -> user.setPrenom(textField.getText());
                        case "nom" -> user.setNom(textField.getText());
                        case "age" -> user.setAge(Integer.parseInt(textField.getText()));
                        case "pseudo" -> user.setPseudo(textField.getText());
                        case "MdP" -> user.setMdp(textField.getText());
                        case "admin" -> user.setAdmin(Boolean.parseBoolean(textField.getText()));
                        case "suspendu" -> user.setSuspendu(Boolean.parseBoolean(textField.getText()));
                    }
                    hm.put(user.getPseudo(), user); // On le rajoute à la hashmap
                    users.setUsers(hm); // On modifie la hashmap de users
                    try {
                        users.serialize("./src/users/usersData.csv"); // On sérialise
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.dispose(); // On ferme la page
                    new AdminPage(users); // On en ouvre une nouvelle avec les users modifiés
                } else { // S'il clique sur "non" ou ferme la fenêtre
                    textField.setText("");
                }
            }
        }
    }
}
