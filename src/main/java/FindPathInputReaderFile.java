import java.io.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

class FindPathInputReaderFile extends AbstractFindPathInputReader {
    private Logger logger = Logger.getAnonymousLogger();

    void findBorders(String fileName){
        BufferedReader bufferedReader = readFile(fileName);
        int c;
        int rows=0;
        int columns=0;
        while(true){
            try{
                if (((c = bufferedReader.read()) == -1))
                    break;
                if((char) c == '\n')
                {
                   rows++;
                   columns = 0;
                    continue;
                }
                columns++;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        columns--;
        this.rows = rows;
        this.columns = columns;
        maze = new int[rows+1][columns+1];
        visited = new boolean[rows+1][columns+1];
    }
    void readInputFile(String fileName){
        int i=0,j=0;
        BufferedReader bufferedReader =  readFile(fileName);
        int c;
        while(true) {
            try {
                if (((c = bufferedReader.read()) == -1))
                    break;
                if((char) c == '\n')
                {
                    i++;
                    j=0;
                    continue;
                }
                if((char)c == '#'){
                    maze[i][j++] = WALL;
                }
                else if((char)c == 'S') {
                    start = new Node(i, j);
                    maze[i][j++] = START;
                }
                else if((char)c == 'X') {
                    end = new Node(i, j);
                    maze[i][j++] = EXIT;
                }
                else if((char)c == '.'){
                    maze[i][j++] = ROAD;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedReader readFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE,"Error with reading file",e);
        }
        return bufferedReader;
    }
}
