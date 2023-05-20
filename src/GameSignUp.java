import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSignUp extends JFrame implements ActionListener {

    public GameSignUp(){
        super("Inscription"); // On set le titre à Connexion
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre
        this.setSize(300,300); // On fixe la taille de la fenêtre
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
