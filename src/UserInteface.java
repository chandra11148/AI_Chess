import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class UserInteface extends JPanel implements MouseListener,MouseMotionListener{
    static int x=0,y=0;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        g.setColor(Color.RED);
        g.fillRect(10, 10, 10, 10);
    
        Image chessPieces;
        chessPieces = new ImageIcon("Chess_pieces.jpg").getImage();
        g.drawImage(chessPieces,x,y,x+64,y+64,0,0,64,64,this);
    }
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
        
    }
    public void mouseMoved(MouseEvent e){
        x =e.getX();
        y= e.getY();
        repaint();
    }
    public void mouseExited(MouseEvent e){}
}
