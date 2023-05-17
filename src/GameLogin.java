import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenêtre de login du jeu
 */
public class GameLogin extends JFrame implements ActionListener{

    private JLabel idLabel = new JLabel("Identifiant :"); // Label identifiant
    private JLabel pwLabel = new JLabel("Mot de passe :"); // Label mot de passe
    private JTextField idTextField = new JTextField(); // zone de texte pour ID
    private JPasswordField pwField = new JPasswordField(); // zone de texte pour MDP
    private JButton signInButton = new JButton("Se connecter"); // bouton de login
    private JButton signUpButton = new JButton("S'inscrire"); // bouton d'inscription

    public GameLogin(){
        super("Connexion"); // On set le titre à Connexion
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre
        this.setSize(300,300); // On fixe la taille de la fenêtre

        GridLayout grille = new GridLayout(6,1); // On crée une grille 6,1
        this.setLayout(grille); // On set le layout de la frame à cette grille

        idLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label
        pwLabel.setHorizontalAlignment(SwingConstants.CENTER); // idem

        signUpButton.addActionListener(this);
        signInButton.addActionListener(this);

        this.add(idLabel);
        this.add(idTextField);
        this.add(pwLabel);
        this.add(pwField);
        this.add(signInButton);
        this.add(signUpButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signInButton){

        } else if(e.getSource() == signUpButton){

        }
    }
}
