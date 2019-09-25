/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloword;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author dericson
 */
public class SimpleJava2DExample extends Frame {

    private int windowHeight;

    /*{
        addWindowListener(new FecharJanela());
        
    }*/
    public SimpleJava2DExample() {
        addWindowListener(new FecharJanela());
    }

    public SimpleJava2DExample(int height) {
        addWindowListener(new FecharJanela());
        windowHeight = height;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawString("Alo Mundao sem porteira!", 30, 100);

        GeneralPath gp = new GeneralPath();
        
        //carro 1
       gp.moveTo(60, 120);
        
        gp.lineTo(80, 120);
        gp.quadTo(90, 140, 100, 120); //roda 1
        gp.lineTo(160, 120);
        gp.quadTo(170, 140, 180, 120); //roda 2
        gp.lineTo(200, 120);
        gp.curveTo(195, 100, 200, 80, 160, 80); // traseira
        gp.lineTo(110, 80);
        gp.lineTo(90, 100);
        gp.lineTo(60, 100);
        gp.lineTo(60, 120);
        
        
       //chao
       gp.moveTo(-20,80);
       gp.lineTo(500,80);
       
       /*
       //carro 2
       gp.moveTo(60,130);
       gp.lineTo(60, 80);
       gp.lineTo(180,80);
       gp.lineTo(180,130);
       
       gp.moveTo(90,130);
       gp.lineTo(90,80);
       gp.moveTo(110,130);
       gp.lineTo(110,80);
       gp.moveTo(65,80);
       gp.lineTo(65,75);
       gp.lineTo(85,80);
       gp.moveTo(90,80);
       gp.lineTo(90,75);
       gp.lineTo(92,75);
       gp.lineTo(92,80);
       gp.moveTo(155,80);
       gp.lineTo(155,75);
       gp.lineTo(175,80);
       gp.moveTo(-20,130);
       gp.lineTo(500,130);
       
            //outro carro
      */
      /* //carreta
       gp.moveTo(195,120);
       gp.lineTo(215,120);
       gp.lineTo(215,100);
       gp.moveTo(215,100);
       gp.lineTo(275,100);
       gp.lineTo(275,120);
       gp.lineTo(265,120);
       gp.quadTo(255,140,245,120);
       gp.lineTo(215, 120);
       */
       
    AffineTransform yUp = new AffineTransform();
    yUp.setToScale(1,-1);
    AffineTransform translate = new AffineTransform();
    translate.setToTranslation(40,windowHeight-50);
    yUp.preConcatenate(translate);

    //Apply the transformation to the Graphics2D object to draw everything
    //in "real" coordinates.
    g2d.transform(yUp);

    //The lines should have a thickness of 3.0 instead of 1.0.
    g2d.setStroke(new BasicStroke(3.0f));

    

    //Define a rotation.
    AffineTransform rotation = new AffineTransform();
    rotation.setToRotation(Math.PI/4);



     g2d.setStroke(new BasicStroke(1.0f));
    //Draw a coordinate system.
   // drawSimpleCoordinateSystem(200,200,g2d);
       
      
       g2d.draw(gp);
       
       

    }
}
