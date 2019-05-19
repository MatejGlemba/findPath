import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
class AbstractFindPathInputReaderTest {

    @Test
    void wholeSolutionWithReaderFile(){
        /*
        output:
        . . . . # C C C .
        . . S C C C # C .
        . . . . # # # X .
         */
        String fileName = "testFile.txt";
        FindPathInputReaderFile findPathInputReaderFile = new FindPathInputReaderFile();
        findPathInputReaderFile.findBorders(fileName);
        findPathInputReaderFile.readInputFile(fileName);
        List<Node> path = findPathInputReaderFile.findPath();
        findPathInputReaderFile.printPath(path);
        findPathInputReaderFile.backtrackPrint(path);
        int nodeCounter = 0;
        for (Node node: path) {
            if(nodeCounter==0) {
                assertEquals(2, node.getX());
                assertEquals(2, node.getY());
            }
            else if(nodeCounter==1) {
                assertEquals(1, node.getX());
                assertEquals(2, node.getY());
            }
            else if(nodeCounter==2) {
                assertEquals(1, node.getX());
                assertEquals(1, node.getY());
            }
            else if(nodeCounter==3) {
                assertEquals(0, node.getX());
                assertEquals(1, node.getY());
            }
            else if(nodeCounter==4) {
                assertEquals(0, node.getX());
                assertEquals(0,node.getY());
            }
            nodeCounter++;
        }
    }

    @Test
    void readInputFile(){
        int wallCounter = 0;
        int roadCounter = 0;
        int startCounter = 0;
        int endCounter = 0;
        String fileName = "testFile.txt";
        BufferedReader bufferedReader =  readFile(fileName);
        int c;
        while(true) {
            try {
                if (((c = bufferedReader.read()) == -1))
                    break;
                if((char)c == '#'){
                   wallCounter++;
                }
                else if((char)c == 'S') {
                   startCounter++;
                }
                else if((char)c == 'X') {
                  endCounter++;
                }
                else if((char)c == '.'){
                    roadCounter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assertEquals(1,startCounter);
        assertEquals(1,endCounter);
        assertEquals(2,wallCounter);
        assertEquals(5,roadCounter);
    }
    @Test
    BufferedReader readFile(String fileName){
        BufferedReader bufferedReader = null;
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

}