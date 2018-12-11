package be.unamur.info.algo2.Problem3;

import java.util.Arrays;

public class Graph {
    private int[] degrees;
    private int[] ordered_degrees;
    private int[][][][] known_combinaisons;

    public Graph(int[] d) {
        this.degrees = d;
        this.ordered_degrees = null;
        // au maximum, on ne pourra quand même pas prendre tous les éléments du tableau car si le k vaut la taille du tableau c'est faux.
        // et une fois qu'on a enlevé le k (premier élément du tableau), la taille du tableau restant est décrémentée de 1
        int[][][] tmp = new int[this.degrees.length - 1][][];
        this.known_combinaisons = new int[this.degrees.length - 1][this.degrees.length - 1][][];
    }

    private int[][] generateTab(int n) {
        int[][] tab = new int[n][1];
        for(int i = 0; i < n; i++) {
            tab[i][0] = i;
        }
        return tab;
    }

    private int[][] generateCombinaisons(int k, int n) {
        if(this.known_combinaisons[k-1][n-1] != null) {
            return this.known_combinaisons[k-1][n-1];
        }

        int[][] tab = generateTab(n);
        if(k == 1) {
            return tab;
        }

        // on prend toutes les combinaisons pour des k inférieurs à celui que l'on veut
        int[][] combinaisons = generateCombinaisons(k-1, n);

        // petit calcul pour donner la taille du tableau (de tableaux d'entiers)
        int count_combinaison = this.factorial(n)/(this.factorial(k) * this.factorial(n-k));

        // on crée ce nouveau tableau de tableaux d'entiers
        int[][] new_combinaisons = new int[count_combinaison][k];

        int c = 0; // un compteur pour ajouter les nouvelles combinaisons au bon endroit.

        // pour chacune des combinaisons dans le tableau de tableau,
        for(int i = 0; i < combinaisons.length; i++) {

            // ici on a donc à chaque fois affaire à un tableau d'entier qui représente une façon de prendre k indices parmis n
            // on calcule la dernière valeur présente dans la combinaison dans laquelle on se trouve
            int last_value = combinaisons[i][combinaisons[i].length - 1];

            // pour chaque entier entre cette valeur maximale et n,
            for(int j = last_value + 1; j < n; j++) {

                // on va recréer un tableau ordonné qui comprend tous les éléments de la combinaison que l'on traitait
                int[] new_combinaison = new int[combinaisons[i].length + 1];
                for(int l = 0; l < combinaisons[i].length; l++) {
                    new_combinaison[l] = combinaisons[i][l];
                }

                // à laquelle on va rajouter un entier entre l'ancienne valeur maximale et n
                new_combinaison[combinaisons[i].length] = j;

                // finalement on ajoute cette nouvelle combinaison au tableau des nouvelles combinaisons
                new_combinaisons[c] = new_combinaison;
                c++;
            }
        }
        this.known_combinaisons[k-1][n-1] = new_combinaisons;
        return new_combinaisons;
    }

    public int[] getDegree() {
        return this.degrees;
    }

    private int factorial(int n) {
        int r = 1;
        for(int i = 2; i <= n; i++) {
            r = r*i;
        }
        return r;
    }


    /**
     * This function will check if the given 'this.degrees' array may be assimilated to a serie of simple graph's degree.
     *
     * @return
     */
    public int isNaiveGraphic(int[] tab_degrees) {

        int k = tab_degrees[0];

        if(k == 0) {
            if (tab_degrees.length == 1) {
                return 1;
            } else {
                for(int i = 0; i < tab_degrees.length; i++) {
                    if(tab_degrees[i] != 0) {
                        return 0;
                    }
                }
                return 1;
            }
        } else if(k >= tab_degrees.length) {
            return 0;
        }

        int[] newtab = new int[tab_degrees.length -1];
        for(int i = 1; i < tab_degrees.length; i++) {
            newtab[i-1] = tab_degrees[i];
        }
        tab_degrees = newtab.clone();

        int[][] combinaisons = this.generateCombinaisons(k, tab_degrees.length);

        for(int i = 0; i < combinaisons.length; i++) {
            newtab = tab_degrees.clone();

            for(int j = 0; j < combinaisons[i].length; j++) {
                int indice = combinaisons[i][j];
                newtab[indice] = newtab[indice] - 1;
                if(newtab[indice] < 0) {
                    return 0;
                }
            }
            if(this.isNaiveGraphic(newtab) == 1) {
                return 1;
            }
        }

        return 0;
    }


    public int isGraphic(int index, int size) {
        // at first, check if the degree's sum is even
        if(!this.isSumEven()) {
            return 0;
        }
        int i = this.degrees.length - 1;
        while(i > 0) {
            // a O(n log(n)) sort from the documentation
            Arrays.sort(this.degrees);

            int value = 0;
            if(this.degrees[i]<0) {
                return 0;
            } else if(this.degrees[i] == 0) {
                i--;
                continue;
            } else {
                value = this.degrees[i];
                this.degrees[i] = 0;
            }
            for(int j = 1; j <= value; j++) {
                if(i-j < 0) {
                    return 0;
                }
                this.degrees[i-j]--;
            }
        }
        return 1;
    }

    /**
     * This is the Erdös Gallai way to solve this problem
     *
     * @param k
     * @return
     */
    public int solveEG(int k) {
        if(k == this.getDegreeSize() && this.ordered_degrees == null) {
            this.ordered_degrees = this.degrees;
            Arrays.sort(this.ordered_degrees);
        }

        if(k < 1) {
            return 1;
        }

        int sum_degree = 0;
        for(int i = 0; i < k; i++) {
            sum_degree+=this.ordered_degrees[i];
        }

        if(k == this.ordered_degrees.length && sum_degree %2 != 0) {
            return 0;
        }

        int f = k*(k-1);

        int sum_right = 0;
        for(int i = k; i < this.ordered_degrees.length; i++) {
            sum_right+= Math.min(k+1, this.ordered_degrees[i]);
        }

        if(sum_degree > f + sum_right) {
            return 0;
        }

        return solveEG(k-1);
    }

    /**
     *
     * @return
     */
    private boolean isSumEven() {
        int sum = 0;
        for(int i = 0; i < this.degrees.length; i++) {
            sum += this.degrees[i];
        }
        return sum%2 == 0;
    }

    public int getDegreeSize() {
        return this.degrees.length;
    }
}
