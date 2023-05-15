import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        //new GameSplashScreen();
        //GameLogin gameLogin = new GameLogin();

        Utilisateur u1 = new Utilisateur("Geoffrey", "Anquetil", 21, "Hailwell", "mdp1", true);
        Utilisateur u2 = new Utilisateur("Alex", "Chen", 21, "Hentaro", "mdp2", true);

        HashMap<String, Utilisateur> hm = new HashMap<>();
        hm.put(u1.getPseudo(), u1);
        hm.put(u2.getPseudo(), u2);

        Utilisateurs users = new Utilisateurs(hm);

        users.serialize("./src/users/usersData.csv");

    }
}


