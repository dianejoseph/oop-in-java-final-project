package levels;

public class Level1 implements Level{
    // public static void main(String args[]){
        //easy lvl
        // 24 numbers given
        private final int[][] solution = {
        
            {6,3,2,5,7,4,1,8,9},
            {8,5,1,3,9,6,7,2,4},
            {7,9,4,8,2,1,5,3,6},
            {9,1,8,4,3,7,2,6,5},
            {2,4,6,1,5,8,9,7,3},
            {5,7,3,2,6,9,4,1,8},
            {4,8,7,9,1,3,6,5,2},
            {1,2,9,6,8,5,3,4,7},
            {3,6,5,7,4,2,8,9,1}
        
    };

    private final int[][] puzzle = new int[9][9];

    private static final int[][] GIVENS = {{0,1}, {1,3}, {2,5}, {3,7}, {4,0},{8,8},{5,3},{0,6},{7,3},{1,0},{0,4},{6,8},{5,5},{6,4},{7,0}, {3,3}, {4,4},{6,6}, {5,7}, {2,2,},{1,1}};

    @Override
    public void setLevel() {
        for (int i = 0; i < GIVENS.length; i++) {
            int[] cell = GIVENS[i];
            int row = cell[0];
            int col = cell[1];
            int value = solution[row][col];
            puzzle[row][col] = value;
        }
    }
    
    @Override
    public int[][] getPuzzle() {
        return puzzle;
    }
    @Override
    public int[][] getSolution() {
        return solution;
    }

    // }
}