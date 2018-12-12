package be.unamur.info.algo2.Problem2;

public class Grid {
    private char[][] matrix;

    /**
     * Constructeur de la Classe Grid.
    /*
     
    /* 
    @ requires matrix != null && size > 0 && size < Integer.MAX_VALUE && matrix.length == size
    @ ensures (\forall int i;
    @                 0 <= i && i < sizeOfGrid[0];
    @                 this.matrix[i] == gridLines[i].toCharArray());
     */ 

    public Grid(int[] sizeOfGrid, String[] gridLines) {
        this.matrix = new char[sizeOfGrid[0]][sizeOfGrid[1]];
        for(int i = 0; i < sizeOfGrid[0]; i++) {
            this.matrix[i] = gridLines[i].toCharArray();
        }
    }
    
    /**
     * Algorithme dynamique
  	 *
    /*
     @ public normal_behaviour
     @ requires matrix != null && matrix.length >=0;
     @ assignable max;
     */

    public int getBestPath() {
    	int m = matrix.length;
    	int n = matrix[0].length;

    	//@ ensures n == 0 && m= 0 ==> \return 0;

    	if (m == 0 || n == 0) return 0;

    	//@assert matrix.length > 0 && matrix[0].length >0 ;
    	//@assignable beforeL;
    	//@assignable beforeC;
    	//@assignable current;
    	//@assignable b;
    	   	
    	    	
    	int [][] maxPath = new int [m][n];
    	int o = 1;
    	int beforeL = 0; 
    	int beforeC = 0;
    	int current;
    	int max = 0;
        //@ requires max == 0;
    	
    	// initialisation premiere ligne
    	maxPath[0][0] = getCellAsInt(0,0);
    	max = maxPath[0][0];
    	
    	//@ ensures max <= 0 ==> \result == 0; 
    	
    	if (max < 0) return 0;
    	
        
    	// Initialisation de la première ligne de la structure de donnée dynamique    	
  /*@
   @
   @ loop_invariant j<=1 && j < matrix[0].length ;
   @ loop_invariant maxPath[0][j-1] >= 0 && matrix[0][j] == 'T' ==> maxPath[0][j] == maxPath[0][j-1] + 1;
   @ loop_invariant maxPath[0][j-1] >= 0 && matrix[0][j] == '#' ==> maxPath[0][j] == -1;
   @ loop_invariant maxPath[0][j-1] >= 0 && matrix[0][j] == '0' ==> maxPath[0][j] == maxPath[0][j-1];
   @ increases j+1 ;
   @*/
	
    	for (int j=1; j < n; j++)
    	{
    		beforeL= maxPath[0][j-1];
    		current = getCellAsInt(0,j);
    		//beforeC = getCellAsInt(0,j);
    		if (beforeL != -1 && current != -1) maxPath[0][j]=beforeL+current;
    		else maxPath[0][j]= -1;
    		max = Math.max(max, maxPath[0][j]);
    	}
    	
		//Initialisation du reste de la structure de donnée dynamique 
        /*@
        @
        @ loop_invariant i=1 && i<matrix.length && j=0 && j< matrix[0].length;
        @ loop_invariant i%2 == 0 && (maxPath[i-1][0] == -1 || matrix[i][0] == '#') ==> maxPath[i][0] == -1 ;
        @ loop_invariant i%2 == 0 && matrix[i][0] == '0' ==> maxPath[i][0] == maxPath [i-1][0] ;
        @ loop_invariant i%2 == 0 && matrix[i][0] == 'T' ==> maxPath[i][0]] == maxPath [i-1][0] +1 ;
        @ 
        @ loop_invariant i%2 == 1 && (maxPath[i-1][matrix[0].length-1] == -1 || matrix[i][matrix[0].length-1]=='#') ==> maxPath[i][matrix[0].length-1] == -1 ;
        @ loop_invariant i%2 == 1 && matrix[i][matrix[0].length-1] == '0' ==> maxPath[i][matrix[0].length-1] == maxPath [i-1][matrix[0].length-1] ;
        @ loop_invariant i%2 == 1 && matrix[i][matrix[0].length-1] == 'T' ==> maxPath[i][matrix[0].length-1] == maxPath [i-1][matrix[0].length-1] +1 ;    
        @
        @ loop_invariant i%2 == 0 && j >0 && ((maxPath[i-1][j] == -1 && mathPath[i][j-1] == -1)|| matrix[i][j] == '#') ==> maxPath [i][j] == -1 ;
        @ loop_invariant i%2 == 0 && j >0 && matrix[i][j] == '0' ==> maxPath[i][j] == Math.max(maxPath [i-1][j],maxPath [i][j-1]) ;
        @ loop_invariant i%2 == 0 && j >0 && matrix[i][j] == 'T' ==> maxPath[i][j]] == Math.max(maxPath [i-1][j],maxPath [i][j-1]) +1 ;
        @
        @ loop_invariant i%2 == 1 && j < matrix[0].length && ((maxPath[i-1][j] == -1 && maxPath[i][j+1] )|| matrix[i][j] == '#') ==> maxPath[i][j] == -1 ;
        @ loop_invariant i%2 == 1 && j < matrix[0].length && matrix[i][j] == '0' ==> Math.max(maxPath [i-1][j],maxPath [i][j+1]) ;
        @ loop_invariant i%2 == 1 && j < matrix[0].length && matrix[i][j] == 'T' ==> Math.max(maxPath [i-1][j],maxPath [i][j+1]) +1 ; 
        @ 
        @ loop_invariant max == (\max int i,j ; 0 <= i ; i <= matrix.length && 0 <= j && j <= matrix[0].length ; maxPath[i][j]) ;
        @
        @ increases i+1 ;
        @*/

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

    /**
     *  ...
  	 */
    
    /*@ public normal_behaviour
     *@ requires true;
     *@ assignable max;
     *@ ensures \result == maxPathFrom(0,0,1);
     */

    public int getNaiveBestPath() {
    	int max = max_pathFrom(0,0,1);
    	return max;
    }
    
    /**
     * Approche naïve, Solution récursive 
  	 */
    
    /*
     * @ requires row >=0 && row <= matrix.length && col <=0 && col <= matrix[0].length ;
     * @ requires o == 
     * @ assignable max;
     * @
     * @ public normal_behaviour
     * @ requires matrix[row][col] == '#' ;
     * @ ensures \result == 0;
     * @
     * @ also
     * @ public normal_behaviour
     * @ requires (matrix[row][col] == 'T'|| matrix[row][col] == '0'); // From left to right
     * @ requires i%2 ==> 0==1 && i%2 == 1 ==> o == -1 ;
     * @ ensure \result == Math.max(getCellAsInt(i,j) + max_pathFrom(row-1,col,o), getCellAsInt(i,j) + max_pathFrom(row,col-o,o) ;
     * 
     * @ ensures \result == max;
     */
                    
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

    /**
     * Convertit le contenu de la cellule en integer
  	 */

    /*
     * @ assignable \nothing;
     * @ requires matrix != null;
     * @ normal_behaviour
     * @ requires (row < 0 || row > matrix.length) || (col < 0 || col > matrix[0].length) || (matrix[row][col] == '#') ;
     * @ ensures \result == -1;
     * @
     * @ also
     * @ requires (row > 0 && row < matrix.length) && (col < 0 && col > matrix[0].length) && (matrix[row][col] == 'T') ;
     * @ ensures \result == 1;
     * @
     * @ also
     * @ requires (row > 0 && row < matrix.length) && (col < 0 && col > matrix[0].length) && (matrix[row][col] == '0') ;
     * @ ensures \result == 0; 
     */

	private int getCellAsInt(int row, int col) {
		if (row<0 || row >= matrix.length || col<0 || col >= matrix[0].length || matrix[row][col] == '#') return -1;
		else if (matrix[row][col] == '0') return 0;
		else return 1;
	}   
}
