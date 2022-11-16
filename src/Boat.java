/**
 * Arin Bindra
 * 
 */

/**
 * Boat class 
 */

public class Boat extends Thread
{
    public static boolean arrived;
    private int x;
    private int y;
    private int wait;
    private boolean finished = false;
    private boolean inProgress = false;
    private final Island island;
    public boolean sync = false;
    
    public Boat(int x,int y, Island island) 
    {
        this.setX(x);
        this.setY(y);
        this.island = island;
        this.wait = Math.round((float)Math.random() * 100.0f);
    }
    
    //--------------------------------------------------------------------------
    
    static 
    {
        Boat.arrived = false;
    }
    
    //--------------------------------------------------------------------------
    
    private void setX(int x)
    {
        this.x = x;
    }
    
    //--------------------------------------------------------------------------
    
    private void setY(int y)
    {
        this.y = y;
    }
    
    //--------------------------------------------------------------------------
    
    public int getX()
    {
        return this.x;
    }
    
    //--------------------------------------------------------------------------
    
    public int getY()
    {
        return this.y;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getFinished()
    {
        return this.finished;
    }
    
    //--------------------------------------------------------------------------
    
    public boolean getInProgress()
    {
        return this.inProgress;
    }
    
    //--------------------------------------------------------------------------
 /**
 * The run method runs the boat thread, which allows the boat to move towards the island.
 * The method also includes synchronizes the island object depending on the game mode 
 */
    @Override
    public void run() 
    {
        while (this.wait > 0) 
        {
            try 
            {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) 
            {
                System.out.println("Boat class, unable to sleep");
            }
            
            this.wait--;
        }
        
        if (this.sync == true) 
        {
            synchronized (this.island)
            {
                this.checkOccupied();
            }
        }
        else 
        {
            this.checkOccupied();
        }
        
        this.inProgress = true;
        
        this.checkPosition();
        
        Boat.arrived = true;
        this.inProgress = false;
        
        try 
        {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) 
        {
            System.out.println("Boat class, Sync, unable to sleep");
        }
        
        this.island.occupied = false;
        Boat.arrived = false;
        this.finished = true;
    }
    
    //--------------------------------------------------------------------------
/**
 * The check Occupied method checks if there is another thread using the island
 * object, by continuously putting the thread to sleep until it is available
 */
    private void checkOccupied()
    {
        while (this.island.occupied == true) 
       {
            try 
            {
                Thread.sleep(7L);
            }
            catch (InterruptedException ex2) 
            {
                System.out.println("Boat class, Sync, unable to sleep");
            }
        }
        this.island.occupied = true;
    }
    
    //--------------------------------------------------------------------------
 /**
 * The check position continuously checks the position of the boat compared to the
 * island, and increases or decreases the x and y coordinates of the boat until
 * they are the same as the island
 */
    private void checkPosition()
    {
        while ((this.x != this.island.getX()) || (this.y != this.island.getY())) 
        {
            if (this.x < this.island.getX()) 
            {
                this.x++;
            }
            
            if (this.y < this.island.getY()) 
            {
                this.y++;
            }
            
            if (this.island.getY() < this.y) 
            {
                this.y--;
            }
            
            try 
            {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) 
            {
                System.out.println("Boat class, unable to sleep");
            }
        }
    }
}
