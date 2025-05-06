package levels;

public class Level3 implements Level{
        //hard lvl
        // 4 given

    private final int[][] solution = {
        
        {4,1,7,3,6,9,8,2,5},
        {6,3,2,1,8,5,9,4,7},
        {9,5,8,7,2,4,3,1,6},
        {8,6,3,5,7,2,1,9,4},
        {1,7,4,9,3,8,5,6,2},
        {2,9,5,6,4,1,7,8,3},
        {5,8,9,2,1,6,4,7,3},
        {7,2,6,4,9,3,0,5,8},
        {3,4,1,8,5,7,6,2,9}
        
    };
    
    // private final int[][] puzzle = {
    //     {0,1,0,0,0,0,0,0,0},
    //     {0,0,0,1,0,0,0,0,0},
    //     {0,0,0,0,0,4,0,0,0},
    //     {0,0,0,0,0,0,0,9,0},
    //     {1,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,0},
    //     {0,0,0,0,0,0,0,0,9}

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
