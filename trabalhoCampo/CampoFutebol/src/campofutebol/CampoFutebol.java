/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campofutebol;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Dericson Pablo
 */
public class CampoFutebol extends Frame{

    CampoFutebol(){
    //Enables the closing of the window.
    {
		addWindowListener(new FecharJanela());
    } 
  }
    
    @Override
    public void paint(Graphics g){
    
        Graphics2D c = (Graphics2D) g;
        
        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        c.setRenderingHints(rh);
        //BufferedImage image = ImageIO.read(new File(fileName));
        
        
        Image theImage = new javax.swing.ImageIcon("C:\\Users\\Dericson Pablo\\Documents\\CURSO\\CG\\campof.png").getImage();
        c.drawImage(theImage, 50,-22,900,800, null);
        c.setColor(Color.WHITE);
        c.setStroke(new BasicStroke(3));
        c.drawRect(200,200,600,350);
        c.drawLine(500, 200, 500, 550);
        c.drawRect(200, 275, 100, 205);
        c.drawRect(200, 332, 40, 90); //pequena area
        c.drawRect(700, 275, 100, 205);
        c.drawRect(760, 332, 40, 90);//peq
        c.drawOval(425, 300, 150, 150);
        c.drawOval(498, 375, 5, 5);
        c.fillOval(498, 375, 5, 5);
        //c.setColor(Color.WHITE);
        c.drawOval(275, 375, 4, 4);
        c.fillOval(275, 375, 4, 4);
        //c.setColor(Color.WHITE);
        c.drawOval(721, 375, 4, 4);
        c.fillOval(721, 375, 4, 4);
        //c.setColor(Color.WHITE);
        c.drawArc(250, 330, 100, 95, 90, -180);
        c.drawArc(650, 330, 100, 95, 90, 180);
        c.drawArc(192, 193, 25, 25, 25, -130);
        c.drawArc(192, 533, 25, 25, 108, -130);
        c.drawArc(785, 533, 25, 25, 73, 130);
        c.drawArc(785, 193, 25, 25, 155, 130);
        c.setColor(Color.WHITE);
        c.setFont(new Font("Roboto", Font.PLAIN, 32));
        c.drawString("CAMPO DE FUTEBOL", 350, 175);
        c.rotate(Math.toRadians(90), 1000, 50);
        c.translate(390,-140);
        c.drawString("CAMPO DE FUTEBOL", 785, 195);
        c.rotate(Math.toRadians(180), 1000, 50);
        c.translate(125,-1230);
        c.drawString("CAMPO DE FUTEBOL", 785, 200);
        c.rotate(Math.toRadians(270), 800, 1280);
        c.translate(490,-10);
        c.drawString("CAMPO DE FUTEBOL", 800, 1205);
    }
    
    public class FecharJanela extends WindowAdapter{
        @Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
    }
    
    public static void main(String[] args) {
        CampoFutebol cf = new CampoFutebol();
        cf.setTitle("Campo de Futebol");
        cf.setSize(1280,720);
        cf.setVisible(true);
        cf.setBackground(Color.black);
    }
    
}
