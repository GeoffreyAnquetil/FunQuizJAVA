import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        String path = "./src/testfile.txt";
        Personne personne1 = new Personne("0001", "Hailwell", "mdp1", true, "PP1.png");
        Personne personne2 = new Personne("0002", "Araonof", "mdp2", true, "PP2.png");
        Personne personne3 = new Personne("0003", "Flowt", "mdp3", true, "PP3.png");

        HashMap<String, Personne> personnes = new HashMap<String, Personne>();
        personnes.put(personne1.getId(), personne1);
        personnes.put(personne2.getId(), personne2);
        personnes.put(personne3.getId(), personne3);

        Utilisateurs users1 = new Utilisateurs(personnes);
        users1.serialize(path);

        Utilisateurs users2 = new Utilisateurs();
        users2.deserialize(path);

        System.out.println(users2.getUsers().get("0002").getPseudo());
    }
}


