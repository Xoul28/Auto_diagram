package auto_BSA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawerSimple extends JFrame {
	public DrawerSimple(Block function){
		super("Block");
        JPanel cp = new JPanel(new BorderLayout());
        cp.setOpaque(true);
        cp.setBackground(Color.white);
        setContentPane(cp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        cp.add(new Component(function, cp), BorderLayout.CENTER);
    
		
        
        setSize(400, 2000);
      setLocationRelativeTo(null);
        
     
    	
        
    }

}
