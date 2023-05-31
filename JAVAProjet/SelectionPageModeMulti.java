import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectionPageModeMulti implements ActionListener {
    private JFrame frame;
    private JButton modeAbutton;
    private JButton modeBbutton;
    private final Utilisateur user;
    private final Utilisateurs users;

    /**
     * Page de selection du mode de jeu en multijoueur
     */
    public SelectionPageModeMulti(Utilisateur user, Utilisateurs users){
        this.user = user;
        this.users = users;

        frame = new JFrame();
        frame.setSize(500,200);
        frame.setLocationRelativeTo(null);
        frame.setTitle("page de sélection du mode de jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modeAbutton = new JButton("Mode A");
        modeBbutton = new JButton("Mode B");

        JPanel modeJpanel = new JPanel();
        modeJpanel.add(modeAbutton);
        modeJpanel.add(modeBbutton);
        frame.add(modeJpanel, BorderLayout.CENTER);

        modeAbutton.addActionListener(this);
        modeBbutton.addActionListener(this);

        frame.setVisible(true);
    }

    /**
     * affiche la page pour le mode A ou B en fonction du bouton appuyer
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JButton bouton = (JButton) event.getSource(); // bouton prend la source de l'évènement(elle prend le button qui a été appuyé)
        if (bouton.getText().equals("Mode A")){
            try {
                new ThemeSelectionPageMultiA(user,users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.dispose();
        }
        if (bouton.getText().equals("Mode B")){
            try {
                new ThemeSelectionPageMultiB(user,users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.dispose();
        }
    }
}
