/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Font;
import java.net.MalformedURLException;
import javax.swing.JFrame;

public class TrabalhoFinal extends JFrame {

  
  public Canvas3D myCanvas3D;


  public TrabalhoFinal() throws MalformedURLException
  {
 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());


    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);


    simpUniv.getViewingPlatform().setNominalViewingTransform();


    createSceneGraph(simpUniv);


    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.4,0.3,1.0),Double.MAX_VALUE));
    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


    setTitle("Trabalho Final - Computação Gráfica: Planeta Terra com Spotlight imitando luz do Sol");
    setSize(700,700);
    getContentPane().add("Center", myCanvas3D);
    setVisible(true);

  }




  public static void main(String[] args) throws MalformedURLException
  {
     TrabalhoFinal tf = new TrabalhoFinal();
  }




  public void createSceneGraph(SimpleUniverse su) throws MalformedURLException
  {

   
    BranchGroup theScene = new BranchGroup();

    TextureLoader textureLoad = new TextureLoader("C:\\Users\\Micro\\Documents\\NetBeansProjects\\TrabalhoFinal\\earth.jpg",null);

  
    ImageComponent2D textureIm = textureLoad.getScaledImage(64,128);


    Texture2D aTexture = new Texture2D(Texture2D.BASE_LEVEL,Texture2D.RGB,
                                            textureIm.getWidth(),
                                            textureIm.getHeight());
    aTexture.setImage(0,textureIm);

    //An Appearance which will use the texture.
    Appearance textureApp = new Appearance();
    textureApp.setTexture(aTexture);
    TextureAttributes textureAttr = new TextureAttributes();
    textureAttr.setTextureMode(TextureAttributes.REPLACE);
    textureApp.setTextureAttributes(textureAttr);
    Material mat = new Material();
    mat.setShininess(120.0f);
    textureApp.setMaterial(mat);
    TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
                                                    TexCoordGeneration.TEXTURE_COORDINATE_2);

    textureApp.setTexCoordGeneration(tcg);


    Color3f ambientColourSphere = new Color3f(0.0f,0.2f,0.2f);
    Color3f emissiveColourSphere = new Color3f(0.0f,0.5f,0.7f);
    Color3f diffuseColourSphere = new Color3f(0.6f,0.6f,0.6f);
    Color3f specularColourSphere = new Color3f(0.5f,0.5f,0.5f);

    float shininessSphere = 20.0f;

    Appearance sphereApp = new Appearance();

    sphereApp.setMaterial(new Material(ambientColourSphere,emissiveColourSphere,
                           diffuseColourSphere,specularColourSphere,shininessSphere));

    ColoringAttributes ca = new ColoringAttributes();
    ca.setShadeModel(ColoringAttributes.SHADE_FLAT);
    sphereApp.setColoringAttributes(ca);


 
    int n = 10;
    float r = 0.15f;
    float shift = 2*r+0.05f;

    Sphere spheres = new Sphere();
    TransformGroup[] tg = new TransformGroup[n];
    Transform3D[] tf = new Transform3D[n];
        

    for (int i=0; i<n; i++)
    {
      spheres = new Sphere(r,Sphere.GENERATE_NORMALS,4+i*i*i,textureApp);
      tf[i] = new Transform3D();
      tg[i] = new TransformGroup(tf[i]);
      tg[i].addChild(spheres);
      theScene.addChild(tg[i]);
    }
    
     for (int i=0; i<n; i++)
    {
      spheres = new Sphere(r,Sphere.GENERATE_NORMALS,8+i*i*i,sphereApp);
      tf[i] = new Transform3D();
      tg[i] = new TransformGroup(tf[i]);
      tg[i].addChild(spheres);
      theScene.addChild(tg[i]);
    }
    BoundingSphere bounds =
	       new BoundingSphere(new Point3d(0.0,0.0,0.0), 150.0);

    
    Color3f lightColourSpot = new Color3f(0.3f, 0.3f, 0.3f);
    SpotLight lightSpot = new SpotLight(lightColourSpot,
                                     new Point3f(0.0f,0.0f,1.0f),
                                     new Point3f(0.1f,0.1f,0.01f),
                                     new Vector3f(0.0f,0.0f,-1.0f),
                                     (float) (Math.PI/4),
                                     0.0f);

    lightSpot.setInfluencingBounds(bounds);

    TransformGroup tfmLight = new TransformGroup();
    BranchGroup bgLight = new BranchGroup();
    tfmLight.addChild(lightSpot);

    Alpha alphaLight = new Alpha(-1,4000);

    RotationInterpolator rot = new RotationInterpolator(alphaLight,tfmLight,
                                                        new Transform3D(),
                                                         0.0f,(float) Math.PI*2);
    rot.setSchedulingBounds(bounds);

    tfmLight.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tfmLight.addChild(rot);

    bgLight.addChild(tfmLight);

    Background bg = new Background(new Color3f(0.0f,0.0f,0.0f));
    
    bg.setApplicationBounds(bounds);
    theScene.addChild(bg);
    Color3f ambientLightColour = new Color3f(0.5f, 0.5f, 0.5f);
    AmbientLight ambLight = new AmbientLight(ambientLightColour);
    ambLight.setInfluencingBounds(bounds);
    bgLight.addChild(ambLight);


    su.addBranchGraph(bgLight);
    
    theScene.compile();

    su.addBranchGraph(theScene);

  }

}
