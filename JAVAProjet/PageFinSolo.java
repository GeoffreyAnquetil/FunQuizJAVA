import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PageFinSolo implements ActionListener {
    private final JFrame frame;
    private final JButton optionButtonRestart;
    private final JButton optionButtonAccueil;
    private final JButton optionButtonDeconnexion;
    private final Utilisateur user;
    private final Utilisateurs users;
    public PageFinSolo(int score, Utilisateur user, Utilisateurs users){
        this.user = user;
        this.users = users;

        frame = new JFrame("Page de fin solo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        //Affichage du message.
        JPanel messageJpanel = new JPanel();
        JLabel messageJlabel;
        ImageIcon GifImage;
        if(score>0){ // score positif
            messageJlabel = new JLabel("Bravo. Votre score final est de : "+score);
            //Image
            GifImage = new ImageIcon("./src/more/victoire.gif");
        }else { // score nul ou négatif
            messageJlabel = new JLabel("Dommage. Vous n'êtes pas très fort. Score :"+score);
            GifImage = new ImageIcon("./src/more/GifNul.gif");

        }
        //Message
        messageJlabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageJpanel.add(messageJlabel);
        frame.add(messageJpanel, BorderLayout.NORTH);

        //Image
        JLabel gifJlabel= new JLabel();
        gifJlabel.setIcon(GifImage);
        JPanel gifJpanel= new JPanel();
        gifJpanel.add(gifJlabel);
        frame.add(gifJpanel, BorderLayout.CENTER);

        //bouton
        JPanel optionsPanel = new JPanel();
        optionButtonRestart = new JButton("Restart"); //Option 1
        optionButtonAccueil = new JButton("Accueil"); //Option 2
        optionButtonDeconnexion = new JButton("Deconnexion"); // option 3

        optionsPanel.add(optionButtonAccueil);
        optionsPanel.add(optionButtonRestart);
        optionsPanel.add(optionButtonDeconnexion);

        frame.add(optionsPanel, BorderLayout.SOUTH);

        optionButtonRestart.addActionListener(this);
        optionButtonAccueil.addActionListener(this);
        optionButtonDeconnexion.addActionListener(this);

        frame.setVisible(true);

        if(score>0) {
            MusicWin();
        }else{
            MusicLoose();
        }
    }

    /**
     * Si bouton Restart, on remet sur la page de selection.
     * Si bouton Accueil, on remet sur la page d'accueil.
     * Si bouton Deconnexion, on remet sur la toute 1re page.
     * @param event the event to be processed
     */
    public void actionPerformed (ActionEvent event){
        JButton bouton = (JButton) event.getSource(); // bouton prend la source de l'évènement(elle prend le button qui a été appuyé)
        if(bouton.getText().equals("Restart")){
            // Page où on choisit les themes
            try {
                new ThemeSelectionPageSolo(user,users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.dispose();
        } // Page d'accueil
        if(bouton.getText().equals("Accueil")){
            try {
                new GameHomePage(user,users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.dispose();
        }
        if(bouton.getText().equals("Deconnexion")){
            try {
                new GameSplashScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.dispose();
        }
    }

    /**
     * Joue la musique si victoire
     */
    public void MusicWin(){
        String filepath= "./src/more/ENCEmusic.wav";
        try{
            File fileaudio = new File(filepath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(fileaudio);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){
            e.printStackTrace();
        }

    }

    /**
     * Joue la musique si defaite
     */
    public void MusicLoose(){
        String filepath= "./src/more/SimpsonNul.wav";
        try{
            File fileaudio = new File(filepath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(fileaudio);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){
            e.printStackTrace();
        }

    }
}
