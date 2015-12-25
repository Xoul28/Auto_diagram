package auto_BSA;

import java.awt.Graphics;

import javax.swing.JComponent;

abstract class BSblock extends JComponent{
	
    protected String line;
    protected int pos;
    public BSblock(String line, int pos){
    	this.line = line;
    	this.pos = pos;
    	setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
    }
    public abstract int getBlockHeight();
		
    public int nextPos(){
    	return pos+5+getBlockHeight();
    }

}
