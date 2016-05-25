
package rompecabezas;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Salvador
 */
public class Atras_button {
    Frame f;
    ImageIcon atras;
    int  x = 10, y;
    
    public Atras_button(Frame f){
        this.f = f;
        atras = new ImageIcon(getClass().getResource("/recursos/atras.png"));
        y = (int) (f.obtenerDimension().height * 0.80);
        
    }
    
    public void pintar(Graphics2D g2d){
        g2d.drawImage(atras.getImage(),x,y,f);
    }
    
    public boolean click(java.awt.Point p, boolean click){
        if( (p.x>x && p.x < x+atras.getIconWidth()) && (p.y > y && p.y < y+atras.getIconHeight()) && click){
            return true;
        }else{
            return false;
        }
    }
        
    
}
