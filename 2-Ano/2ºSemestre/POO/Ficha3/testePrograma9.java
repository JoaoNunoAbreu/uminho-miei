
/**
 * Escreva a descrição da classe testePrograma9 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class testePrograma9
{
    public static void main(String [] args){
        Ponto a = new Ponto();
        Ponto b = new Ponto(0,18);
        Ponto c = new Ponto(24,0);
        Triangulo t = new Triangulo(a,b,c);
        System.out.println(t.toString());
        System.out.println("A área do triângulo é: " + t.calculaAreaTriangulo());
        System.out.println("O perímetro do triângulo é: " + t.calculaPerimetroTriangulo());
        System.out.println("A altura do triângulo é: " + t.alturaTriangulo());
    }
}
