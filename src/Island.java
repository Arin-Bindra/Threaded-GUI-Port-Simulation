/**
 *
 * Arin Bindra
 * 
 */

/**
 * Island class
 */
public class Island
{
    private int x;
    private int y;
    public boolean occupied;
    
    public Island(int x, int y) 
    {
        this.setX(x);
        this.setY(y);
        this.occupied = false;
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
}
