import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class UsersTab extends JFrame {

    public UsersTab() throws IOException {
        super("Utilisateurs");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // On récupère les données dans le fichier usersData
        ArrayList<String[]> dataArray = new CSVFileIO().readCSV("./src/users/usersData.csv");

        String[][] data = new String[dataArray.size()][];
        for (int i = 0; i < dataArray.size(); i++) {
            data[i] = dataArray.get(i);
        }

        //Les titres des colonnes
        String[] champs = {"prenom", "nom", "age", "pseudo", "MdP", "admin", "suspendu"};
        JTable tableau = new JTable(data, champs);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        this.getContentPane().add(new JScrollPane(tableau));
    }
}