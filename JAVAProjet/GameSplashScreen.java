import javax.swing.JWindow;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

/**
 * Splash screen au lancement du jeu
 */
public class GameSplashScreen extends JWindow {

    Image splashscreen; // Objet de type Image de awt
    ImageIcon imageIcon; // Objet de type ImageIcon de swing

    public GameSplashScreen() throws IOException {
        // Chemin d'accès à l'image du splash
        String splashPath = "./src/img/splash.png";
        // On récupère l'image de splash screen dans l'objet Image
        splashscreen = Toolkit.getDefaultToolkit().getImage(splashPath);
        // On crée un ImageIcon à partir de l'Image
        imageIcon = new ImageIcon(splashscreen);
        // On redimensionne l'imageIcon à la taille de l'image en png
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // On récupère les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // On récupère les coordonnées en x et y auxquelles mettre le splashscreen pour qu'il soit centré
        int x = (screenSize.width - getSize().width)/2;
        int y = (screenSize.height - getSize().height)/2;
        // On définit la nouvelle position de l'imageIcon
        setLocation(x,y);
        // On affiche le splashScreen
        setVisible(true);
        // On ne l'affiche que 3sec

        //Instanciation des objets nécessaire au jeu
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.deserialize("./src/users/usersData.csv");

        try{
            Thread.sleep(1000);
            dispose();
            new GameLogin(utilisateurs); // On lance la fenêtre de login
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(splashscreen, 0, 0 ,this);
    }

}
