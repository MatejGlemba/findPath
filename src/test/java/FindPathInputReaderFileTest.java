import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FindPathInputReaderFileTest {
    @Test
    void findBorders(){
       String fileName = "testFile.txt";
        BufferedReader bufferedReader = readFile(fileName);
        int c;
        int rows=0;
        int columns=0;
        while(true) {
            try {
                if (((c = bufferedReader.read()) == -1))
                    break;
                if ((char) c == '\n') {
                    rows++;
                    columns = 0;
                    continue;
                }
                columns++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        columns--;

        assertEquals(2,rows);
        assertEquals(2,columns);
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