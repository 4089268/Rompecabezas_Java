
package rompecabezas;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Salvador
 */
public class panelCompletado extends JPanel{
    
    JButton salir;
    ImageIcon complete;
    
    Random r;
    int panelwidth, panelheigth;
    Frame fr;
    
    
    public panelCompletado(int w, int h,Frame f){
        
        this.setLayout(new BorderLayout());
        this.fr =f;
        panelwidth = w;
        panelheigth = h;
        complete = new ImageIcon(getClass().getResource("/recursos/complete.png"));
        
        salir = new JButton("ok");
        salir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fr.cambiarEstadoJuego(0);
            }
        });
        this.add(salir,BorderLayout.SOUTH);
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(complete.getImage(),(panelwidth/2)-(complete.getIconWidth()/2),100, this);
        
        
    }
    
 
}
