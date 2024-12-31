public class SudokuSolver {

    private static final int GRID_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY = 0;

    public static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0 ; column <GRID_SIZE; column++){
                if (board[row][column] == EMPTY){
                    for (int numberToTry = 1; numberToTry <=GRID_SIZE; numberToTry++){
                        if (isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;
                            
                            if (solveBoard(board)){
                                return true;
                            } else {
                                board[row][column] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;  // Board is solved when we reach here
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        //Check row
        for (int i=0; i<GRID_SIZE; i++){
            if (board[row][i] == number){
                return false;
            }
        }

        //Check column
        for (int j=0; j<GRID_SIZE; j++){

                if (board[j][column] == number){
                    return false;
                }
            
        }

        //Check subgrid
        int boxStartRow = row - row % SUBGRID_SIZE;
        int boxStartColumn = column - column % SUBGRID_SIZE;

        for (int i = boxStartRow; i < boxStartRow + SUBGRID_SIZE; i++){
            for (int j = boxStartColumn; j < boxStartColumn + SUBGRID_SIZE; j++){
                if (board[i][j] == number){
                    return false;
                }
            }
        }
        
        return true;
    
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] board = TestCase.getTestCase1(); // Get board from TestCase
        int[][] board2 = TestCase.getTestCase2();


        if (solveBoard(board)) {
            System.out.println("Solved Sudoku board:");
            printBoard(board);
        } else {
            System.out.println("No solution found for board");
        }

        if (solveBoard(board2)) {
            System.out.println("Solved Sudoku board2:");
            printBoard(board);
        } else {
            System.out.println("No solution found for board2");
        }
    }
}
