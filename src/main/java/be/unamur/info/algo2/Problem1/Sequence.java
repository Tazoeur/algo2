package be.unamur.info.algo2.Problem1;

public class Sequence {
    private String[] shareHolders;

    public Sequence(int size, String[] tab) throws Exception {
        if(tab.length != size) {
            throw new Exception("Error in array size");
        }
        this.shareHolders = tab;
    }

    /**
     * This is the naive algorithm to solve the majority shareHolder problem
     *
     *
     * @return
     */
    public String getNaiveMajorityShareHolder() {
        int length = this.shareHolders.length;
        // The big idea here is to keep track of shareHolder's name that have already been seen and a counter of occurrence of that name
        // the worst case : all the shareHolders are different -> size of arrays is 'length'
        int[] countTab = new int[length];         // the array that will take count of the shareHolders
        String[] nameTab = new String[length];    // the array that will keep memory of already counted shareHolders
        int indice = 0;                           // the index of our 2 arrays (countTab & nameTab)
        Boolean found = false;                    // is the name already stored in nameTab ?
        String name = "";

        for(int i = 0; i < length; i++) {
            name = this.shareHolders[i];            // get the name of the shareholder
            found = false;                          // reset the trigger to false
            for(int j = 0; j < length; j++) {
                indice = j;                         // keep track of index
                // at the beginning, all the element of the nameTab are null
                // if null, the search of the name is done, name not found
                if(nameTab[j] == null) {
                    break;
                }
                if(nameTab[j].equals(name)) {
                    found = true;
                    break;
                }

            }
            // if not found, create an entry in both array
            if(!found) {
                nameTab[indice] = name;
                countTab[indice] = 1;
            } else {
                // if found, increment the counter
                countTab[indice]++;
            }
        }

        // get the index of the maximum value of the countTab array
        int maxAt = 0;
        for (int i = 0; i < length; i++) {
            maxAt = countTab[i] > countTab[maxAt] ? i : maxAt;
        }

        // check if the biggest shareHolder have the majority
        if(countTab[maxAt] > length/2) {
            return nameTab[maxAt];
        } else {
            return "null";
        }
    }

    public String[] getShareHolders() {
        return this.shareHolders;
    }
}
