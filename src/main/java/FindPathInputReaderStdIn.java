import java.util.Scanner;

class FindPathInputReaderStdIn extends  AbstractFindPathInputReader {
    void readInputFromstdln(){
        int rows,columns;

        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        maze = new int[rows][columns];
        visited = new boolean[rows][columns];

        String c;
        for (int i =0; i < rows; i++){
            for (int j=0;j<columns;j++){
                c = scanner.next();
                if(c.charAt(0) == '#'){
                    maze[i][j] = WALL;
                }
                else if(c.charAt(0) == 'S') {
                    start = new Node(i, j);
                    maze[i][j] = START;
                }
                else if(c.charAt(0) == 'X') {
                    end = new Node(i, j);
                    maze[i][j] = EXIT;
                }
                else if(c.charAt(0) == '.'){
                    maze[i][j] = ROAD;
                }
                else {
                    System.out.println("wrong character, write again !");
                    j--;
                }
            }
        }
    }
}
