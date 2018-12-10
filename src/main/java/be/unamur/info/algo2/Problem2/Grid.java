package be.unamur.info.algo2.Problem2;

public class Grid {
    private char[][] matrix;

    public Grid(int[] sizeOfGrid, String[] gridLines) {
        this.matrix = new char[sizeOfGrid[0]][sizeOfGrid[1]];
        for(int i = 0; i < sizeOfGrid[0]; i++) {
            this.matrix[i] = gridLines[i].toCharArray();
        }
    }
    
    /*@
     * requires 		
     * 		\forall int i,j ; 0 <=i && i < matrix.length && 0 <= j && j < matrix[0].length; matrix[i][j] = 'T' || matrix[i][j] = '0' || matrix[i][j] = '#'  
     * requires int m == matrix.length;
     * requires int n == matrix[0].length;
     * requires int beforeL == 0 && int beforeC == 0;
     * requires int o == 1; (parcourt vers la doite);
     * requires int [][] maxPaths[][]  intitalisé à 0.
     * invariant 
     * 		\forAll int i,j; 0 <=i && i < m && 0 <= j && j < n;
     * 			(matrix[][] == '#') implies currentCellAsInt(i,j)=-1  	
     * 			(matrix[][] == '0') implies currentCellAsInt(i,j)=0  	
     * 			(matrix[][] == 'T') implies currentCellAsInt(i,j)=1
     * 		forall i<0 || i>=m && j<0 || j>=n; currentCellAsInt(i,j) =-1  	
     * 
     * 		if (currentCellAsInt(0,0)<0) maxPath[0][0] = -1 else maxPath[0][0]=currentCellAsInt(0,0)
     * 			\forAll 0<j && j<n; 
     * 				(currentCellAsInt(0,j) < 0 || currentCellAsInt(0,j-1) < 0) implies maxPath[0][j]=-1 ;
     * 				(currentCellAsInt(0,j) >= 0 && currentCellAsInt(0,j-1) >= 0) implies maxPath[0][j]=maxPath[0][j-1] + currentCellAsInt(0,j);
     * 
     * 			\forAll i=1 && i<m && i%2 == 0 && j=0 && j < n-1; 
     * 				if (currentCellAsInt(i,j) < 0 || ((currentCellAsInt(i-1,j) && (currentCellAsInt(i,j-1)) maxPath[i][j]=-1 
     * 						else maxPath[i][j]=currentCellAsInt(i,j) + max(maxPath[i-1][j],maxPath[i][j-1];
     * 			\forAll i=1 && i<m && i%2 == 1 && j=1 && j <= n-1; 
     * 				if (currentCellAsInt(i,j) < 0 || ((currentCellAsInt(i-1,j) && (currentCellAsInt(i,j+1)) maxPath[i][j]=-1 
     * 						else maxPath[i][j]=currentCellAsInt(i,j) + max(maxPath[i-1][j],maxPath[i][j+1];
     * 				 
     * ensure \results == MAX(maxPath[m][n]);
    @*/ 

    public int getBestPath() {
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int [][] maxPath = new int [m][n];
    	int o = 1;
    	int beforeL = 0; 
    	int beforeC = 0;
    	int max = 0;
    	// initialisation premiere ligne
    	maxPath[0][0] = getCellAsInt(0,0);
    	max = maxPath[0][0];
    	if (max < 0) return 0;
    	for (int j=1; j < n; j++)
    	{
    		if (getCellAsInt(0,j) < 0 || getCellAsInt(0,j-1) < 0) maxPath[0][j]= -1;
    		else maxPath[0][j]= maxPath[0][j-1]+getCellAsInt(0,j);
    		max = Math.max(max, maxPath[0][j]);
    	}
    	
    	for (int i=1; i< m; i++) {
    		if (i%2 == 0) { //parcourt vers la droite
        		if (getCellAsInt(i,0) < 0 || getCellAsInt(i-1,0) < 0) maxPath[i][0]= -1;
        		else maxPath[i][0]= maxPath[i-1][0]+getCellAsInt(i,0);
        		max = Math.max(max, maxPath[i][0]);    			
    			for (int j=1;j<=n-1;j++) {
    			     if ((getCellAsInt(i,j) < 0) ||(getCellAsInt(i-1,j)<0 && getCellAsInt(i,j-1)<0)) maxPath[i][j]=-1; 
    			     else maxPath[i][j]=getCellAsInt(i,j) + Math.max(maxPath[i-1][j],maxPath[i][j-1]);
       			     max = Math.max(max, maxPath[i][j]);  
    				
    				
    			}
    		}
    		    		
    		else { // parcourt vers la gauche
        		if (getCellAsInt(i,n-1) < 0 || getCellAsInt(i-1,n-1) < 0) maxPath[i][n-1]= -1;
        		else maxPath[i][n-1]= maxPath[i-1][n-1]+getCellAsInt(i,n-1);
        		max = Math.max(max, maxPath[i][n-1]);    			
        		for (int j=n-2;j>=0;j--) {
       			     if ((getCellAsInt(i,j) < 0) ||(getCellAsInt(i-1,j)<0 && getCellAsInt(i,j+1)<0)) maxPath[i][j]=-1; 
       			     else maxPath[i][j]=getCellAsInt(i,j) + Math.max(maxPath[i-1][j],maxPath[i][j+1]);
       			     max = Math.max(max, maxPath[i][j]);  
    			}
    			
    		}
    	}
    		
    	
    	for (int i=0;i<m;i++) {
    		System.out.print("Ligne ["+i+"]"+"=");    		    	
    		for (int j=0;j<n;j++) System.out.print(maxPath[i][j]+ " ");
    	    System.out.println();
    	}
    	for (int i=0;i<m;i++) {
    		System.out.print("Ligne ["+i+"]"+"=");    		    	
    		for (int j=0;j<n;j++) System.out.print(matrix[i][j]+ " ");
    	    System.out.println();
    	}

    	System.out.println("Max = "+max);
    	return max;
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
        	if (row<0 || row >= matrix.length || col<0 || col >= matrix[0].length || matrix[row][col] == '#') return -1;
        	else if (matrix[row][col] == '0') return 0; 
        	else return 1;
        }

        
}
