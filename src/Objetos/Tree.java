package Objetos;

public abstract class Tree implements Cloneable {
    private int treeHeight;
    private int treeWidth;
    private int treeX;
    private int treeY;
    
    public Tree(int treeX, int treeY, int treeWidth, int treeHeight) {
        this.treeX = treeX;
        this.treeY = treeY;
        this.treeWidth = treeWidth;
        this.treeHeight = treeHeight;
    }

    
    public int getTreeHeight() {
        return treeHeight;
    }

    public void setTreeHeight(int treeHeight) {
        this.treeHeight = treeHeight;
    }

    public int getTreeWidth() {
        return treeWidth;
    }

    public void setTreeWidth(int treeWidth) {
        this.treeWidth = treeWidth;
    }

    public int getTreeX() {
        return treeX;
    }

    public void setTreeX(int treeX) {
        this.treeX = treeX;
    }

    public int getTreeY() {
        return treeY;
    }

    public void setTreeY(int treeY) {
        this.treeY = treeY;
    }

    @Override
    public Tree clone() throws CloneNotSupportedException {
        return (Tree) super.clone();
    }
}
