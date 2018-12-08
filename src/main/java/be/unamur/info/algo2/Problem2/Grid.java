package be.unamur.info.algo2.Problem2;

public class Grid {
    private char[][] matrix;

    public Grid(int[] sizeOfGrid, String[] gridLines) {
        this.matrix = new char[sizeOfGrid[0]][sizeOfGrid[1]];
        for(int i = 0; i < sizeOfGrid[0]; i++) {
            this.matrix[i] = gridLines[i].toCharArray();
        }
    }

    public int getBestPath() {
        return 1;
    }

    public int getNaiveBestPath() {
    	int max = max_pathFrom(0,0,1);
    	return max;
    }
    
    /*@
     * requires 		
     * 		\forall int i,j ; 0 <=i && i < matrix.length && 0 <= j && j < matrix[0].length; matrix[i][j] = 'T' || matrix[i][j] = '0' || matrix[i][j] = '#'  
     * requires int row == 0 and int col == 0 && int moveH == 1; 
     * invariant 
     * 		(\forall int i,j,o ; 0 <=i && i < matrix.length && 0 <= j && j < matrix[0].length && (o == 1 || o == -1;
     * 				matrix[i][j] == '#' ==> maxTreeFrom(i,j,o) == 0;
     * 		(\forall int i; 0 <=i && i < matrix.length) 		matrix[i][j] != '#' ==> 
     * 				 
     * ensure \results == maxTreeFrom(\old row,\old col,\old moveH);
    @*/ 
         
        
        public int max_pathFrom(int row, int col, int o) {
        	int max = 0;
        	if (this.matrix[row][col] == '#') {
        		return max;
        	}
        	else 
        	{
        		max = getCellAsInt(row,col);
        		int maxL = 0; int maxV = 0;
        		int new_col = col + o;
        		if (new_col >=0 && new_col < matrix[0].length)
        		{
        			maxL = max_pathFrom(row,new_col,o);
        		}
        		int new_row = row+1;
        		int new_o= o*(-1);
        		if ( (new_row < (matrix.length))) {
        			maxV= max_pathFrom(new_row,col,new_o);
        		}
        		
            	max = max + Math.max(maxL,maxV);
            	return max;
        	}
        }

        int getCellAsInt(int row, int col) {
        	if (row<0 || row >= matrix.length || col<0 || col >= matrix[0].length || matrix[row][col] == '0') return 0; 
        	else return 1;
        }

        
}
