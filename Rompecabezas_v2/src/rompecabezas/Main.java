
package rompecabezas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Salvador
 */
public class Main extends JPanel{
    Frame fr;
    java.awt.Point cursor;
    Dimension centro;  
    Graphics2D g2d;
    
    ImageIcon fondo,titulo;
    ImageIcon pepa,bob,dog,pocoyo;
    
    int margenx =300;
    
    int pepaWidth=0, pepaHeight=0,pepaPosX =margenx, pepaPosY=0;
    int bobWidth=0, bobHeight=0,bobPosX =margenx, bobPosY=0;
    int dogWidth=0, dogHeight=0,dogPosX =margenx, dogPosY=0;
    int pocoyoWidth=0, pocoyoHeight=0,pocoyoPosX =margenx, pocoyoPosY=0;
    
        
    private boolean click =false;
    
    Main(Frame fr){
        this.fr= fr;
        centro = fr.obtenerDimension();
        
        fondo = new ImageIcon(getClass().getResource("/recursos/paisaje.jpg"));
        
        pepa = new ImageIcon(getClass().getResource("/recursos/pepa.gif"));
        bob= new ImageIcon(getClass().getResource("/recursos/bob.gif"));
        dog = new ImageIcon(getClass().getResource("/recursos/dog.gif"));
        pocoyo = new ImageIcon(getClass().getResource("/recursos/pocoyo.gif"));
        titulo = new ImageIcon(getClass().getResource("/recursos/titulo.png"));
        
        
        pepaWidth = pepa.getIconWidth();
        pepaHeight = pepa.getIconHeight();
        
        bobWidth= bob.getIconWidth();
        bobHeight = bob.getIconHeight();
        
        dogWidth= bob.getIconWidth();
        dogHeight = bob.getIconHeight();
        
        pocoyoWidth= (int)(bob.getIconWidth() * 0.75);
        pocoyoHeight = (int)(bob.getIconHeight() * 0.75);
        actualizar();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawImage(fondo.getImage(),0,0,fr.obtenerDimension().width,fr.obtenerDimension().height,this);        
        g2d.drawImage(pepa.getImage(),pepaPosX,pepaPosY,pepaWidth,pepaHeight,fr);
        g2d.drawImage(bob.getImage(),bobPosX,bobPosY,bobWidth,bobHeight,fr);
        g2d.drawImage(dog.getImage(),dogPosX,dogPosY,dogWidth,dogHeight,fr);
        //g2d.drawImage(pocoyo.getImage(),pocoyoPosX,pocoyoPosY,pocoyoWidth,pocoyoHeight,fr);
        
        g2d.drawImage(titulo.getImage(),(fr.obtenerDimension().width/2)-(titulo.getIconWidth()/2),50,this);
        
    }
    
    public void actualizar(){
        pepaPosY =(int)((centro.getHeight()/2) - (pepaHeight/2));
        
        bobPosY = (int)((centro.getHeight()/2)- (bobHeight/2));
        bobPosX = (int)(pepaPosX + margenx);
        
        dogPosY = (int)((centro.getHeight()/2)- (dogHeight/2));
        dogPosX = (int)(bobPosX + margenx);
        
        pocoyoPosY = (int)((centro.getHeight()/2)- (pocoyoHeight/2));
        pocoyoPosX = (int)(dogPosX + margenx);
        
        colision();
    }
    
    public void colision(){
        cursor = fr.obtenerMousePosition();
       
        //el try es por el uso del click, una variable que puede estar usandose
        try{
            //*******pepa
            if(cursor.x <(pepaPosX+pepaWidth) && cursor.x>pepaPosX && cursor.y <(pepaPosY+pepaHeight) && cursor.y>pepaPosY){
                if(pepaWidth < (pepa.getIconWidth()+30)){
                    pepaWidth = pepaWidth+ 40;
                    pepaHeight = pepaHeight+ 40;
                }
                if(click){
                    fr.cambiarEstadoJuego(1);
                    fr.r.Click();
               }
            }else{
                    pepaWidth = pepa.getIconWidth();
                    pepaHeight = pepa.getIconHeight();
            }
            
            //*******bob
            if(cursor.x <(bobPosX+pepaWidth) && cursor.x>bobPosX && cursor.y <(bobPosY+bobHeight) && cursor.y>bobPosY){
                if(bobWidth < (bob.getIconWidth()+30)){
                    bobWidth = bobWidth+ 40;
                    bobHeight = bobHeight+ 40;
                }
                
                if(click){
                    fr.cambiarEstadoJuego(2);
                    fr.r.Click();
                }
            }
            else{
                    bobWidth = bob.getIconWidth();
                    bobHeight = bob.getIconHeight();
            }

            //*******dog
            
            if(cursor.x <(dogPosX+dogWidth) && cursor.x>dogPosX && cursor.y <(dogPosY+dogHeight) && cursor.y>dogPosY){
                if(dogWidth < (dog.getIconWidth()+30)){
                    dogWidth = dogWidth+ 40;
                    dogHeight = dogHeight+ 40;
                }
                if(click){
                    fr.cambiarEstadoJuego(3);
                    fr.r.Click();
                }
            }else{
                dogWidth = dog.getIconWidth();
                dogHeight = dog.getIconHeight();
            }
            //*******pocoyo
            /* if(cursor.x <(pocoyoPosX+pocoyoWidth) && cursor.x>pocoyoPosX && cursor.y <(pocoyoPosY+pocoyoHeight) && cursor.y>pocoyoPosY){
                if(pocoyoWidth < (pocoyo.getIconWidth()+30)){
                    pocoyoWidth = pocoyoWidth+ 40;
                    pocoyoHeight = pocoyoHeight+ 40;
                }
            }else{
                pocoyoWidth = pocoyo.getIconWidth();
                pocoyoHeight = pocoyo.getIconHeight();
            }*/
            
        }catch(Exception e){}
    }
    
    public void mouseClick(boolean x){
        this.click = x;
    }
        
   
}
