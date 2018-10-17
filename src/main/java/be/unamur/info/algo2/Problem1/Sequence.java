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
     * This is the function where the algorithm takes place
     *
     * @return
     */
    public String getMajorityShareHolder() {
        return "Goku";
    }

    public String[] getShareHolders() {
        return this.shareHolders;
    }
}
