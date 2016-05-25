
package rompecabezas;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import niveles.Nivel_Avent;
import niveles.Nivel_Bob;
import niveles.Nivel_Pepa;

/**
 *
 * @author Salvador
 */

public class Frame extends JFrame implements MouseListener{
    
    private Cursor cursor1;
    private Cursor cursor2;
    private ImageIcon ico1;
    private ImageIcon ico2;
    
    Nivel_Pepa pepa;
    Nivel_Bob bob;
    Nivel_Avent avent;
    
    Main main;
    public Reproductor r;
    
    private int estadoJuego = 0;
    public Game game;
    
        
    Frame(){
        ico1 = new ImageIcon(getClass().getResource("/recursos/mano1.png"));
        ico2 = new ImageIcon(getClass().getResource("/recursos/mano2.png"));
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        cursor1 = tk.createCustomCursor(ico1.getImage(),new Point(1,1), "cursor1");
        cursor2 = tk.createCustomCursor(ico2.getImage(),new Point(1,1), "cursor2");
        
        actualizarEstado();
        
        this.setUndecorated(true);
        this.setCursor(cursor1);
        this.addMouseListener(this);
        this.setMinimumSize(tk.getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        r = new Reproductor();
        r.Play();
        
        game = new Game(this);
        game.run();
    }
    
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setTitle("Main");
    }
    
    public void actualizar(){
        try{
            switch(estadoJuego){
                case 0:
                    main.actualizar();
                    main.repaint();
                break;
                case 1:
                    pepa.actualizar();
                    pepa.repaint();
                break;
                case 2:
                    bob.actualizar();
                    bob.repaint();
                break;
                case 3:
                    avent.actualizar();
                    avent.repaint();
                break;
        }
        
        }catch(Exception err){}
        
        this.repaint();
        this.validate();
    }
    
    public Dimension obtenerDimension(){
        Dimension vnt;
        Toolkit t = Toolkit.getDefaultToolkit();
        vnt =t.getScreenSize();
        return vnt;
    }
    
    public Point obtenerMousePosition(){
        Point x = this.getMousePosition();
        return x;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        this.setCursor(cursor2);
        try{
            switch(estadoJuego){
            case 0:
                main.mouseClick(true);
            break;
            case 1:
                pepa.mouseClick(true);
            break;
            case 2:
                bob.mouseClick(true);
            break;
            case 3:
                avent.mouseClick(true);
            break;
                      
                        }
        }catch(Exception err){}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setCursor(cursor1);
        try{
            switch(estadoJuego){
            case 0:
                main.mouseClick(false);
            break;
            case 1:
                pepa.mouseClick(false);
            break;
            case 2:
                bob.mouseClick(false);
            break;
            case 3:
                avent.mouseClick(false);
            break;
                  
        }
        }catch(Exception err){}
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void actualizarEstado() {
        switch(estadoJuego){
            /*  0 main
             *  1 nivel pepa
             *  2 nivel bob
             *  3 nivel dog
             *  4 nivel pocoyo
             */
            case 0:
                limpiarNiveles();
                this.validate();
                
                this.main= new Main(this);
                this.add(main);
            break;
                
            case 1:
                limpiarNiveles();
                this.validate();
                
                pepa = new Nivel_Pepa(this);
                this.add(pepa);
            break;
            
            case 2:
                limpiarNiveles();
                this.validate();
                
                bob = new Nivel_Bob(this);
                this.add(bob);
            break;
            case 3:
                limpiarNiveles();
                this.validate();
                
                avent = new Nivel_Avent(this);
                this.add(avent);
            break;
                
        }
    }
    
    public void limpiarNiveles(){
        try{
            this.remove(main);
            main=null;
        }catch(Exception e){}
        
        try{
            this.remove(pepa);
            pepa=null;
        }catch(Exception e){}
        
        try{
            this.remove(bob);
            bob = null;
        }catch(Exception e){}
        
        try{
            this.remove(avent);
            avent = null;
        }catch(Exception e){}
        
    }
    
    public void cambiarEstadoJuego(int est ){
        this.estadoJuego = est;
        actualizarEstado();
    }
    
    
     
}
    