import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        if(choice == 1){    // run the program with reading input from StdIn
            FindPathInputReaderStdIn findPathInputReaderStdIn = new FindPathInputReaderStdIn();
            findPathInputReaderStdIn.readInputFromstdln();
            List<Node> path = findPathInputReaderStdIn.findPath();
            findPathInputReaderStdIn.printPath(path);
            findPathInputReaderStdIn.backtrackPrint(path);
        }
        else if(choice == 2){ // run the program with reading input from text file
            String fileName = "inputFile.txt";
            FindPathInputReaderFile findPathInputReaderFile = new FindPathInputReaderFile();
            findPathInputReaderFile.findBorders(fileName);
            findPathInputReaderFile.readInputFile(fileName);
            List<Node> path = findPathInputReaderFile.findPath();
            findPathInputReaderFile.printPath(path);
            findPathInputReaderFile.backtrackPrint(path);
        }
    }
}
