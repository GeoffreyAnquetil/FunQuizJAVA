import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PageFinMulti implements ActionListener {
    private final JFrame frame;
    private final JButton optionButtonRestart;
    private final JButton optionButtonAccueil;
    private final JButton optionButtonDeconnexion;
    private final Utilisateur user;
    private final Utilisateurs users;

    /**
     * Page de fin pour le multijoueur
     * @param scoreA : score de l'équipe A
     * @param scoreB : score de l'équipe B
     */
    public PageFinMulti(int scoreA, int scoreB, Utilisateur user, Utilisateurs users){
        this.user = user;
        this.users = users;

        frame = new JFrame("Page de fin solo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        //Affichage du message.
        JPanel messageJpanel = new JPanel();
        messageJpanel.setLayout(new BoxLayout(messageJpanel, BoxLayout.Y_AXIS));
        JLabel messageJlabel;
        JLabel perdantJlabel;
        ImageIcon GifImage;
        if(scoreA>scoreB){ // score positif
            messageJlabel = new JLabel("Bravo Equipe A. Votre score final est de : "+scoreA);
            perdantJlabel = new JLabel("Dommage pour l'equipe B : "+scoreB);
            //Image
            GifImage = new ImageIcon("./src/more/victoire.gif");
        }else {
            if (scoreA == scoreB) {
                messageJlabel = new JLabel("NUL NUL NUL. Score final equipe B : " + scoreB);
                perdantJlabel = new JLabel("Dommage pour l'equipe A : " + scoreA);
                //Image
                GifImage = new ImageIcon("./src/more/GIfNul.gif");
            } else {
                // score nul ou négatif
                messageJlabel = new JLabel("Bravo Equipe B. Votre score final est de : " + scoreB);
                perdantJlabel = new JLabel("Dommage pour l'equipe A : " + scoreA);
                //Image
                GifImage = new ImageIcon("./src/more/victoire.gif");
            }
        }
        //Message
        messageJlabel.setFont(new Font("Arial", Font.BOLD, 20));
        perdantJlabel.setFont(new Font("Arial", Font.BOLD, 15));
        messageJpanel.add(messageJlabel);
        messageJpanel.add(perdantJlabel);
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


        if(scoreA!=scoreB) {
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
            new SelectionPageModeMulti(user,users);
            frame.dispose();
        }
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
     * Joue la musique associé à une victoire
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
     * Joue la musique associé à une défaite
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
