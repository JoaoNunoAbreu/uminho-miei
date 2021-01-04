
/**
 * Escreva a descrição da classe testePrograma5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma5
{
    public static void main(String [] args){
        Futebol f = new Futebol();
        System.out.println(f.toString());
        f.startGame();
        f.goloVisitado();
        f.goloVisitante();
        System.out.println(f.toString());
    }
}
