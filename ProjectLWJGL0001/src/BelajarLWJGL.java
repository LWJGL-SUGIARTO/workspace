import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
 
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Belajar Java dengan Library LWJGL
 * @author o9ie.blogspot.com
 */

public class BelajarLWJGL {

    public static final int DISPLAY_HEIGHT = 480;
    public static final int DISPLAY_WIDTH = 640;
    
    private float rtri;
    
    private long lastTime;
    private int fps;
        
    public static void main(String[] args) {
        BelajarLWJGL main = null;
        try {
            main = new BelajarLWJGL();
            main.create();
            main.run();
        }catch(Exception e){}
        
        if(main != null) {
            main.destroy();
        }
    }
    
    public BelajarLWJGL() {
        //tambahkan inisiasi sesukanya
    }
    
    public void create() throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setTitle("Belajar LWJGL");
        Display.setFullscreen(false);
        Display.create();
        
        initGL();
        resizeGL();
    }
    
    public void destroy() {
        Display.destroy();
    }
    
    public void initGL() {
        glShadeModel(GL_SMOOTH);                // Enables Smooth Shading
 glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   // Black Background
        glClearDepth(1.0f);                     // Depth Buffer Setup 
        glEnable(GL_DEPTH_TEST);                // Enables Depth Testing 
        glDepthFunc(GL_LEQUAL);                 // The Type Of Depth Test To Do
        
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);          // Really Nice Perspective Calculations
        
        lastTime = Sys.getTime();
    }
    
    public void resizeGL() {
        glViewport(0,0,DISPLAY_WIDTH,DISPLAY_HEIGHT);
        
        glMatrixMode(GL_PROJECTION);    // Select The Projection Matrix 
        glLoadIdentity();               // Reset The Projection Matrix 

        // Calculate The Aspect Ratio Of The Window 
        gluPerspective(45.0f,(float)DISPLAY_WIDTH/(float)DISPLAY_HEIGHT,0.1f,100.0f); 

        glMatrixMode(GL_MODELVIEW);     // Select The Modelview Matrix 
        glLoadIdentity();               // Reset The Modelview Matrix
    }
 
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        
        glTranslated(0.0f,0.0f,-5.0f);      // Translasi piramid ke belakang
        glRotatef(rtri,0.0f,1.0f,0.0f);     // Rotate The Pyramid On It's Y Axis
        
        glBegin(GL_TRIANGLES);              // Start Drawing The Pyramid
        glColor3f(1.0f,0.0f,0.0f);          // Red
        glVertex3f( 0.0f, 1.0f, 0.0f);      // Top Of Triangle (Front)
        glColor3f(0.0f,1.0f,0.0f);          // Green
        glVertex3f(-1.0f,-1.0f, 1.0f);      // Left Of Triangle (Front)
        glColor3f(0.0f,0.0f,1.0f);          // Blue
        glVertex3f( 1.0f,-1.0f, 1.0f);      // Right Of Triangle (Front)
        
        glColor3f(1.0f,0.0f,0.0f);          // Red
        glVertex3f( 0.0f, 1.0f, 0.0f);      // Top Of Triangle (Right)
        glColor3f(0.0f,0.0f,1.0f);          // Blue
        glVertex3f( 1.0f,-1.0f, 1.0f);      // Left Of Triangle (Right)
        glColor3f(0.0f,1.0f,0.0f);          // Green
        glVertex3f( 1.0f,-1.0f, -1.0f);     // Right Of Triangle (Right)
        
        glColor3f(1.0f,0.0f,0.0f);          // Red
        glVertex3f( 0.0f, 1.0f, 0.0f);      // Top Of Triangle (Back)
        glColor3f(0.0f,1.0f,0.0f);          // Green
        glVertex3f( 1.0f,-1.0f, -1.0f);     // Left Of Triangle (Back)
        glColor3f(0.0f,0.0f,1.0f);          // Blue
        glVertex3f(-1.0f,-1.0f, -1.0f);     // Right Of Triangle (Back)

        glColor3f(1.0f,0.0f,0.0f);          // Red
        glVertex3f( 0.0f, 1.0f, 0.0f);      // Top Of Triangle (Left)
        glColor3f(0.0f,0.0f,1.0f);          // Blue
        glVertex3f(-1.0f,-1.0f,-1.0f);      // Left Of Triangle (Left)
        glColor3f(0.0f,1.0f,0.0f);          // Green
        glVertex3f(-1.0f,-1.0f, 1.0f);      // Right Of Triangle (Left)
        glEnd();                            // Done Drawing The Pyramid
        
        rtri+=0.2f;
    }
 
  public void run() {
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if(Display.isVisible()) {
                update();
                render();
            }
            else {
                if(Display.isDirty()) {
                    render();
                }
                try {
                    Thread.sleep(100);
                }
                catch(InterruptedException ex) {
                }
            }
            Display.update();
            Display.sync(60); //fps --> 60
        }
    }
    
    public void update() {
        updateFPS();
    }
    
    public void updateFPS(){
        if(Sys.getTime()-lastTime>1000){
            Display.setTitle("Belajar LWJGL fps: "+fps);
            fps = 0;
            lastTime = Sys.getTime();
        }
        fps++;
    }
}