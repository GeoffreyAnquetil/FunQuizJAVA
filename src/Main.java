public class Main {
    public static void main(String[] args) {
        FileScan fileScan = new FileScan("./src/testfile.txt");
        fileScan.printLineByLine();
    }
}