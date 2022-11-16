/**
 *
 * Arin Bindra
 * 
 */

/**
 * SimulationMain class
 */

import javax.swing.JFrame;

public class SimulationMain
{
    public static void main(final String[] args) 
    {
        JFrame frame = new JFrame("Island Port Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        
        frame.setSize(830, 850);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
