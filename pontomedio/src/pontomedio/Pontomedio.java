/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pontomedio;


import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 *
 * @author dericson
 */
public class Pontomedio extends Frame{

    Pontomedio(){
    //Enables the closing of the window.
    {
		addWindowListener(new FecharJanela());
  } 

  }
    static int cX, cY, raio;
    
    public void paint(Graphics g){
    
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        int x = 0,y = raio, u = 1, v = 2*raio-1, e = 0;
        
        while(x<y){
            
            g2d.drawRect(cX + x, cY + y,1,1); // NNE
            g2d.drawRect( cX + y, cY - x,1,1); // ESE
            g2d.drawRect( cX - x, cY - y,1,1); // SSW
            g2d.drawRect( cX - y, cY + x,1,1); // WNW
            x++; e += u; u += 2;
            if (v < 2 * e){y--; e -= v; v -= 2;}
            if (x > y) break;
            g2d.drawRect( cX + y, cY + x,1,1); // ENE
            g2d.drawRect( cX + x, cY - y,1,1); // SSE
            g2d.drawRect( cX - y, cY - x,1,1); // WSW
            g2d.drawRect( cX - x, cY + y,1,1); // NNW
        }      
    
    }
    
    public class FecharJanela extends WindowAdapter{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
    }
    
    public static void main(String[] args) {
        Pontomedio f = new Pontomedio();
        Scanner s1 = new Scanner(System.in);
        System.out.println("Digite o Centro: ");
        System.out.println("\nX:");
        cX = s1.nextInt();
        System.out.println("\nY:");
        cY = s1.nextInt();
        System.out.println("\nDigite o raio:");
        raio = s1.nextInt();
        f.setTitle("Ponto Medio");
        f.setSize(1280,720);
        f.setVisible(true);
    }
    
}
