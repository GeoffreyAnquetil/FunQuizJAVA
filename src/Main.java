import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileScan fileScan = new FileScan("./src/testfile.txt");
        fileScan.printLineByLine();
        ArrayList<String[]> test = fileScan.toTab();

        System.out.println();

        for(String[] ligne : test){
            for(String mot : ligne){
                System.out.print(mot + " / ");
            }
            System.out.println();
        }
    }
}