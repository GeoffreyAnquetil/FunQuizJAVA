import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class UserSuppressionPage extends JFrame implements ActionListener {

    private Utilisateurs users;

    private final JLabel label = new JLabel();
    private final JComboBox<String> comboBox = new JComboBox<>();
    private final JButton button = new JButton();

    public UserSuppressionPage(Utilisateurs users){
        super("Suppression Utilisateur");
        this.users = users;

        this.setSize(400,250); // On initialise la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre

        label.setText("Veuillez sélectionner l'utilisateur à supprimer :");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        button.setText("Supprimer");
        button.addActionListener(this);

        for(String key : users.getUsers().keySet()) comboBox.addItem(key);

        GridLayout grille = new GridLayout(3,1);
        this.setLayout(grille);

        this.add(label);
        this.add(comboBox);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pseudo = comboBox.getItemAt(comboBox.getSelectedIndex());
        if(e.getSource() == button){
            String[] options = {"Oui", "Non"};
            int choix = JOptionPane.showOptionDialog(null,
                    "Êtes-vous sûr de vouloir supprimer le compte de " + pseudo,
                    "Confirmation",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(choix == JOptionPane.YES_OPTION){
                try {
                    HashMap<String, Utilisateur> hm = users.getUsers();
                    hm.remove(pseudo);
                    users.setUsers(hm);
                    users.serialize("./src/users/usersData.csv");

                    JOptionPane.showMessageDialog(this,"Le compte de " + pseudo + " a bien été supprimé.");

                    this.dispose();
                    new UserSuppressionPage(users);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}