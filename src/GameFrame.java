import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Frame du jeu, fenêtre dans laquelle sera affiché le quiz
 */
public class GameFrame extends JFrame {

    /**
     * Instancie un objet héritant de JFrame avec :
     * Pour titre "FunQuizz"
     * Pour taille 400x400
     * Qui s'affiche d'origine au centre de l'écran
     */
    public GameFrame(){
        String iconPath = "./src/img/icon.png";
        Image icon = Toolkit.getDefaultToolkit().getImage(iconPath);
        setTitle("FunQuizz");
        setIconImage(icon);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
