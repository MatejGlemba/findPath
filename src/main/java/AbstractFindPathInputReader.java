import java.util.*;

class AbstractFindPathInputReader {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int ROAD = 0;
    static final int WALL = 1;
    static final int START = 2;
    static final int EXIT = 3;
    private static final int PATH = 4;

    int[][] maze;
    boolean[][] visited;
    int columns;
    int rows;
    Node start;
    Node end;

    private int getHeight() {
        return maze.length;
    }

    private int getWidth() {
        return maze[0].length;
    }

    private Node getEntry() {
        return start;
    }

    private boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    private boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    private boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    private boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    private void setVisited(int row, int col) {
        visited[row][col] = true;
    }

    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth();
    }

    List<Node> findPath() {
        LinkedList<Node> nextToVisit = new LinkedList<>();
        Node start = getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Node cur = nextToVisit.remove();

            if (!isValidLocation(cur.getX(), cur.getY()) || isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (isWall(cur.getX(), cur.getY())) {
                setVisited(cur.getX(), cur.getY());
                continue;
            }

            if (isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Node coordinate = new Node(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                setVisited(cur.getX(), cur.getY());
            }
        }
        return Collections.emptyList();
    }

    private List<Node> backtrackPath(Node cur) {
        List<Node> path = new ArrayList<>();
        Node iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }

    void backtrackPrint(List<Node> list){
        ListIterator listIterator = list.listIterator(list.size());
        Node cur,prev = null;
        StringBuilder builder = new StringBuilder();
        while(listIterator.hasPrevious())
        {
            cur = (Node)listIterator.previous();

            if(prev==null) {
                prev = cur;
                continue;
            }
            if(cur.getX()>prev.getX() && cur.getY()==prev.getY())
                builder.append("d,");
            if(cur.getX()<prev.getX() && cur.getY()==prev.getY())
                builder.append("u,");
            if(cur.getX()==prev.getX() && cur.getY()>prev.getY())
                builder.append("r,");
            if(cur.getX()==prev.getX() && cur.getY()<prev.getY())
                builder.append("l,");
            prev = cur;
        }
        builder.setLength(builder.length()-1);
        System.out.println("Path:");
        System.out.println(builder.toString());
    }

    void printPath(List<Node> path) {
        int[][] tempMaze = Arrays.stream(maze).map(int[]::clone).toArray(int[][]::new);
        for (Node node : path) {
            if (isStart(node.getX(), node.getY()) || isExit(node.getX(), node.getY())) {
                continue;
            }
            tempMaze[node.getX()][node.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    private String toString(int[][] maze) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] == ROAD) {
                    result.append('.');
                } else if (maze[row][col] == WALL) {
                    result.append('#');
                } else if (maze[row][col] == START) {
                    result.append('S');
                } else if (maze[row][col] == EXIT) {
                    result.append('X');
                } else {
                    result.append('C');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}
