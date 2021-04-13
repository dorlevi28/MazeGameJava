package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.*;

/**
 * Class maze represents a maze:
 * int[][] body: two-dimensional integer array.
 * int numofrow: number of rows in the maze.
 * int numofcols: number of columns in the maze.
 * Position startPosition: position of the starting point to the maze.
 * Position goalPosition: position of the ending point to the maze.
 *  @author Daniel Goldman and Dor Levy
 *  @version 1.0
 *  @since   2020-15-03
 */

public class Maze implements Serializable {
    private int[][] body;
    private int numofrows;
    private int numofcols;
    private Position startPosition;
    private Position goalPosition;

    /**
     * Constructor method to initiate the maze object.
     *
     * @param rows determines the number of rows in the maze.
     * @param cols determines the number of columns in the maze.
     * @param p1   Position, represents the start Position for the search in the maze.
     * @param p2   Position, represents the goal Position for the exit of the maze.
     */
    public Maze(int rows, int cols, Position p1, Position p2) {
        body = new int[rows][cols];
        this.numofcols = cols;
        this.numofrows = rows;
        this.startPosition = p1;
        this.goalPosition = p2;
    }

    /**
     * This method is a Getter for the number of columns.
     *
     * @return int number of columns in the maze
     */
    public int getNumofcols() {
        return numofcols;
    }

    /**
     * This method is a Getter for the number of rows.
     *
     * @return int number of rows in the maze
     */
    public int getNumofrows() {
        return numofrows;
    }

    /**
     * This method changes the value inside the given maze cell represented by the given parameters:
     *
     * @param row, represents the row of the changed cell
     * @param col, represents the column of the changed cell
     * @param val, the value to be inserted to the cell represented by (row,col)
     */
    public void setCellValue(int row, int col, int val) {
        this.body[row][col] = val;
    }

    /**
     * This method is a getter for the value inside the cell represented by the given parameters:
     *
     * @param row, represents the row of the wanted cell
     * @param col, represents the column of the wanted cell
     * @return int, the value inside the cell represented by (row,col)
     */
    public int getCellValue(int row, int col) {
        return this.body[row][col];
    }

    /**
     * This method returns the starting position of the search in the maze.
     *
     * @return Position, the start Position.
     */
    public Position getStartPosition() {
        return this.startPosition;
    }

    /**
     * This method returns the goal position of the search in the maze.
     *
     * @return Position, the exit Position from the maze.
     */
    public Position getGoalPosition() {
        return this.goalPosition;
    }

    /**
     * This method prints the maze, Start Position is marked with "S", End position is marked with "E".
     */
    public void print() {

        for (int i = 0; i < this.numofrows; i++) {
            for (int j = 0; j < this.numofcols; j++) {

                if (this.startPosition != null && this.startPosition.getRowIndex() == i && this.startPosition.getColumnIndex() == j) {
                    System.out.print("S");
                } else if (this.goalPosition != null && this.goalPosition.getRowIndex() == i && this.goalPosition.getColumnIndex() == j) {
                    System.out.print("E");
                } else {
                    System.out.print(this.body[i][j]);
                }
            }
            System.out.println("");
        }
    }

    /**
     * This method changes the value of the given column with 0, starting from given starting row up to the given end row.
     *
     * @param startRow starting row where we start to change values and climbing upwards
     * @param endRow   ending row where method stops and all column values has been changed to 0
     * @param startCol the number of the column to be changed to 0
     * @return int, the row where the method stops.
     */
    public int setUpColWith0(int startRow, int endRow, int startCol) {
        while (startRow > endRow) {
            this.body[startRow - 1][startCol] = 0;
            startRow--;
        }
        return endRow;
    }

    /**
     * This method changes the values of the given column with 0, starting from given starting row up to the given end row.
     *
     * @param startRow starting row where we start to change values and descending downwards
     * @param endRow   ending row where method stops and all column values has been changed to 0
     * @param startCol the number of the column to be changed to 0
     * @return int, the row where the method stops.
     */
    public int setDownColWith0(int startRow, int endRow, int startCol) {
        while (startRow < endRow) {
            this.body[startRow + 1][startCol] = 0;
            startRow++;
        }
        return endRow;
    }

    /**
     * This method changes the values of the given row with 0, starting from given starting column up to the given end column.
     *
     * @param startRow the number of the row to be changed to 0
     * @param startCol starting column where we start changing values to 0, moving rightwards every iteration
     * @param endCol   ending column where the method stops and all row values has been changed to 0
     * @return int, the row where the method stops.
     */
    public int setRightRowWith0(int startCol, int endCol, int startRow) {
        while (startCol < endCol) {
            this.body[startRow][startCol + 1] = 0;
            startCol++;
        }
        return startCol;
    }

    /**
     * This method changes the values of the given row with 0, starting from given starting column up to the given end column.
     *
     * @param startRow the number of the row to be changed to 0
     * @param startCol starting column where we start changing values to 0, moving leftwards every iteration
     * @param endCol   ending column where the method stops and all row values has been changed to 0
     * @return int, the row where the method stops.
     */
    public int setLeftRowWith0(int startCol, int endCol, int startRow) {
        while (startCol > endCol) {
            this.body[startRow][startCol - 1] = 0;
            startCol--;
        }
        return startCol;
    }

    /**
     * This method is used to make sure that on a randomly generated maze there is a Solution.
     * setting a path of 0 from the starting position to the goal position using the methods above.
     */
    public void MakePath() {

        int startRow = startPosition.getRowIndex();
        int startCol = startPosition.getColumnIndex();
        int endRow = goalPosition.getRowIndex();
        int endCol = goalPosition.getColumnIndex();

        boolean sRowbigger = startRow > endRow;
        boolean sColbigger = startCol > endCol;
        boolean rowsEqual = startRow == endRow;
        boolean colsEqual = startCol == endCol;

        if (sRowbigger) {
            setUpColWith0(startRow, endRow, startCol);
        } else if (rowsEqual) {
        } else {
            setDownColWith0(startRow, endRow, startCol);
        }

        startRow = endRow;
        if (sColbigger) {
            setLeftRowWith0(startCol, endCol, startRow);
        } else if (colsEqual) {
        } else {
            setRightRowWith0(startCol, endCol, startRow);
        }
    }

    /**
     * This method sets the Start and End position of the maze according to all restrictions.
     *
     * @param moves the parameter which determines how many available moves the Start and Goal position need to obligate to.
     */
    public void SetStartandGoal(int moves) {
        Random random = new Random();
        boolean flag = true;
        while (flag) {
            if (startPosition.isEqual(goalPosition)) { //if we got the same positions as both beginning and end
                goalPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else if (!checkonframe(goalPosition)) {//making sure Goal position is on frame
                goalPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else if (!checkonframe(startPosition)) {//making sure Start position is on frame
                startPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else if (onsameline(startPosition, goalPosition)) { //making sure that both positions are not on the same line
                goalPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else if (this.getAvailableMovesCount(startPosition) < moves) { // making sure Start position has at list moves
                startPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else if (this.getAvailableMovesCount(goalPosition) < moves) { // making sure Goal position has at list moves
                goalPosition = new Position(random.nextInt(numofrows), random.nextInt(numofcols));
            } else {
                flag = false;
            }
        }
        this.body[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;
        this.body[goalPosition.getRowIndex()][goalPosition.getColumnIndex()] = 0;
    }

    /**
     * This method makes sure than a given Position is located on one of the edges of the maze (first row/column or last row/column).
     *
     * @param p Position to be checked
     * @return boolean, true if the given position is on one of the edges of the maze, false otherwise.
     */
    public boolean checkonframe(Position p) {
        return p.getRowIndex() == 0 || p.getRowIndex() == numofrows - 1 || p.getColumnIndex() == 0 || p.getColumnIndex() == numofcols - 1;
    }

    /**
     * This method checks if two given positions are on the same row or the same column.
     *
     * @param p1 Position to be checked
     * @param p2 Position to be checked
     * @return boolean, true if the two positions are on the same column\row, false otherwise.
     */
    public boolean onsameline(Position p1, Position p2) {
        return p1.getColumnIndex() == p2.getColumnIndex() || p1.getRowIndex() == p2.getRowIndex();
    }

    /**
     * This method returns the number of available moves from a given position.
     *
     * @param p represents the Position to move from
     * @return int, number of available moves.
     */
    public int getAvailableMovesCount(Position p) {

        int c = 0;
        int row = p.getRowIndex();
        int col = p.getColumnIndex();

        // left neighbor
        if (row < this.numofrows && col - 1 >= 0 && this.body[row][col - 1] == 0) {
            c++;
        }
        //upper neighbour
        if (col < this.numofcols && row - 1 >= 0 && this.body[row - 1][col] == 0) {
            c++;
        }
        //right neighbor
        if (row < this.numofrows && col + 1 < this.numofcols && this.body[row][col + 1] == 0) {
            c++;
        }
        //lower neighbor
        if (col < this.numofcols && row + 1 < this.numofrows && this.body[row + 1][col] == 0) {
            c++;
        }
        return c;
    }

        public byte[] toByteArray() {
        byte[] Maze = new byte[13 + (numofrows * numofcols)];
            Maze[0]= (byte) (numofrows/255);
            Maze[1]= (byte) (numofrows%255);

            Maze[2]= (byte) (numofcols/255);
            Maze[3]= (byte) (numofcols%255);

            Maze[4]= (byte) (startPosition.getRowIndex()/255);
            Maze[5]= (byte) (startPosition.getRowIndex()%255);

            Maze[6]= (byte) (startPosition.getColumnIndex()/255);
            Maze[7]= (byte) (startPosition.getColumnIndex()%255);

            Maze[8]= (byte) (goalPosition.getRowIndex()/255);
            Maze[9]= (byte) (goalPosition.getRowIndex()%255);

            Maze[10]= (byte) (goalPosition.getColumnIndex()/255);
            Maze[11]= (byte) (goalPosition.getColumnIndex()%255);

            Maze[12] = ((byte)(numofrows*numofcols%8));

        int k = 13;
        for (int i = 0; i < numofrows; i++) {
            for (int j = 0; j < numofcols; j++) {
                Maze[k] = (byte) body[i][j];
                k++;
            }
        }
        return Maze;
    }

        public Maze(byte[] arr) {

        this.numofrows = arr[0]*255 + arr[1];
            if (arr[1] < 0)
                this.numofrows +=256;
        this.numofcols = arr[2]*255 + arr[3];
            if (arr[3] < 0)
                this.numofcols +=256;
        this.body = new int[numofrows][numofcols];
        int start_pos_row = arr[4]*255 + arr[5];
            if (arr[5] < 0)
                start_pos_row +=256;
        int start_pos_col = arr[6]*255 + arr[7];
            if (arr[7] < 0)
                start_pos_col +=256;
        this.startPosition = new Position(start_pos_row,start_pos_col);
        int goal_pos_row = arr[8]*255 + arr[9];
            if (arr[9] < 0)
                goal_pos_row +=256;
        int goal_pos_col= arr[10]*255 + arr[11];
            if (arr[11] < 0)
                goal_pos_col +=256;
        this.goalPosition = new Position(goal_pos_row,goal_pos_col);

        int k = 13;
        for (int i = 0; i < numofrows; i++) {
            for (int j = 0; j < numofcols; j++) {
                this.body[i][j] = (int)arr[k];
                k++;
            }
        }
    }

    /*
    this function checks if the mazes are equal by comparing the byteArray of each maze
     */
    public boolean mazeArrayEquals(Maze m2){
        return (Arrays.equals(this.toByteArray(),m2.toByteArray()));
    }


//    public void NumToBytes(int startIndex, int num, byte[] arr) {
//        int i = startIndex;
//        while (num >= 0) {
//            if (num - 255 >= 0) {
//                arr[i] = (byte) 255;
//            } else {
//                arr[i] = (byte) num;
//            }
//            num -= 255;
//            i++;
//        }
//    }
//
//    public byte[] toByteArray() {
//        byte[] Maze = new byte[24 + (numofrows * numofcols)];
//        NumToBytes(0, numofrows, Maze);
//        NumToBytes(4, numofcols, Maze);
//        NumToBytes(8, startPosition.getRowIndex(), Maze);
//        NumToBytes(12, startPosition.getColumnIndex(), Maze);
//        NumToBytes(16, goalPosition.getRowIndex(), Maze);
//        NumToBytes(20, goalPosition.getColumnIndex(), Maze);
//
//        int k = 24;
//        for (int i = 0; i < numofrows; i++) {
//            for (int j = 0; j < numofcols; j++) {
//                Maze[k] = (byte) body[i][j];
//                k++;
//            }
//        }
//        return Maze;
//    }
//
//    public static int BytesToNum(int startIndex, int count, byte[] arr) {
//        int i = startIndex;
//        int num = 0;
//        while (count>= 0) {
//            num += arr[i];
//            count--;
//            i++;
//        }
//        return num;
//    }
//    public Maze(byte[] arr) {
//
//        this.numofrows = BytesToNum(0, 4, arr);
//        this.numofcols = BytesToNum(4, 4, arr);
//        this.body = new int[numofrows][numofcols];
//        int start_pos_row = BytesToNum(8, 4, arr);
//        int start_pos_col = BytesToNum(12, 4, arr);
//        this.startPosition = new Position(start_pos_row,start_pos_col);
//        int goal_pos_row = BytesToNum(16, 4, arr);
//        int goal_pos_col= BytesToNum(20, 4, arr);
//        this.goalPosition = new Position(goal_pos_row,goal_pos_col);
//
//        int k = 24;
//        for (int i = 0; i < numofrows; i++) {
//            for (int j = 0; j < numofcols; j++) {
//                this.body[i][j] = (int)arr[k];
//                k++;
//            }
//        }
//    }
}





