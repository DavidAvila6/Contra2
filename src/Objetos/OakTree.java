package Objetos;

import java.util.Random;

public class OakTree extends Tree {
    private int treeHeight;
    private int treeWidth;
    private int treeX;
    private int treeY;

    public OakTree(int treeX, int treeY, int treeWidth, int treeHeight) {
        // Constructor con parámetros
        super(treeX, treeY, treeWidth, treeHeight);
        this.treeHeight = 100;
        this.treeWidth = 50;
        this.treeX = 200;
        this.treeY = 400;

        Random random = new Random();
        this.treeX = random.nextInt(800) + 800; // Ajusta según el tamaño de tu ventana
        this.treeY = 450;
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

}
