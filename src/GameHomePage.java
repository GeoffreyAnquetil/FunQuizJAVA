import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameHomePage extends JFrame implements ActionListener {

    public GameHomePage(Utilisateur user){
        super("FunQuiz");
        setSize(800,800);
        // On fait en sorte que le programme termine lorsqu'elle est fermée
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // On affiche la fenêtre au centre
        setLocationRelativeTo(null);
        // On affiche la fenetre
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
