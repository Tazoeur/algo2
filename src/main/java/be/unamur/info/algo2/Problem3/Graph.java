package be.unamur.info.algo2.Problem3;

import java.util.Arrays;

public class Graph {
    private int[] degrees;

    public Graph(int[] d) {
        this.degrees = d;
    }

    public int isNaiveGraphic() {
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

    public int isGraphic() {
        return 1;
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

}
