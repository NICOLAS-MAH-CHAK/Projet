import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Rond {
    private static int RAYON = 30;
    
   
    private double posX;
    private double posY;

  
    private double vitessX;
    private double vitessY;

  
    private int dpWidth;
    private int dpHeight;

    
    private Image image;

    public Rond(Color color, int dpWidth, int dpHeight) {
        this.dpWidth = dpWidth;
        this.dpHeight = dpHeight;

        
        posX = Math.random() * (dpWidth - RAYON);
        posY = Math.random() * (dpHeight - RAYON);
        vitessX = Math.random() * 10 - 5;
        vitessY = Math.random() * 10 - 5;

       
        image = new BufferedImage(RAYON, RAYON, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillOval(0, 0, RAYON, RAYON);
        g.dispose();
    }

    public void go() {

       
	if(Fenetre.getFlag()) {
        if (posX + vitessX < 0) {
            vitessX = Math.abs(vitessX);
        }
        if (posX + vitessX + RAYON >= dpWidth) {
            vitessX = -Math.abs(vitessX);
        }
        posX += vitessX;

      
        if (posY + vitessY < 0) {
            vitessY = Math.abs(vitessY);
        }
        if (posY + vitessY + RAYON >= dpHeight*0.83) {
            vitessY = -Math.abs(vitessY);
        }
        posY += vitessY;

    }	
	
	
    }
    
    public static int getRayon() {
        return  RAYON;
      }
    
    public int getPosX() {
        return (int) posX;
      }

      public void setPosX(int posX) {
        this.posX = posX;
      }

      public int getPosY() {
        return (int) posY;
      }

      public void setPosY(int posY) {
        this.posY = posY;
      }  
      
    public void draw(Graphics g) {
        int x = (int) posX;
        int y = (int) posY;
        g.drawImage(image, x, y, null);
    }
    
    
    
   
    
}