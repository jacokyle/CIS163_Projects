package project1.lockExample;

import javax.swing.JFrame;

public class LockGUI
{
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Lock example");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       
        //LockGUIPanel panel = new LockGUIPanel();
        // OR
        mainPanel panel = new mainPanel();
        
        frame.getContentPane().add(panel);
        frame.setSize (1000,200);
        frame.setVisible(true);
    }
}
