
package clippingplanes;

import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;

public class ClippingPlanes extends JFrame {

    //The canvas to be drawn upon.
  public Canvas3D myCanvas3D;



  public ClippingPlanes()
  {
    //Mechanism for closing the window and ending the program.
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    //Default settings for the viewer parameters.
    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());


    //Construct the SimpleUniverse:
    //First generate it using the Canvas.
    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);


    //Default position of the viewer.
    simpUniv.getViewingPlatform().setNominalViewingTransform();


    //The scene is generated in this method.
    createSceneGraph(simpUniv);


    //Add some light to the scene.
    addLight(simpUniv);


    //The following three lines enable navigation through the scene using the mouse.
    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


    //The View of the SimpleUniverse.
    View v = simpUniv.getViewer().getView();
    //The angle of view is reduced to 30 degrees.
    v.setFieldOfView(Math.PI/6);
    //The back clipping plane is shifted closer to the viewer.
    v.setBackClipDistance(0.345);
    //The front clipping plane is shifted away from the viewer.
    v.setFrontClipDistance(0.33);


    //Show the canvas/window.
    setTitle("Static scene with elementary objects badly clipped");
    setSize(700,700);
    getContentPane().add("Center", myCanvas3D);
    setVisible(true);

  }




  public static void main(String[] args)
  {
     ClippingPlanes cp = new ClippingPlanes();
  }





  //In this method, the objects for the scene are generated and added to 
  //the SimpleUniverse.
  public void createSceneGraph(SimpleUniverse su)
  {

//*** The platform on which the helicopter stands ***
//*** and its transformation group.               ***

    //An Appearance to make the platform red.
    Appearance redApp = new Appearance();
    setToMyDefaultAppearance(redApp,new Color3f(0.8f,0.0f,0.0f));


    //Half edge length of the cube that represents the platform.
    float platformSize = 0.1f;

    //Generate the platform in the form of a cube.
    Box platform = new Box(platformSize,platformSize,platformSize,redApp);

    //A transformation rotating the platform a little bit.
    Transform3D tfPlatform = new Transform3D();
    tfPlatform.rotY(Math.PI/6);

    //The transformation group of the platform.
    TransformGroup tgPlatform = new TransformGroup(tfPlatform);
    tgPlatform.addChild(platform);





//*** The cockpit of the helicopter. ****

    //An Appearance to make the cockpit green.
    Appearance greenApp = new Appearance();
    setToMyDefaultAppearance(greenApp,new Color3f(0.0f,0.7f,0.0f));

    //Radius of the cockpit.
    float cabinRadius = 0.1f;

    //Generate the cockpit in the form of a sphere.
    Sphere cabin = new Sphere(cabinRadius,greenApp);

    //The transformation group for the cockpit.
    //The cockpit first remains in the origin. Later on, the whole
    //helicopter is shifted onto the platform.
    TransformGroup tgCabin = new TransformGroup();
    tgCabin.addChild(cabin);


//*** The rotor blade of the helicopter. ***

    //An Appearance to make the rotor blade blue.
    Appearance blueApp = new Appearance();
    setToMyDefaultAppearance(blueApp,new Color3f(0.0f,0.0f,1.0f));

    //Generate the rotor blade in the form of a (very thin and long) box.
    Box rotor = new Box(0.4f,0.0001f,0.01f,blueApp);

    //A transformation placing the rotor blade on top of the cockpit.
    Transform3D tfRotor = new Transform3D();
    tfRotor.setTranslation(new Vector3f(0.0f,cabinRadius,0.0f));

    //The transformation group for the rotor blade.
    TransformGroup tgRotor = new TransformGroup(tfRotor);
    tgRotor.addChild(rotor);


//*** The tail of the helicopter. ***

    //Length of the tail.
    float halfTailLength = 0.2f;

    //Generate the tail in form of a green box.
    Box tail = new Box(halfTailLength,0.02f,0.02f,greenApp);

    //A transformation placing the tail at the end of the cockpit.
    Transform3D tfTail = new Transform3D();
    tfTail.setTranslation(new Vector3f(cabinRadius+halfTailLength,0.0f,0.0f));

    //The transformation group for the tail.
    TransformGroup tgTail = new TransformGroup(tfTail);
    tgTail.addChild(tail);



//*** The helicopter, assembled by the transformation groups  ***
//*** for the cockpit, the rotor blade and the tail.          ***
//*** Afterwards, the helicopter must be lifted onto the      ***
//*** platform.                                               ***

    //The transformation placing the helicopter on top of the platform.
    Transform3D tfHelicopter = new Transform3D();
    tfHelicopter.setTranslation(new Vector3f(0.0f,platformSize+cabinRadius,0.0f));

    //The transformation group for the helicopter.
    TransformGroup tgHelicopter = new TransformGroup(tfHelicopter);
    tgHelicopter.addChild(tgCabin);
    tgHelicopter.addChild(tgRotor);
    tgHelicopter.addChild(tgTail);


//*** The helicopter and the platform are joint together in one    ***
//*** transformation group in order to place them together         ***
//*** in the scene.                                                ***
    //The transformation for positioning the helicopter
    //together with the platform.
    Transform3D tfHeliPlat = new Transform3D();
    tfHeliPlat.setTranslation(new Vector3f(0.0f,0.1f,0.0f));

    //The transformation group for the helicopter together with the platform.
    TransformGroup tgHeliPlat = new TransformGroup(tfHeliPlat);
    tgHeliPlat.addChild(tgHelicopter);
    tgHeliPlat.addChild(tgPlatform);



//*** The tree trunk. ***

    //An Appearance to make the tree trunk brown.
    Appearance brownApp = new Appearance();
    setToMyDefaultAppearance(brownApp,new Color3f(0.5f,0.2f,0.2f));

    //Height of the tree trunk.
    float trunkHeight = 0.4f;

    //Generate the tree trunk as a cylinder.
    Cylinder trunk = new Cylinder(0.05f,trunkHeight,brownApp);

    //The transformation group of the tree trunk. The tree trunk first
    //remains in the origin and is placed in the scene together with the 
    //leaves.
    TransformGroup tgTrunk = new TransformGroup();
    tgTrunk.addChild(trunk);


//*** The treetop. ***

    //Height of the treetop.
    float leavesHeight = 0.4f;

    //Generate the treetop in the form of a green cone.
    Cone leaves = new Cone(0.3f,leavesHeight,greenApp);

    //A transformation to place the treetop on top of the tree trunk.
    Transform3D tfLeaves = new Transform3D();
    tfLeaves.setTranslation(new Vector3f(0.0f,(trunkHeight+leavesHeight)/2,0.0f));

    //The transformation group of the treetop.
    TransformGroup tgLeaves = new TransformGroup(tfLeaves);
    tgLeaves.addChild(leaves);


//*** The tree assembled from the transformation groups for     ***
//*** the tree trunk and the treetop.                           ***

    //A transformation for positioning the tree in the scene.
    Transform3D tfTree = new Transform3D();
    tfTree.setTranslation(new Vector3f(-0.6f,0.0f,0.0f));

    //The transformation group of the tree.
    TransformGroup tgTree = new TransformGroup(tfTree);
    tgTree.addChild(tgTrunk);
    tgTree.addChild(tgLeaves);


//*** The root of the graph containing the scene. ***
    BranchGroup theScene = new BranchGroup();


    //Add the helicopter and the tree to the scene.
    theScene.addChild(tgHeliPlat);
    theScene.addChild(tgTree);


    //The following four lines generate a white background.
    Background bg = new Background(new Color3f(0.0f,0.4f,0.7f));
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
    bg.setApplicationBounds(bounds);
    theScene.addChild(bg);


    theScene.compile();

    //Add the scene to the universe.
    su.addBranchGraph(theScene);

  }


  /**
  * Generates a default surface (Appearance) in a specified colour.
  *
  * @param app      The Appearance for the surface.
  * @param col      The colour.
  */
  public static void setToMyDefaultAppearance(Appearance app, Color3f col)
  {
    app.setMaterial(new Material(col,col,col,col,120.0f));
  }



  //Some light is added to the scene here.
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
