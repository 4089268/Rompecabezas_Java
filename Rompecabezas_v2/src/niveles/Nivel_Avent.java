
package niveles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rompecabezas.Atras_button;
import rompecabezas.Frame;
import rompecabezas.panelCompletado;

/**
 *
 * @author Salvador
 */
public class Nivel_Avent extends JPanel{
    //                      x      y
    //tamaño de cuadro     1843 * 1276
    //tamaño de las piezas 171  * 241
    //tamaño de las piezas 184  * 241
    
    Frame fr;
    Graphics2D g2d;
    private Pieza[] piezas;
    int maxPiezas = 18;
    
    Atras_button boton;
    
    ImageIcon fondo;
    ImageIcon f;
    ImageIcon ejemplo;
    
    boolean win =false;
    boolean mouse = false;
    int piezaSelec = -1;
    
    Dimension centro;
    double escala = 0.5;
    
    public Nivel_Avent(Frame fr){
        this.fr= fr;
        piezas = new Pieza[maxPiezas];
        Random generador = new Random();
        boton = new Atras_button(fr);
                
        fondo = new ImageIcon(getClass().getResource("/recursos/fondo_amarillo.jpg"));
        f = new ImageIcon(getClass().getResource("/recursos/nivelAvent/fondo.png"));
        ejemplo= new ImageIcon(getClass().getResource("/recursos/nivelAvent/ejemplo.png"));
        
        obtenerDimensionCentral();
        piezas[0] = new Pieza("nivelAvent/p1",generador.nextInt(900),generador.nextInt(500),0,0,centro.width,centro.height,2);
        piezas[1] = new Pieza("nivelAvent/p2",generador.nextInt(900),generador.nextInt(500),171,0,centro.width,centro.height,2);
        piezas[2] = new Pieza("nivelAvent/p3",generador.nextInt(900),generador.nextInt(500),355,0,centro.width,centro.height,2);
        piezas[3] = new Pieza("nivelAvent/p4",generador.nextInt(900),generador.nextInt(500),539,0,centro.width,centro.height,2);
        piezas[4] = new Pieza("nivelAvent/p5",generador.nextInt(900),generador.nextInt(500),723,0,centro.width,centro.height,2);
        piezas[5] = new Pieza("nivelAvent/p6",generador.nextInt(900),generador.nextInt(500),907,0,centro.width,centro.height,2);
        
        piezas[6] = new Pieza("nivelAvent/p7",generador.nextInt(900),generador.nextInt(500),0,241,centro.width,centro.height,2);
        piezas[7] = new Pieza("nivelAvent/p8",generador.nextInt(900),generador.nextInt(500),171,241,centro.width,centro.height,2);
        piezas[8] = new Pieza("nivelAvent/p9",generador.nextInt(900),generador.nextInt(500),355,241,centro.width,centro.height,2);
        piezas[9] = new Pieza("nivelAvent/p10",generador.nextInt(900),generador.nextInt(500),539,241,centro.width,centro.height,2);
        piezas[10] = new Pieza("nivelAvent/p11",generador.nextInt(900),generador.nextInt(500),723,241,centro.width,centro.height,2);
        piezas[11] = new Pieza("nivelAvent/p12",generador.nextInt(900),generador.nextInt(500),907,241,centro.width,centro.height,2);
        
        piezas[12] = new Pieza("nivelAvent/p13",generador.nextInt(900),generador.nextInt(500),0,482-1,centro.width,centro.height,2);
        piezas[13] = new Pieza("nivelAvent/p14",generador.nextInt(900),generador.nextInt(500),171,482-1,centro.width,centro.height,2);
        piezas[14] = new Pieza("nivelAvent/p15",generador.nextInt(900),generador.nextInt(500),355,482-1,centro.width,centro.height,2);
        piezas[15] = new Pieza("nivelAvent/p16",generador.nextInt(900),generador.nextInt(500),539,482-1,centro.width,centro.height,2);
        piezas[16] = new Pieza("nivelAvent/p17",generador.nextInt(900),generador.nextInt(500),723,482-1,centro.width,centro.height,2);
        piezas[17] = new Pieza("nivelAvent/p18",generador.nextInt(900),generador.nextInt(500),907,482-1,centro.width,centro.height,2);
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawImage(fondo.getImage(),0,0,fr.obtenerDimension().width,fr.obtenerDimension().height,this);
        g2d.drawImage(f.getImage(),centro.width,centro.height,(f.getIconWidth()/2)-2,(f.getIconHeight()/2)-2,this);
        g2d.drawImage(ejemplo.getImage(),10,10,ejemplo.getIconWidth()/8,ejemplo.getIconHeight()/8, fr);
        
         for(int i = maxPiezas-1; i>=0; i--){
            g2d.drawImage(piezas[i].ObtnerIcon(), piezas[i].ObtenerPosX(), piezas[i].ObtenerPosY(),
                        piezas[i].obtenerAnchura(),piezas[i].obtenerAltura(), this);
        }
        
        boton.pintar(g2d);
         
         if(piezaSelec != -1){
             g2d.drawImage(piezas[piezaSelec].ObtnerIcon(), piezas[piezaSelec].ObtenerPosX(), piezas[piezaSelec].ObtenerPosY(),
                     piezas[piezaSelec].obtenerAnchura(),piezas[piezaSelec].obtenerAltura(), fr);
         }
    }
    
    public void actualizar(){
        if(gameOver()){
             if(!win){
                fr.r.Tada();
                //agregarAnimacion();
                
                JOptionPane.showMessageDialog(null, "Nivel Completado");
                fr.cambiarEstadoJuego(0);
                win=true;
            }
            
            //fr.cambiarEstadoJuego(0);
        }
        if(boton.click(fr.obtenerMousePosition(),mouse)){
            fr.cambiarEstadoJuego(0);
        }
        try{
            if(mouse){
                if(piezaSelec == -1){
                    piezaSelec = colision(fr.obtenerMousePosition().x,fr.obtenerMousePosition().y);
                }
                moverPieza(piezaSelec,fr.obtenerMousePosition().x,fr.obtenerMousePosition().y);
            }else{
                piezaSelec = -1;
                bloquearPiezas();
                
            }
        }catch(Exception e){}
    }
    
    public void mouseClick(boolean m){
        mouse = m;
    }

    private void obtenerDimensionCentral() {
        Toolkit t= Toolkit.getDefaultToolkit();
        Dimension pantalla = t.getScreenSize();
        int x = (int)(pantalla.width - (fondo.getIconWidth()*escala))/2;
        int y = (int)(pantalla.height - (fondo.getIconHeight()*escala))/2;
        
        centro= new Dimension(x,y);
    }
    
    public int colision(int x,int y){
        boolean ok =true;
        int i =0, r=-1;
        
        while(ok && i<maxPiezas){
            if((x>piezas[i].ObtenerPosX() && x < (piezas[i].ObtenerPosX()+piezas[i].obtenerAnchura())) &&
                (y>piezas[i].ObtenerPosY() && y < (piezas[i].ObtenerPosY()+piezas[i].obtenerAltura()))&& !piezas[i].bloqueada()){
                r =i;
                ok = false;
            }
            i++;
        }
       return r;
    }
    
    public void moverPieza(int id, int x, int y){
        piezas[id].NuevaPosision(x-(piezas[id].obtenerAnchura()/2),y-(piezas[id].obtenerAltura()/2));
    }
    
    public void bloquearPiezas(){
        for(int i=0; i<maxPiezas; i++){
            piezas[i].bloquearPieza();
        }
    }
    
    public boolean gameOver(){
       boolean ok = true;
       int i=0;
       
       while(ok && i<maxPiezas){
           if(!piezas[i].bloqueada()){
               ok = false;
           }
           i++;
       }
       return ok;
    }

    private void agregarAnimacion() {
        
        int ancho = 500, alto=300;
        int margen_y =50;
        int margen_x =(int) (((fr.obtenerDimension().width)/2)-(ancho/2)) ;
        panelCompletado anim = new panelCompletado(ancho,alto,fr);
        
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(margen_x)
                .addComponent(anim,ancho,ancho,ancho)
                .addContainerGap(margen_x, Short.MAX_VALUE)));
        
        layout.setVerticalGroup( layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(margen_y)
                .addComponent(anim,alto,alto,alto) .addContainerGap(margen_y, Short.MAX_VALUE)));
        
        this.setLayout(layout);
    }
}
