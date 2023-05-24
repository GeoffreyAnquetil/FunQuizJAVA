import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdminScreen extends JFrame implements ActionListener {

    public AdminScreen(Utilisateurs users) throws IOException {
        super("Administration");
        this.setSize(500,500); // On initialise la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre

        CSVFileIO csvFileIO = new CSVFileIO(); // On instancie un CSVFileIO
        ArrayList<String[]> array = csvFileIO.readCSV("./src/users/usersData.csv");
        String[][] tab = array.toArray(new String[0][]);
        String[] cols = {"Prénom","Nom","Age","Pseudo","Mdp","Admin","Suspendu"};

        DefaultTableModel model = new DefaultTableModel(tab, cols);
        JTable usersData = new JTable(model);

        this.add(usersData);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
