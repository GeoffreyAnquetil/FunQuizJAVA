import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileScan fileScan = new FileScan("./src/testfile.txt");
        fileScan.printLineByLine();
        ArrayList<String[]> test = fileScan.toTab();

        System.out.println();

        System.out.println(fileScan.getData("IsAdmin","0001"));
        System.out.println(fileScan.getData("Pseudo","0002"));
        System.out.println(fileScan.getData("Caca","0001"));
        System.out.println(fileScan.getData("IsAdmin","0003"));
    }
}