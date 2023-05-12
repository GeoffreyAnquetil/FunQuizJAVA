import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenêtre de login du jeu
 */
public class GameLogin extends JFrame implements ActionListener{

    public GameLogin(){
        // On crée une fenêtre JFrame avec pour titre Login
        super("Login");
        // On définit sa taille à 400x400
        setSize(400,200);
        // On fait en sorte que le programme termine lorsqu'elle est fermée
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // On affiche la fenêtre au centre
        setLocationRelativeTo(null);

        // On instancie un JPanel
        JPanel loginPanel = new JPanel();

        // Dimension pour placer les différents éléments
        Dimension dimLabel = new Dimension(this.getWidth()/2,25);
        Dimension dimTextArea = new Dimension(this.getWidth()/2,25);
        Dimension dimPasswordField = new Dimension(this.getWidth()/2,25);
        Dimension dimButton = new Dimension(this.getWidth(),40);

        // Fonts
        Font fontLabel = new Font("Arial", Font.BOLD, 20);
        Font fontTextArea = new Font("Arial", Font.PLAIN, 20);
        Font fontPasswordField = new Font("Arial", Font.PLAIN, 20);

        // On instancie un JLabel "Identifiant :"
        JLabel labelID = new JLabel("Identifiant :", JLabel.CENTER);
        // On ajoute la font
        labelID.setFont(fontLabel);
        labelID.setPreferredSize(dimLabel);
        // On l'ajoute au JPanel
        loginPanel.add(labelID);

        // On instancie un JTextArea pour la saisie du pseudo
        JTextArea areaID = new JTextArea();
        areaID.setPreferredSize(dimTextArea);
        areaID.setFont(fontTextArea);
        // Ajoute le JTexte Area au JPanel
        loginPanel.add(areaID);

        // On instancie un JLabel "Identifiant :"
        JLabel labelMDP = new JLabel("Mot de passe :", JLabel.CENTER);
        // On ajoute la font
        labelMDP.setFont(fontLabel);
        // On définit la taille
        labelMDP.setPreferredSize(dimLabel);
        // On l'ajoute au JPanel
        loginPanel.add(labelMDP);

        // On instancie un JTextArea pour la saisie du mot de passe
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fontPasswordField);
        passwordField.setPreferredSize(dimPasswordField);
        loginPanel.add(passwordField);

        // On instancie un JButton pour se logger
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(dimButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = areaID.getText();
                String mdp = String.copyValueOf(passwordField.getPassword());

                Utilisateurs users = new Utilisateurs();
                users.deserialize();

                if(users.getUsers().get(pseudo) == null){
                    JOptionPane.showMessageDialog(loginPanel, "Cet utilisateur n'existe pas");
                } else {
                    if(mdp.equals(users.getUsers().get(pseudo).getMdp())){
                        successfulLogin();
                    }
                    else JOptionPane.showMessageDialog(loginPanel, "Mauvais mot de passe");
                }
            }
        });
        loginPanel.add(loginButton);

        // On ajoute le JPanel au JFrame
        this.add(loginPanel);

        // On affiche la fenêtre
        setVisible(true);
    }

    private void successfulLogin(){
        this.dispose();
        new GameHomePage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}