import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLogin extends JFrame implements ActionListener {

    public GameLogin(){
        // On crée une fenêtre JFrame avec pour titre Login
        super("Login");
        // On définit sa taille à 400x400
        setSize(400,400);
        // On fait en sorte que le programme termine lorsqu'elle est fermée
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // On affiche la fenêtre au centre
        setLocationRelativeTo(null);
        // On affiche la fenêtre
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}