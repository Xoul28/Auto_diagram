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
	public DrawerSimple(){
		super("Block");
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.black)));
        setContentPane(cp);
        //cp.add(new SelectorBlock("����� ���"), BorderLayout.CENTER);
        //cp.add(new Cycle("����� ���"), BorderLayout.CENTER);
        cp.add(new Component(), BorderLayout.CENTER);
        JButton btn = new JButton("Close");
        cp.setBackground(Color.white);
        setSize(500, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new DrawerSimple().setVisible(true);
    }
}
