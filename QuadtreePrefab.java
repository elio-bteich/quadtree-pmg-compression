public class QuadtreePrefab {
    private int[][] tQuadtree; // The array from which the Quadtree will be constructed
    private boolean isGoodPrefabs; // True if all values were correctly read from the file, false otherwise
    private int maxLuminosity; // Maximum luminosity value

    /**
     * Constructor for the QuadtreePrefab class.
     *
     * @param T      The array from which the Quadtree will be constructed.
     * @param isGood True if all values were correctly read from the file, false otherwise.
     * @param maxL   The maximum luminosity value.
     */
    public QuadtreePrefab(int[][] T, boolean isGood, int maxL) {
        this.tQuadtree = T;
        this.isGoodPrefabs = isGood;
        this.maxLuminosity = maxL;
    }

    /**
     * Default constructor for the QuadtreePrefab class.
     */
    public QuadtreePrefab() {
        this.tQuadtree = null;
        this.isGoodPrefabs = true;
        this.maxLuminosity = -1;
    }

    /**
     * Gets the validity of the Quadtree prefabs.
     *
     * @return True if all values were correctly read from the file, false otherwise.
     */
    public boolean getIsGoodPrefabs() {
        return this.isGoodPrefabs;
    }

    /**
     * Gets the array from which the Quadtree will be constructed.
     *
     * @return The array from which the Quadtree will be constructed.
     */
    public int[][] getTableau() {
        return this.tQuadtree;
    }

    /**
     * Sets the array from which the Quadtree will be constructed.
     *
     * @param T The new array to be assigned.
     */
    public void setTableau(int[][] T) {
        this.tQuadtree = T;
    }

    /**
     * Sets the validity of the Quadtree prefabs.
     *
     * @param isGood True if all values were correctly read from the file, false otherwise.
     */
    public void setIsGoodPrefabs(boolean isGood) {
        this.isGoodPrefabs = isGood;
    }

    /**
     * Gets the maximum luminosity value.
     *
     * @return The maximum luminosity value.
     */
    public int getMaxLuminosity() {
        return this.maxLuminosity;
    }

    /**
     * Sets the maximum luminosity value.
     *
     * @param maxL The new maximum luminosity value to be assigned.
     */
    public void setMaxLuminosity(int maxL) {
        this.maxLuminosity = maxL;
    }
}