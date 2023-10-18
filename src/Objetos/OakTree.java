package Objetos;

import java.awt.Graphics;
import java.awt.Image;

public class OakTree extends Object {
    private Image Image;
    
    private int treeHeight;
    private int treeWidth;
    private int treeX;
    private int treeY;
    public OakTree(int treeX, int treeY, int treeWidth, int treeHeight, Image Image) {
        // Constructor con parámetros
        super(treeX, treeY, treeWidth, treeHeight, Image);
        this.Image = Image;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
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

    public void draw(Graphics g) {
        // Dibuja la imagen del árbol en lugar de un rectángulo
        g.drawImage(Image, getX(), getY(), getWidth(), getHeight(), null);
    }

}
