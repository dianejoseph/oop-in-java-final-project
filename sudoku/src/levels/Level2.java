package levels;

public class Level2 implements Level{
   //med lvl
    // 5 given
    private final int[][] solution = {
        
        {8,5,9,3,1,6,4,2,7},
        {6,7,2,4,8,9,5,3,1},
        {1,4,3,2,7,5,6,9,8},
        {7,2,6,8,5,3,9,1,4},
        {9,1,8,7,6,4,2,5,3},
        {4,3,5,9,2,1,7,8,6},
        {2,9,7,6,4,8,3,1,5},
        {5,8,1,7,3,2,9,6,4},
        {3,6,4,5,9,1,8,7,2}

    };

    // private final int[][] puzzle = {
    //     {8,0,0,0,1,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,5,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,3,0},
    //     {2,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0}
    // };

    private final int[][] puzzle = new int[9][9];

    private static final int[][] GIVENS = {{0,1}, {1,3}, {2,5}, {3,7}, {4,0},{8,8}};

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
}

