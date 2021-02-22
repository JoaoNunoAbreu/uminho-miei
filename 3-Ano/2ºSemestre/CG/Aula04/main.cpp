#include <stdio.h>
#define GL_SILENCE_DEPRECATION
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glew.h>
#include <GL/glut.h>
#endif

#define _USE_MATH_DEFINES
#include <math.h>
#include <stdio.h>
#include <vector>

using namespace std;

float alfa = 0.0f, beta = 0.0f, radius = 5.0f;
float camX, camY, camZ;

GLuint vertices;
int verticeCount = 0;

GLuint indices;
unsigned int indexCount;

vector<float> p;

int timebase = 0;
int frame = 0;


void spherical2Cartesian() {

    camX = radius * cos(beta) * sin(alfa);
    camY = radius * sin(beta);
    camZ = radius * cos(beta) * cos(alfa);
}

void prepareData(){
    // criar um vector com as coordenadas dos pontos
    
    verticeCount = p.size()/3;
    
    // criar VBO
    
    glGenBuffers(1, &vertices);
    
    // copiar o vector para a memória gráfica
    glBindBuffer(GL_ARRAY_BUFFER, vertices);
    glBufferData(
                 GL_ARRAY_BUFFER, // tipo do buffer, só é relevante na altura do desenho
                 sizeof(float) * p.size(), // tamanho do vector em bytes
                 p.data(), // os dados do array associado ao vector
                 GL_STATIC_DRAW); // indicativo da utilização (estático e para desenho)
}

void prepareData2(){
    // criar um vector com as coordenadas dos pontos
    vector<unsigned int> i;
    
    i.push_back(-1);
    i.push_back(1);
    i.push_back(0);
    
    i.push_back(1);
    i.push_back(-1);
    i.push_back(0);
    
    i.push_back(1);
    i.push_back(1);
    i.push_back(0);
    
    verticeCount = i.size()/3;
    
    glGenBuffers(1, &indices);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indices);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,
                 sizeof(unsigned int) * i.size(),
                 i.data(),
                 GL_STATIC_DRAW);
    indexCount = i.size();
}

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


void cylinder(float radius, float height, int sides) {

    int i;
    float step;

    step = 360.0 / sides;

    glBegin(GL_TRIANGLES);

    // top
    for (i = 0; i < sides; i++) {
        glVertex3f(0, height*0.5, 0);
        glVertex3f(cos(i * step * M_PI / 180.0)*radius, height*0.5, -sin(i * step *M_PI / 180.0)*radius);
        glVertex3f(cos((i+1) * step * M_PI / 180.0)*radius, height*0.5, -sin((i + 1) * step *M_PI / 180.0)*radius);
    }

    // bottom
    for (i = 0; i < sides; i++) {
        glVertex3f(0, -height*0.5, 0);
        glVertex3f(cos((i + 1) * step * M_PI / 180.0)*radius, -height*0.5, -sin((i + 1) * step *M_PI / 180.0)*radius);
        glVertex3f(cos(i * step * M_PI / 180.0)*radius, -height*0.5, -sin(i * step *M_PI / 180.0)*radius);
    }

    // body
    for (i = 0; i <= sides; i++) {

        glVertex3f(cos(i * step * M_PI / 180.0)*radius, height*0.5, -sin(i * step *M_PI / 180.0)*radius);
        glVertex3f(cos(i * step * M_PI / 180.0)*radius, -height*0.5, -sin(i * step *M_PI / 180.0)*radius);
        glVertex3f(cos((i + 1) * step * M_PI / 180.0)*radius, height*0.5, -sin((i + 1) * step *M_PI / 180.0)*radius);

        glVertex3f(cos(i * step * M_PI / 180.0)*radius, -height*0.5, -sin(i * step *M_PI / 180.0)*radius);
        glVertex3f(cos((i + 1) * step * M_PI / 180.0)*radius, -height*0.5, -sin((i + 1) * step *M_PI / 180.0)*radius);
        glVertex3f(cos((i + 1) * step * M_PI / 180.0)*radius, height*0.5, -sin((i + 1) * step *M_PI / 180.0)*radius);
    }
    glEnd();
}

void cylinder2(float radius, float height, int sides) {

    int i;
    float step;

    step = 360.0 / sides;



    // top
    for (i = 0; i < sides; i++) {
        p.push_back(0);p.push_back(height*0.5);p.push_back(0);
        p.push_back(cos(i * step * M_PI / 180.0)*radius);p.push_back(height*0.5);p.push_back(-sin(i * step *M_PI / 180.0)*radius);
        p.push_back(cos((i+1) * step * M_PI / 180.0)*radius);p.push_back(height*0.5);p.push_back(-sin((i + 1) * step *M_PI / 180.0)*radius);
    }

    // bottom
    for (i = 0; i < sides; i++) {
        p.push_back(0);p.push_back(-height*0.5);p.push_back(0);
        p.push_back(cos((i + 1) * step * M_PI / 180.0)*radius); p.push_back(-height*0.5); p.push_back(-sin((i + 1) * step *M_PI / 180.0)*radius);
        p.push_back(cos(i * step * M_PI / 180.0)*radius);p.push_back(-height*0.5);p.push_back(-sin(i * step *M_PI / 180.0)*radius);
    }

    // body
    for (i = 0; i <= sides; i++) {

        p.push_back(cos(i * step * M_PI / 180.0)*radius);p.push_back(height*0.5);p.push_back(-sin(i * step *M_PI / 180.0)*radius);
        p.push_back(cos(i * step * M_PI / 180.0)*radius);p.push_back(-height*0.5);p.push_back(-sin(i * step *M_PI / 180.0)*radius);
        p.push_back(cos((i + 1) * step * M_PI / 180.0)*radius); p.push_back(height*0.5); p.push_back(-sin((i + 1) * step *M_PI / 180.0)*radius);

        p.push_back(cos(i * step * M_PI / 180.0)*radius); p.push_back(-height*0.5); p.push_back(-sin(i * step *M_PI / 180.0)*radius);
        p.push_back(cos((i + 1) * step * M_PI / 180.0)*radius); p.push_back(-height*0.5);p.push_back(-sin((i + 1) * step *M_PI / 180.0)*radius);
        p.push_back(cos((i + 1) * step * M_PI / 180.0)*radius); p.push_back(height*0.5);p.push_back(-sin((i + 1) * step *M_PI / 180.0)*radius);
    }
}



void renderScene(void) {

    int time = 0;
    float fps = 0.0f;
    char s[64];
    
    // clear buffers
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // set the camera
    glLoadIdentity();
    gluLookAt(camX, camY, camZ,
        0.0, 0.0, 0.0,
        0.0f, 1.0f, 0.0f);
    
    prepareData();
    glBindBuffer(GL_ARRAY_BUFFER, vertices);
    glVertexPointer(3, GL_FLOAT, 0, 0);
    glDrawArrays(GL_TRIANGLES, 0, verticeCount);
    
    /*glBindBuffer(GL_ARRAY_BUFFER,vertices);
    glVertexPointer(3,GL_FLOAT,0,0);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indices);
    glDrawElements(GL_TRIANGLES,
                   indexCount, // número de índices a desenhar
                   GL_UNSIGNED_INT, // tipo de dados dos índices
                   0);// parâmetro não utilizado*/

    
    frame++;
    time = glutGet(GLUT_ELAPSED_TIME);
    
    if (time - timebase > 1000) {
        fps = frame*1000.0 / (time - timebase);
        timebase = time;
        frame = 0;
        sprintf(s, "FPS: %f6.2", fps);
        glutSetWindowTitle(s);
    }
    
    // End of frame
    glutSwapBuffers();
}


void processKeys(unsigned char c, int xx, int yy) {

// put code to process regular keys in here

}


void processSpecialKeys(int key, int xx, int yy) {

    switch (key) {

    case GLUT_KEY_RIGHT:
        alfa -= 0.1; break;

    case GLUT_KEY_LEFT:
        alfa += 0.1; break;

    case GLUT_KEY_UP:
        beta += 0.1f;
        if (beta > 1.5f)
            beta = 1.5f;
        break;

    case GLUT_KEY_DOWN:
        beta -= 0.1f;
        if (beta < -1.5f)
            beta = -1.5f;
        break;

    case GLUT_KEY_PAGE_DOWN: radius -= 0.5f;
        if (radius < 0.1f)
            radius = 0.1f;
        break;

    case GLUT_KEY_PAGE_UP: radius += 0.5f; break;
    }
    spherical2Cartesian();
    glutPostRedisplay();

}


void printInfo() {

    printf("Vendor: %s\n", glGetString(GL_VENDOR));
    printf("Renderer: %s\n", glGetString(GL_RENDERER));
    printf("Version: %s\n", glGetString(GL_VERSION));

    printf("\nUse Arrows to move the camera up/down and left/right\n");
    printf("Home and End control the distance from the camera to the origin");
}


int main(int argc, char **argv) {
    
    cylinder2(1,2,10);

// init GLUT and the window
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DEPTH|GLUT_DOUBLE|GLUT_RGBA);
    glutInitWindowPosition(100,100);
    glutInitWindowSize(800,800);
    glutCreateWindow("CG@DI-UM");
        
// Required callback registry
    glutDisplayFunc(renderScene);
    glutReshapeFunc(changeSize);
    glutIdleFunc(renderScene);
    
// Callback registration for keyboard processing
    glutKeyboardFunc(processKeys);
    glutSpecialFunc(processSpecialKeys);

#ifndef __APPLE__
    glewInit();
#endif
    glEnableClientState(GL_VERTEX_ARRAY);

//  OpenGL settings
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glPolygonMode(GL_FRONT, GL_LINE);

    spherical2Cartesian();

    printInfo();

// enter GLUT's main cycle
    glutMainLoop();
    
    return 1;
}
