package rompecabezas;

/**
 *
 * @author Salvador
 */
public class Game implements Runnable {
    Frame fr;
    Game(Frame r){
        this.fr=r;
    }

    @Override
    public void run() {
        while(true){
            fr.actualizar();
            try{
                Thread.sleep(100);
            }catch(Exception e){}
            
        }
        
    }
    
    
    
}
