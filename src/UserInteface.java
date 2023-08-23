import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class UserInteface extends JPanel implements MouseListener,MouseMotionListener{
    static int x=0,y=0;
    static int sqrSize =32;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        for(int i=0;i<64;i+=2){
            g.setColor(new Color(255, 200, 100));
            g.fillRect((i%8+(i/8)%2)*sqrSize, (i/8)*sqrSize, sqrSize, sqrSize);
            g.setColor(new Color(150, 50, 30));
            g.fillRect(((i+1)%8-((i+1)/8)%2)*sqrSize, ((i+1)/8)*sqrSize, sqrSize, sqrSize);
        }
        Image chessPieces;
        chessPieces = new ImageIcon("Chess_pieces1.png").getImage();
        
        for(int i=0;i<64;i++){
            int j=-1,k=-1;
            switch(AlphaChess.ChessBoard[i/8][i%8]){
                case "P":j=5;k=1;
                        break;
                case "p":j=5;k=0;
                        break;
                case "R":j=0;k=1;
                        break;
                case "r":j=0;k=0;
                        break;
                case "K":j=4;k=1;
                        break;
                case "k":j=4;k=0;
                        break;
                case "B":j=1;k=1;
                        break;
                case "b":j=1;k=0;
                        break;
                case "A":j=3;k=1;
                        break;
                case "a":j=3;k=0;
                        break;
                case "Q":j=2;k=1;
                        break;
                case "q":j=2;k=0;
                        break;
            }
            if(j!=-1&&k!=-1){
                g.drawImage(chessPieces,(i%8)*sqrSize,(i/8)*sqrSize,(i%8+1)*sqrSize,(i/8+1)*sqrSize,j*64,k*64,(j+1)*64,(k+1)*64,this);
            }
        }
        
        
        // g.setColor(Color.RED);
        // g.fillRect(10, 10, 10, 10);
    
        // Image chessPieces;
        // chessPieces = new ImageIcon("Chess_pieces.jpg").getImage();
        // g.drawImage(chessPieces,x,y,x+64,y+64,0,0,64,64,this);
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
