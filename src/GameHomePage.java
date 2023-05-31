import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameHomePage extends JFrame implements ActionListener {

    private Utilisateur user;
    private Utilisateurs users;

    private final JPanel panelPrincipal = new JPanel();
    private final JPanel panelPrincipalButtons = new JPanel();

    private final JPanel panelGlobalBestScores = new JPanel();

    private final JPanel panelPersoBestScore = new JPanel();
    private final JPanel panelPersoBestScoreSec = new JPanel();

    private final JLabel welcomeLabel = new JLabel();
    private final JLabel choiceLabel = new JLabel("Choisissez votre mode de jeu :");
    private final JLabel bestPersScorelabel = new JLabel();

    private final JButton soloButton = new JButton("Jeu Solo");
    private final JButton multiButton = new JButton("Jeu Multi");
    private final JButton adminButton = new JButton("Administration");
    private final JButton retryButton = new JButton("Tenter de battre ce score");

    public GameHomePage(Utilisateur user, Utilisateurs users) throws IOException {
        super("FunQuiz");

        this.users = users;
        this.user = user;

        setSize(1100,250);
        // On fait en sorte que le programme termine lorsqu'elle est fermée
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // On affiche la fenêtre au centre
        setLocationRelativeTo(null);
        // On affiche la fenetre
        setVisible(true);

        // On crée les grilles pour placer nos items dans les panels
        GridLayout grille31 = new GridLayout(3,1);
        GridLayout grille13 = new GridLayout(1,3);
        GridLayout grille21 = new GridLayout(2,1);
        GridLayout grille12 = new GridLayout(1,2);

        this.setLayout(grille13);
        panelPrincipal.setLayout(grille31);
        panelPersoBestScore.setLayout(grille31);
        panelPersoBestScoreSec.setLayout(grille21);

        // On initialise les JLabel
        welcomeLabel.setText("Bienvenue " + user.getPseudo() + " !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        choiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bestPersScorelabel.setHorizontalAlignment(SwingConstants.CENTER);

        // On récupère le meilleur score de l'utilisateur
        CSVFileIO csvFileIO = new CSVFileIO();
        ArrayList<String[]> array = csvFileIO.readCSV("./src/users/userBestScores.csv");
        bestPersScorelabel.setText("Vous n'avez pas encore de meilleur score");
        for(String[] score : array)
            if(score[0].equals(user.getPseudo()))
                bestPersScorelabel.setText("Votre meilleur score : " + score[1]);

        // Si l'utilisateur est admin on affichera 3 boutons, 2 sinon
        if(user.isAdmin()){
            panelPrincipalButtons.setLayout(grille13);
        } else {
            panelPrincipalButtons.setLayout(grille12);
        }

        // Initialisation des boutons
        if(!bestPersScorelabel.getText().equals("Vous n'avez pas encore de meilleur score")){
            retryButton.addActionListener(this);
        } else {
            retryButton.setEnabled(false);
        }
        soloButton.addActionListener(this);
        panelPrincipalButtons.add(soloButton);
        multiButton.addActionListener(this);
        panelPrincipalButtons.add(multiButton);

        if(user.isAdmin()){
            adminButton.addActionListener(this);
            panelPrincipalButtons.add(adminButton);
        }

        panelPrincipal.add(welcomeLabel);
        panelPrincipal.add(choiceLabel);
        panelPrincipal.add(panelPrincipalButtons);

        // Création du tableau des 10 derniers meilleurs scores
        // Récupération des scores
        ArrayList<String[]> dataArray = csvFileIO.readCSV("./src/users/generalBestScores.csv");

        System.out.println(dataArray.toString());

        String[][] data = new String[dataArray.size()][];
        for (int i = 0; i < dataArray.size(); i++) {
            data[i] = dataArray.get(i);
        }

        System.out.println(Arrays.deepToString(data));

        // Création des titres des colonnes
        String[] champs = {"Les Meilleurs", "Scores"};
        JTable tableau = new JTable(data, champs);
        tableau.setEnabled(false);

        panelGlobalBestScores.setLayout(new GridLayout(1,1));
        panelGlobalBestScores.add(new JScrollPane(tableau));

        panelPersoBestScore.add(new JLabel(""));
        panelPersoBestScoreSec.add(bestPersScorelabel);
        panelPersoBestScoreSec.add(retryButton);
        panelPersoBestScore.add(panelPersoBestScoreSec);

        this.add(panelGlobalBestScores);
        this.add(panelPrincipal);
        this.add(panelPersoBestScore);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == soloButton){
            new GameSoloMode(user, users);
            this.dispose();
        } else if(e.getSource() == multiButton){
            new GameMultiMode(user, users);
            this.dispose();
        } else if(e.getSource() == adminButton){
            new AdminPage(users);
        } else if(e.getSource() == retryButton){
            JOptionPane.showMessageDialog(this, "Fonctionnalité à venir...");
        }
    }
}
