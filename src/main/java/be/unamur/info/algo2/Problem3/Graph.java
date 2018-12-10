package be.unamur.info.algo2.Problem3;

import java.util.Arrays;

public class Graph {
    private int[] degrees;
    private int[] ordered_degrees;

    public Graph(int[] d) {
        this.degrees = d;
        this.ordered_degrees = null;
    }

    /**
     * This function will check if the given 'this.degrees' array may be assimilated to a serie of simple graph's degree.
     *
     * @return
     */
    public boolean isNaiveGraphic() {
        // at first, check if the degree's sum is even
        if(!this.isSumEven()) {
            return false;
        }
        int i = this.degrees.length - 1;
        while(i > 0) {
            // a O(n log(n)) sort from the documentation
            Arrays.sort(this.degrees);

            int value = 0;
            if(this.degrees[i]<0) {
                return false;
            } else if(this.degrees[i] == 0) {
                i--;
                continue;
            } else {
                value = this.degrees[i];
                this.degrees[i] = 0;
            }
            for(int j = 1; j <= value; j++) {
                if(i-j < 0) {
                    return false;
                }
                this.degrees[i-j]--;
            }
        }
        return true;
    }


    public boolean isGraphic(int index, int size) {
        int value = this.degrees[index];



        // en gros il faut que pour la solution d'naive, il faut générer toutes les façons de prendre les relations de ce sommet parmis les autres sommets disponibles.
        // si on arrive à générer au moins 1 solution, ça veut dire que l'on peut construire un graphe à partir du tableau des degrés
        //
        // par la suite, l'hypothèse gloutonne va être de dire que si on enlève à chaque fois les bouts d'arrete sur le noeud qui a le degré le plus important,
        // bah on arrive d'office à une solution -> vérifier et prouver l'hypothèse gloutonne et c'est le GG !!! :D


        return false;
    }

    private int[] combinatorySolution(int n) {
        if(n == 1) {
            int[] tab = new int[this.degrees.length];
            for(int i = 0; i < this.degrees.length; i++) {
                tab[i] = i;
            }
            return tab;
        }

        int[] ctab = combinatorySolution(n-1);
        int[] tab = new int[this.degrees.length];

        for(int pos = 0; pos < n; pos++) {
            for(int j = 0; j < this.degrees.length; j++) {
                for (int i = 0; i < ctab.length; i++) {
                    int[] elem = new int[n];
                    elem[pos] = j;
                    for (int k = 0; k < n -1; k++) {

                    }
                }
            }
        }
        return tab; // this is a mistake, to make the error-check happy!!!!
    }

    /**
     * This is the Erdös Gallai way to solve this problem
     *
     * @param k
     * @return
     */
    public boolean solveEG(int k) {
        if(k == this.getDegreeSize() && this.ordered_degrees == null) {
            this.ordered_degrees = this.degrees;
            Arrays.sort(this.ordered_degrees);
        }

        if(k < 1) {
            return true;
        }

        int sum_degree = 0;
        for(int i = 0; i < k; i++) {
            sum_degree+=this.ordered_degrees[i];
        }

        if(k == this.ordered_degrees.length && sum_degree %2 != 0) {
            return false;
        }

        int f = k*(k-1);

        int sum_right = 0;
        for(int i = k; i < this.ordered_degrees.length; i++) {
            sum_right+= Math.min(k+1, this.ordered_degrees[i]);
        }

        if(sum_degree > f + sum_right) {
            return false;
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
