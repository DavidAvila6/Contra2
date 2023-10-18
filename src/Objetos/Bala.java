package Objetos;
import java.awt.Color;

public class Bala extends Object {

    private int x; // Posición en el eje X
    private int y; // Posición en el eje Y
    private int width; // Ancho de la bala
    private int height; // Altura de la bala
   

    public Bala(int x, int y, int width, int height) {
        super(x, y, width, height);     
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        
    }
    

    public void mover(int velocidad, int caidaAjuste) {
        // Mueve la bala hacia la derecha (o en la dirección que desees)
        x += velocidad;
        if(x>100){
            y+=1;
            if(x>250){
                y+=1;
                if(x>400){
                    y+=1;
                }
            }          
        }
          
    }

    // Métodos Getter y Setter para acceder a las propiedades de la bala

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
