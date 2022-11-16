/**
 *
 * Arin Bindra
 * 
 */

/**
 * Panel class
 */

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Panel extends JPanel implements KeyListener
{
    private Image iBoat;
    private Image iIsland;
    private Image iBoatIsland;
    public Island island;
    private final Boat[] boats;
    private boolean started = false;
    private final int nBoats = 20;
    
    public Panel()
    {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(new Color(51,204,255));
        this.island = new Island(750, 320);
        this.setImages();
        this.boats = new Boat[this.nBoats];
        this.setBoats();
    }
    
    //--------------------------------------------------------------------------
    
    private void setImages()
    {
        this.iBoat = new ImageIcon("boat.png").getImage();
        this.iIsland = new ImageIcon("land.png").getImage();
        this.iBoatIsland = new ImageIcon("boat_land.png").getImage();
    }
    
    //--------------------------------------------------------------------------
    
    private void setBoats()
    {
        for (int i=0; i < this.nBoats; i++) 
        {
            this.boats[i] = new Boat(10, i * 40, this.island); /////
        }
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public void paintComponent(final Graphics graphics)
    {
        super.paintComponent(graphics);
        
        graphics.setFont(new Font("Courier", 1, 18));
        
        graphics.drawString("TO START SYNCHRONIZED PRESS 'S'", 250, 20);
        graphics.drawString("TO START UNSYNCHRONIZED PRESS SPACE", 250, 40);
        
        for (Boat boat : this.boats) {
            if (boat.getFinished() == false) {
                graphics.drawImage(this.iBoat, boat.getX(), boat.getY(), this);
            }
        }
        
        for (int i = 0; i < this.boats.length; i++) 
        {
            if (this.boats[i].getInProgress() == true)
            {
                for (int j = 0; j < this.boats.length; j++) 
                {
                    if ((this.boats[j].getInProgress() == true) && (j != i) && (this.boats[i].getX() == this.boats[j].getX())
                            && (this.boats[i].getY() == this.boats[j].getY())) 
                    {
                        graphics.drawString("BOATS HAVE CRASHED!", 250,60);
                    }
                }
            }
        }
        
        if (Boat.arrived == true) 
        {
            graphics.drawImage(this.iBoatIsland, this.island.getX(), this.island.getY() -20, this);
        }
        else 
        {
            graphics.drawImage(this.iIsland, this.island.getX(), this.island.getY() -20, this);
        }
        
        this.repaint();
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public void keyTyped(final KeyEvent key) {
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public void keyPressed(final KeyEvent key) 
    {
        if (this.started == false) 
        {
            for (Boat boat : this.boats) 
            {
                boat.sync = ((key.getKeyChar() == 83) || (key.getKeyChar() == 115));
                boat.start();
            }
            this.started = true;
        }
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public void keyReleased(final KeyEvent key) {
    }
}
