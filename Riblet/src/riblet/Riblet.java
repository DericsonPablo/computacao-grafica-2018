/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riblet;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Dericson Pablo
 */

public class Riblet extends Frame{
    
    {
        addWindowListener(new FecharJanela());
    }
    
     public class FecharJanela extends WindowAdapter
    {
        @Override
	public void windowClosing(WindowEvent e)
	{
            System.exit(0);
	}
    }
    
    public void desenhaRiblet(double tam, double altura, double angulo, double distCentro, double distInicio, Graphics g, Color cor)
    {
        Graphics2D g2d = (Graphics2D) g;
        GeneralPath riblet = new GeneralPath();
        GeneralPath base = new GeneralPath();
        g2d.setColor(cor);
        double cosseno = angulo + 90;          
        double catAdj = Math.cos(cosseno) * altura;   
        riblet.moveTo(0, 540);
        riblet.lineTo(catAdj * 2, 540);
        riblet.lineTo(catAdj * 2, 540 - altura);
        riblet.lineTo(catAdj, 540);
        riblet.moveTo(catAdj * 2, 540);
        riblet.lineTo(catAdj * 3, 540);
        riblet.lineTo(catAdj * 2, 540 - altura);
        riblet.moveTo(catAdj * 3, 540);
        riblet.lineTo(distCentro + (catAdj * 2), 540);      
        base.moveTo(0, 540);
        base.lineTo(0, 640);
        base.lineTo(catAdj * 3 + distCentro, 640);
        base.lineTo(catAdj * 3 + distCentro, 540);
        g2d.draw(riblet);
        g2d.fill(riblet);
        g2d.draw(base);
        g2d.fill(base);
        
        
        
        
        for(int i = 1; i < tam; i++)
        {
            g2d.translate(distCentro + (catAdj * 2), 0);
            g2d.draw(riblet);
            g2d.fill(riblet);
            g2d.draw(base);
            g2d.fill(base);
        }
    }
     
    @Override
    public void paint(Graphics g)
    {
        desenhaRiblet(10, 50, 60, 50, 50, g, Color.RED);
    }
    
    public static void main(String[] args) {
        Riblet r = new Riblet();
        r.setTitle("Micro Estrutura");
        r.setVisible(true);
        r.setBackground(Color.BLACK);
        r.setSize(1920, 1080);
    }
    
}
