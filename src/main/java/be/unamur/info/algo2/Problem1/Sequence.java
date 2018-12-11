package be.unamur.info.algo2.Problem1;

public class Sequence {
    private /*@ spec_public @*/ String[] shareHolders;

    /**
     * Constructor of the Sequence Class.
     * Assignation of the tab param to this.shareHolder with some verifications
     *
     * @param size          the size of the array that is passed, just to double check
     * @param tab           the array on which we will base all our logic.
     *                      This is the sequence S that contains all different shareHolders
     * @throws Exception    if array is null
     *                      if size is negative or zero
     *                      if array.length and size are different
     */
    /*@
      @ public normal_behaviour
      @     requires tab != null && size > 0 && size < Integer.MAX_VALUE && tab.length == size;
      @     ensures (\forall int i;
      @                 0 <= i && i < size;
      @                 this.shareHolders[i] == tab[i]);
      @ also
      @ public exceptional_behaviour
      @     requires tab == null;
      @     signals_only Exception;
      @ also
      @ public exceptional_behaviour
      @     requires size <= 0;
      @     signals_only Exception;
      @ also
      @ public exceptional_behaviour
      @     requires size > 0  && tab.length != size;
      @     signals_only Exception;
      @*/
    public Sequence(int size, String[] tab) throws Exception {
        if(tab == null) {
            throw new Exception("Array cannot be null");
        }
        if(size <= 0) {
            throw new Exception("Array size must be greater than 0");
        }
        if(tab.length != size) {
            throw new Exception("Error in array size");
        }
        this.shareHolders = tab;
    }

    /**
     * Return the size of the array
     */
    /*@
      @ public normal_behaviour
      @     requires true;
      @     assignable \nothing;
      @     ensures \result == this.shareHolders.length;
      @
      @*/
    public int getSize() {
        return this.shareHolders.length;
    }


    /**
     * Return the array of shareHolders
     *
     */
    /*@
      @ public normal_behaviour
      @     requires true;
      @     assignable \nothing;
      @     ensures (\forall int i; i >= 0 && i < this.shareHolders.length; \result[i] == this.shareHolders[i]);
      @*/
    public String[] getShareHolders() {
        return this.shareHolders;
    }


    /**
     * Naive algorithm to solve the majority shareHolder problem
     *
     */
    /*@ public normal_behaviour
      @     requires true;
      @     assignable \everything
      @     ensures (
      @                 \result != null
      @                 &&
      @                 (\exists int k; 0 <= k && k < this.shareHolders.length; \result == this.shareHolders[k])
      @                 &&
      @                 (\sum int i; 0 <= i && i < this.shareHolders.length && \result == this.shareHolders[i]; 1) > this.shareHolders.length/2
      @             ) ||
      @             (
      @                 result == null
      @                 &&
      @                 (\forall int i; 0 <= i && i < this.shareHolders.length; (\sum int j; 0 <= j && j < this.shareHolders.length && this.shareHolders[i] == this.shareHolders[j]; 1) <= this.shareHolders.length/2)
      @             )
      @*/
    public String getNaiveMajorityShareHolder() {
        int length = this.shareHolders.length;
        // The big idea here is to keep track of shareHolder's name that have already been seen and a counter of occurrence of that name
        // the worst case : all the shareHolders are different -> size of arrays is 'length'
        int[] countTab = new int[length];         // the array that will take count of the shareHolders
        String[] nameTab = new String[length];    // the array that will keep memory of already counted shareHolders
        int indice = 0;                           // the index of our 2 arrays (countTab & nameTab)
        Boolean found = false;                    // is the name already stored in nameTab ?
        String name = "";

        /*@
          @ loop_invariant 0 <= i && i < length
          @ decreases length - i
          @*/
        for(int i = 0; i < length; i++) {
            name = this.shareHolders[i];            // get the name of the shareholder
            found = false;                          // reset the trigger to false
            /*@
              @ loop_invariant 0 <= j && i < length
              @ decreases length - j
              @*/
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

        /*@
          @ loop_invariant 0 <= i && i <= length;
          @ loop_invariant (\forall int l; 0 <= l && l < length && countTab[l] != null; countTab[l] <= arr[maxAt]);
          @ decreases length - i
          @*/
        for (int i = 0; i < length; i++) {
            if (countTab[i] > countTab[maxAt]) {
                maxAt = i;
            }
        }

        // check if the biggest shareHolder have the majority
        if(countTab[maxAt] > length/2) {
            return nameTab[maxAt];
        } else {
            return null;
        }
    }

    /**
     *
     * ici il faut placer les même pré-post condition que sur getNaiveMajorityShareHolder()
     * on peut rajouter :
     * @ assignable left,right;
     */
    public String getMajorityShareHolder(int left, int right) {
        // DIVIDE
        if(left == right) {
            return this.shareHolders[left];
        }

        int total = right - left + 1; // the number of item in the given interval
        int median = (left + right)/2;

        String majority_left = getMajorityShareHolder(left, median);
        String majority_right = getMajorityShareHolder(median+1, right);

        // CONQUER
        if(majority_left == null && majority_right == null) {
            return null;
        }

        if(majority_left != null && majority_left.equals(majority_right)) {
            return majority_left;
        }

        int count_majority_left = 0;
        int count_majority_right = 0;
        /*@
          @ loop_invariant left <= i && i <= right;
          @ decreases (right - left) - i;
          @*/
        for(int i = left; i <= right; i++) {
            if(this.shareHolders[i].equals(majority_left)) {
                count_majority_left++;
            }
            if(this.shareHolders[i].equals(majority_right)) {
                count_majority_right++;
            }
        }

        if(majority_left != null && count_majority_left > total/2) {
            return majority_left;
        }
        if(majority_right != null && count_majority_right > total/2) {
            return majority_right;
        }

        return null;
    }
}
