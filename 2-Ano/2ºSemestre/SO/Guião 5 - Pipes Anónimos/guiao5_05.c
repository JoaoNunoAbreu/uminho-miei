#include <unistd.h>   /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>    /* O_RDONLY, O_WRONLY, O_CREAT, O_* */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */
#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[])
{
  //vou precisar de 3 pipes -> grep | cut | uniq | wc (reparar nos 3 |)
  int p[3][2];

  //crio primeiro so 1 pipe, para nao ter de fechar os outros 2
  //porque ainda nao preciso deles
  pipe(p[0]);

  int f_ret = -1;
  if ((f_ret = fork()) == 0) //filho
  {
    //nao vou ler do p[0] 0
    close(p[0][0]);
    //vou escrever para o p[0] 1
    dup2(p[0][1], 1);
    //fecho o p[0] 1 porque o stdout (1) ja aponta para ele
    //porque fiz dup2
    close(p[0][1]);
    execlp("grep", "grep", "-v", "^#", "/etc/passwd", NULL);
    _exit(0); //depois do exec so executa isto se o exec falhar
  }
  f_ret = -1;
  //vou precisar de um novo pipe por isso abro agora
  pipe(p[1]);

  if ((f_ret = fork()) == 0) //filho
  {
    //nao vou escrever para p[0] 1
    close(p[0][1]);
    //vou ler de p[0] 0 que e o que o processo anterior escreveu
    //em p[0] 1
    dup2(p[0][0], 0);
    close(p[0][0]);

    //nao vou ler nada de p[1] 0
    close(p[1][0]);
    //vou escrever para p[1] 1
    dup2(p[1][1], 1);
    close(p[1][1]);

    execlp("cut", "cut", "-f7", "-d:", NULL);
    _exit(0);
  }
  f_ret = -1;
  //nao vou precisar mais de p[0] por isso fecho este pipe
  //para nao ter de fechar de cada vez que faco fork
  close(p[0][0]);
  close(p[0][1]);

  pipe(p[2]);
  if ((f_ret = fork()) == 0) //filho
  {
    close(p[1][1]);
    dup2(p[1][0], 0);
    close(p[1][0]);
    close(p[2][0]);
    dup2(p[2][1], 1);
    close(p[2][1]);
    execlp("uniq", "uniq", NULL);
    _exit(0);
  }
  f_ret = -1;
  close(p[1][0]);
  close(p[1][1]);
  if ((f_ret = fork()) == 0) //filho
  {
    close(p[2][1]);
    dup2(p[2][0], 0);
    close(p[2][0]);
    execlp("wc", "wc", "-l", NULL);
    _exit(0);
  }
  //nao fecho p[0] nem p[1] porque ja fechei antes, >>entre os forks!!<<
  //se fechar so dentro dos forks tambem preciso de fechar aqui no fim!!
  close(p[2][0]);
  close(p[2][1]);
  wait(0);
  wait(0);
  wait(0);
  wait(0);
  return 0;
}
