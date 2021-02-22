#ifdef __APPLE__
#define GL_SILENCE_DEPRECATION
#include <OpenGL/gl.h>
#include <GLUT/glut.h>
#else
#include <GL/gl.h>
#include <GL/glut.h>
#endif

#define _USE_MATH_DEFINES
#include <math.h>

float angle1 = 0;
float angle2 = 0;

float xpos = 0;
float zpos = 0;

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

void drawAxis(){
    
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
}

void drawCylinder(float r, float height, int slices) {
    float angulo = (2*M_PI) /slices;
    
    glBegin(GL_TRIANGLES);
        for(int i = 0; i < slices; i++){
            
            // Topo
            glColor3f(1,0,0);
            glVertex3f(0,height/2,0);
            glVertex3f(r*sin(angulo*i),height/2,r*cos(angulo*i));
            glVertex3f(r*sin(angulo*(i+1)),height/2,r*cos(angulo*(i+1)));

            glColor3f(0,1,0);
            glVertex3f(r*sin(angulo*i),height/2,r*cos(angulo*i));
            glVertex3f(r*sin(angulo*i),-height/2,r*cos(angulo*i));
            glVertex3f(r*sin(angulo*(i+1)),height/2,r*cos(angulo*(i+1)));


            glColor3f(0,0,1);
            glVertex3f(r*sin(angulo*(i+1)),height/2,r*cos(angulo*(i+1)));
            glVertex3f(r*sin(angulo*i),-height/2,r*cos(angulo*i));
            glVertex3f(r*sin(angulo*(i+1)),-height/2,r*cos(angulo*(i+1)));
            
            // Base
            glColor3f(1,0,1);
            glVertex3f(r*sin(angulo*i),-height/2,r*cos(angulo*i));
            glVertex3f(0,-height/2,0);
            glVertex3f(r*sin(angulo*(i+1)),-height/2,r*cos(angulo*(i+1)));
        }
    glEnd();
}

void renderScene(void) {

	// clear buffers
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// set the camera
	glLoadIdentity();
	gluLookAt(5*cos(angle2)*sin(angle1),5*sin(angle2),5*cos(angle2)*cos(angle1),
		      0+xpos,0,0+zpos,
			  0.0f,1.0f,0.0f);

    //glRotatef(angle1,0,1,0);
    //glRotatef(angle2,0,0,1);
    drawAxis();
    drawCylinder(1,1,10);
    
	// End of frame
	glutSwapBuffers();
}


void processKeys(unsigned char c, int xx, int yy) {

}


void processSpecialKeys(int key, int xx, int yy) {
    switch (key){
        case GLUT_KEY_LEFT:
            angle1 -= 0.05;
            break;
        case GLUT_KEY_RIGHT:
            angle1 += 0.05;
            break;
        case GLUT_KEY_UP:
            angle2 += 0.05;
            break;
        case GLUT_KEY_DOWN:
            angle2 -= 0.05;
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
	
// Callback registration for keyboard processing
	glutKeyboardFunc(processKeys);
	glutSpecialFunc(processSpecialKeys);

//  OpenGL settings
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_CULL_FACE);
	
// enter GLUT's main cycle
	glutMainLoop();
	
	return 1;
}
