package tarefaschiff;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;
import java.util.Enumeration;


public class TarefaSchiff extends JFrame
{

  public Canvas3D myCanvas3D;


  public TarefaSchiff()
  {

    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);
    simpUniv.getViewingPlatform().setNominalViewingTransform();
    createSceneGraph(simpUniv);
    addLight(simpUniv);

    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


    setTitle("Um objeto carregado de um arquivo");
    setSize(700,700);
    getContentPane().add("Center", myCanvas3D);
    setVisible(true);


  }


  public static void main(String[] args)
  {
     TarefaSchiff le = new TarefaSchiff();
  }


  public void createSceneGraph(SimpleUniverse su)
  {


    ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
    Scene s = null;

    try
    {
      s = f.load("C:\\Users\\deinfo\\Documents\\NetBeansProjects\\TarefaSchiff\\Schiff.obj");
    }
    catch (Exception e)
    {
      System.out.println("Falha no carregamento do arquivo:" + e);
    }


    Transform3D tfObject = new Transform3D();
    tfObject.rotZ(0.4*Math.PI);
    Transform3D xRotation = new Transform3D();
    xRotation.rotY(0.4*Math.PI);
    tfObject.mul(xRotation);
    TransformGroup tgObject = new TransformGroup(tfObject);
    tgObject.addChild(s.getSceneGroup());


    Hashtable namedObjects = s.getNamedObjects();
    Enumeration enumer = namedObjects.keys();
    String name;
    while (enumer.hasMoreElements())
    {
      name = (String) enumer.nextElement();
      System.out.println("Nome: "+name);
    }



    Appearance lightBlueApp = new Appearance();
    setToMyDefaultAppearance(lightBlueApp,new Color3f(0.0f,0.2f,0.3f));
    Shape3D partOfTheObject = (Shape3D) namedObjects.get("flugdeck");
    partOfTheObject.setAppearance(lightBlueApp);


    BranchGroup theScene = new BranchGroup();

    theScene.addChild(tgObject);


    Background bg = new Background(new Color3f(0.7f,1.0f,1.0f));
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
    bg.setApplicationBounds(bounds);
    theScene.addChild(bg);


    theScene.compile();


    su.addBranchGraph(theScene);
  }


  /**
  * @param app      
  * @param col      
  */
  public static void setToMyDefaultAppearance(Appearance app, Color3f col)
  {
    app.setMaterial(new Material(col,col,col,col,150.0f));
  }




  public void addLight(SimpleUniverse su)
  {

    BranchGroup bgLight = new BranchGroup();

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
    Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
    Vector3f lightDir1  = new Vector3f(-1.0f,0.0f,-0.5f);
    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
    light1.setInfluencingBounds(bounds);

    bgLight.addChild(light1);
    su.addBranchGraph(bgLight);
  }



}