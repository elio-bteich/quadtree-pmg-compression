public class QuadtreePrefab {
    private int[][] tQuadtree;//le tableau a partir duquel sera construit le Quadtree
    private boolean isGoodPrefabs;// vrai si toutes les valeur ont été correctement lue dans le fichier faux sinon 

    public QuadtreePrefab(int[][] T, boolean isGood){
        this.tQuadtree     = T;
        this.isGoodPrefabs = isGood;
    }
    public QuadtreePrefab(){
        this.tQuadtree     = null;
        this.isGoodPrefabs = true;
    }


    public boolean getIsGoodPrefabs(){
        return this.isGoodPrefabs;
    }

    public int[][] getTableau(){
        return this.tQuadtree;
    }

    public void setTableau(int[][] T){
        this.tQuadtree = T;
    }
    public void setIsGoodPrefabs(boolean isGood){
        this.isGoodPrefabs = isGood;
    }
}