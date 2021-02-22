#ifdef __APPLE__
#define GL_SILENCE_DEPRECATION
#include <OpenGL/gl.h>
#include <GLUT/glut.h>
#else
#include <GL/gl.h>
#include <GL/glut.h>
#endif

#include <math.h>

float yr = 0;
float angle = 0;

void changeSize(int w, int h) {

	// Prevent a divide by zero, when window is too short
	// (you cant make a window with zero width).
	if(h == 0)
		h = 1;

	// compute window's aspect ratio 
	float ratio = w * 1.0 / h;

	// Set the projection matrix as current
	glMatrixMode(GL_PROJECTION);
	// Load Identity Matrix
	glLoadIdentity();
	
	// Set the viewport to be the entire window
    glViewport(0, 0, w, h);

	// Set perspective
	gluPerspective(45.0f ,ratio, 1.0f ,1000.0f);

	// return to the model view matrix mode
	glMatrixMode(GL_MODELVIEW);
}


void renderScene(void) {

	// clear buffers
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// set the camera
	glLoadIdentity();
	gluLookAt(5,5,5,
		      0,0,0,
			  0,1,0);

// put the geometric transformations here

// put drawing instructions here
    
    glBegin(GL_LINES);
        // X axis in red
        glColor3f(1.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(100.0f, 0.0f, 0.0f);
        // Y Axis in Green
        glColor3f(0.0f, 1.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 100.0f, 0.0f);
        // Z Axis in Blue
        glColor3f(0.0f, 0.0f, 1.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 100.0f);
    glEnd();
    
    glRotatef(angle,0,1,0);
    angle+=999999999999999999;
    glTranslated(0, yr, 0);

    glBegin(GL_TRIANGLES);
        // Front
        glColor3f(1,0.5,0);
        glVertex3f(0, 1, 0);
        glVertex3f(-1, 0, 1);
        glVertex3f(1, 0, 1);
 
        // Right
        glColor3f(0.5  , 0.5 ,0);
        glVertex3f(0 , 1 , 0);
        glVertex3f(1 , 0 , 1);
        glVertex3f(1 , 0 , -1);
 
        // Back
        glColor3f(1,0,0.5);
        glVertex3f(0, 1, 0);
        glVertex3f(1, 0, -1);
        glVertex3f(-1, 0, -1);
 
        // Left
        glColor3f(0.5, 0.5 ,0.5);
        glVertex3f(0 , 1 , 0);
        glVertex3f(-1 , 0 , -1);
        glVertex3f(-1 , 0 , 1);
        
        // Base
        glColor3f(1, 0 ,0);
        glVertex3f(1 , 0 , 1);
        glVertex3f(-1 , 0 , 1);
        glVertex3f(-1 , 0 , -1);
    
        glColor3f(1, 0 ,0);
        glVertex3f(-1 , 0 , -1);
        glVertex3f(1 , 0 , -1);
        glVertex3f(1 , 0 , 1);
            
    glEnd();   // Done drawing the pyramid
	// End of frame
	glutSwapBuffers();
    
}


// write function to process keyboard events

void processSpecialKeys(int key, int x, int y) {
    switch (key){
        case GLUT_KEY_LEFT:
            angle -= 1;
            break;
        case GLUT_KEY_RIGHT:
            angle += 1;
            break;
        case GLUT_KEY_UP:
            yr += 1;
            break;
        case GLUT_KEY_DOWN:
            yr -= 1;
        break;
    }
    glutPostRedisplay();
}


int main(int argc, char **argv) {

// init GLUT and the window
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DEPTH|GLUT_DOUBLE|GLUT_RGBA);
	glutInitWindowPosition(100,100);
    glutInitWindowSize(1000,1000);
	glutCreateWindow("CG@DI-UM");
		
// Required callback registry 
	glutDisplayFunc(renderScene);
	glutReshapeFunc(changeSize);

	
// put here the registration of the keyboard callbacks
    glutSpecialFunc(processSpecialKeys);

//  OpenGL settings
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_CULL_FACE);
	
// enter GLUT's main cycle
	glutMainLoop();
	
	return 1;
}
