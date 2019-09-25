/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloword;

import java.awt.geom.*;

/**
 *
 * @author dericson
 */
public class ExemploAula2 {
    double x1, y1, x2, y2, ctrx1, ctry1, largura, altura;
    /*Line2D.Double line;
    QuadCurve2D.Double curve;
    CubicCurve2D.Double cubic;
    Rectangle2D.Double rec;
    Ellipse2D.Double ell;*/
    
    public ExemploAula2() {
        /*line = new Line2D.Double(x1, y1, x2, y2);
        curve = new QuadCurve2D.Double(x1, y1, ctrx1, ctry1, x2, y2);
        cubic = new CubicCurve2D.Double(x1, y1, ctrx1, ctry1, ctrx1, ctry1, x2, y2);
        rec = new Rectangle2D.Double(x1, y1, largura, altura);
        ell = new Ellipse2D.Double(x1, y1, largura, altura);*/
    }
    
    public void desenho(){
        GeneralPath gp = new GeneralPath();
        gp.moveTo(10, 10);
        gp.lineTo(10, 30);
        
    }
}
