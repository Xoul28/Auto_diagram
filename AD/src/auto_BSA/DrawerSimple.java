package auto_BSA;

import java.awt.BorderLayout;
import java.awt.Color;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawerSimple extends JFrame {
	public DrawerSimple(Block function){
		super("Block");
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.black)));
        setContentPane(cp);
        //cp.add(new SelectorBlock("����� ���"), BorderLayout.CENTER);
        //cp.add(new Cycle("����� ���"), BorderLayout.CENTER);
        cp.add(new Component(function), BorderLayout.CENTER);
        cp.setBackground(Color.white);
        setSize(700, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
