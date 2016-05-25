
package niveles;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Salvador
 */
public class Pieza {
    
    private boolean bloqueada = false;
    private int posx;
    private int posy;
    
    private int escala = 3;
    
    private int winx;
    private int winy;
    
    private ImageIcon ico;
    
    Pieza(String nombre,int x, int y, int winx, int winy,int margx, int margy){
        ico = new ImageIcon(getClass().getResource("/recursos/"+nombre+".png"));
        posx = x;
        posy = y;
        this.winx = (winx/escala)+margx;
        this.winy = (winy/escala)+margy;
    }
    
    
    Pieza(String nombre,int x, int y, int winx, int winy,int margx, int margy,int esc){
        escala =esc;
        ico = new ImageIcon(getClass().getResource("/recursos/"+nombre+".png"));
        posx = x;
        posy = y;
        this.winx = (winx/escala)+margx;
        this.winy = (winy/escala)+margy;
    }
    
    public void NuevaPosision(int x, int y){
        if(!bloqueada){
            this.posx = x;
            this.posy = y;
        }
    }
    
//    public void actualizarEscala(int s){
//        this.escala = s;
//    }
    
    public int ObtenerPosX(){
        return posx;
    }
    
    public int ObtenerPosY(){
        return posy;
    }
    
    public Image ObtnerIcon(){
        Image x = ico.getImage();
        return x;
    }
    
    public void bloquearPieza(){
        if((posx<(winx+20) && posx >(winx-20)) && (posy<(winy+20) && posy >(winy-20))){
           NuevaPosision(winx,winy);
           bloqueada = true; 
        }
    }
    
    public boolean bloqueada(){
        return bloqueada;
    }
    
    public int obtenerAnchura(){
        return ico.getIconWidth()/escala;
    }
    
    public int obtenerAltura(){
        return ico.getIconHeight()/escala;
    }
    
}
