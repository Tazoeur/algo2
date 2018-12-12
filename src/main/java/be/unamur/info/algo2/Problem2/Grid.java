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
    	int m = matrix.length;
    	int n = matrix[0].length;
    	if (m == 0 || n == 0) return 0;
    	int [][] maxPath = new int [m][n];
    	int o = 1;
    	int beforeL = 0; 
    	int beforeC = 0;
    	int current;
    	int max = 0;
    	// initialisation premiere ligne
    	maxPath[0][0] = getCellAsInt(0,0);
    	max = maxPath[0][0];
    	if (max < 0) return 0;
    	for (int j=1; j < n; j++)
    	{
    		beforeL= maxPath[0][j-1];
    		current = getCellAsInt(0,j);
    		//beforeC = getCellAsInt(0,j);
    		if (beforeL != -1 && current != -1) maxPath[0][j]=beforeL+current;
    		else maxPath[0][j]= -1;
    		max = Math.max(max, maxPath[0][j]);
    	}
    	
    	for (int i=1; i< m; i++) {
    		if (i%2 == 0) { //parcourt vers la droite
    			current = getCellAsInt(i,0);
    			beforeC = maxPath[i-1][0];
        		if (current < 0 || beforeC < 0) maxPath[i][0]= -1;
        		else maxPath[i][0]= beforeC+current;
        		max = Math.max(max, maxPath[i][0]);    			
    			for (int j=1;j<=n-1;j++) {
        			current = getCellAsInt(i,j);
        			beforeC = maxPath[i-1][j];
        			beforeL= maxPath[i][j-1];
    			    if ((current < 0) ||(beforeL<0 && beforeC<0)) maxPath[i][j]=-1; 
    			     else maxPath[i][j]=current + Math.max(beforeC,beforeL);
       			     max = Math.max(max, maxPath[i][j]);  
    				    				
    			}
    		}
    		    		
    		else { // parcourt vers la gauche
    			current = getCellAsInt(i,n-1);
    			beforeC = maxPath[i-1][n-1];
        		if (current < 0 || beforeC < 0) maxPath[i][n-1]= -1;
        		else maxPath[i][n-1]= beforeC+current;
        		max = Math.max(max, maxPath[i][n-1]);    			
    			
        		for (int j=n-2;j>=0;j--) {
        			current = getCellAsInt(i,j);
        			beforeC = maxPath[i-1][j];
        			beforeL= maxPath[i][j+1];        			
       			    if ((current < 0) || (beforeC<0 && beforeL<0) ) maxPath[i][j]=-1; 
       			     else maxPath[i][j]=current + Math.max(beforeC,beforeL);
       			     max = Math.max(max, maxPath[i][j]);  
    			}
    			
    		}
    	}
    		   	
    	return max;
    }


    public int getNaiveBestPath() {
    	int max = max_pathFrom(0,0,1);
    	return max;
    }
    
        
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

	private int getCellAsInt(int row, int col) {
		if (row<0 || row >= matrix.length || col<0 || col >= matrix[0].length || matrix[row][col] == '#') return -1;
		else if (matrix[row][col] == '0') return 0;
		else return 1;
	}

        
}
