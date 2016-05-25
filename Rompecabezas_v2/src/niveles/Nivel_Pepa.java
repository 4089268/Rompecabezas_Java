
package niveles;

import rompecabezas.panelCompletado;
import java.awt.Color;
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

/**
 *
 * @author Salvador
 */
public class Nivel_Pepa extends JPanel{
    //                      x      y
    //tamaño de cuadro     2041 * 1418
    //tamaño de las piezas 511  * 709
    
    Frame fr;
    Graphics2D g2d;
    Atras_button boton;
    private Pieza[] piezas;
    int maxPiezas = 8;
    
    ImageIcon fondo;
    ImageIcon f;
    ImageIcon ejemplo;
    
    boolean win =false;
    boolean mouse = false;
    int piezaSelec = -1;
    
    Dimension centro;
    double escala = 0.5;
    
    public Nivel_Pepa(Frame fr){
        
        this.fr= fr;
        piezas = new Pieza[maxPiezas];
        boton = new Atras_button(fr);
        Random generador = new Random();
        
              
        fondo = new ImageIcon(getClass().getResource("/recursos/fondo_azul.jpg"));
        f = new ImageIcon(getClass().getResource("/recursos/nivelPepa/fondo.png"));
        ejemplo= new ImageIcon(getClass().getResource("/recursos/nivelPepa/ejemplo.png"));
        obtenerDimensionCentral();
        
        piezas[0] = new Pieza("nivelPepa/pieza1",generador.nextInt(900),generador.nextInt(500),0,0,centro.width,centro.height);
        piezas[1] = new Pieza("nivelPepa/pieza2",generador.nextInt(900),generador.nextInt(500),511,0,centro.width,centro.height);
        piezas[2] = new Pieza("nivelPepa/pieza3",generador.nextInt(900),generador.nextInt(500),1022,0,centro.width,centro.height);
        piezas[3] = new Pieza("nivelPepa/pieza4",generador.nextInt(900),generador.nextInt(500),1533-1,0,centro.width,centro.height);
        piezas[4] = new Pieza("nivelPepa/pieza5",generador.nextInt(900),generador.nextInt(500),0,709,centro.width,centro.height);
        piezas[5] = new Pieza("nivelPepa/pieza6",generador.nextInt(900),generador.nextInt(500),511,709+1,centro.width,centro.height);
        piezas[6] = new Pieza("nivelPepa/pieza7",generador.nextInt(900),generador.nextInt(500),1022,709+1,centro.width,centro.height);
        piezas[7] = new Pieza("nivelPepa/pieza8",generador.nextInt(900),generador.nextInt(500),1533-1,709+1,centro.width,centro.height);
        
//        piezas[0] = new Pieza("pieza1",0+centro.width,0+centro.height,0,0,centro.width,centro.height);
//        piezas[1] = new Pieza("pieza2",(511/3)+centro.width,0+centro.height,511,0,centro.width,centro.height);
//        piezas[2] = new Pieza("pieza3",(1022/3)+centro.width,0+centro.height,1022,0,centro.width,centro.height);
//        piezas[3] = new Pieza("pieza4",(1533/3)+centro.width,0+centro.height,1533,0,centro.width,centro.height);
//        piezas[4] = new Pieza("pieza5",0+centro.width,(709/3)+centro.height,0,709,centro.width,centro.height);
//        piezas[5] = new Pieza("pieza6",(511/3)+centro.width,(709/3)+centro.height,511,709,centro.width,centro.height);
//        piezas[6] = new Pieza("pieza7",(1022/3)+centro.width,(709/3)+centro.height,1022,709,centro.width,centro.height);
//        piezas[7] = new Pieza("pieza8",(1533/3)+centro.width,(709/3)+centro.height,1033,709,centro.width,centro.height);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawImage(fondo.getImage(),0,0,fr.obtenerDimension().width,fr.obtenerDimension().height,this);
        g2d.drawImage(f.getImage(),centro.width,centro.height,f.getIconWidth()/3,f.getIconHeight()/3,this);
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
    
    public void actualizar() throws InterruptedException{
        if(gameOver()){
            
            if(!win){
                fr.r.Tada();
                //agregarAnimacion();
                
                JOptionPane.showMessageDialog(null, "Nivel Completado");
                fr.cambiarEstadoJuego(0);
                win =true;
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
