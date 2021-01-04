import java.util.*;
import java.io.*;
/**
 * Escreva a descrição da classe Main aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main{
    public static void main(String [] args){
        HotelPremium h1 = new HotelPremium(1,"nome1","local1",1,200,150.5,0.2);
        HotelStandard h2 = new HotelStandard(2,"nome2","local2",2,400,300.5,true);
        HotelDiscount h3 = new HotelDiscount(3,"nome3","local3",3,600,450.5,0.5);
        List hoteis = new ArrayList<Hotel>();
        
        h2.setPontos(2);
        h1.setPontos(3);
        //System.out.println(h2.getPontos());
        
        hoteis.add(h1);
        hoteis.add(h2);
        hoteis.add(h3);
        
        HoteisInc hoteisinc = new HoteisInc(hoteis);
        hoteisinc.adiciona(new HotelStandard(4,"nome4","local4",4,800,600,false));
        //System.out.println(hoteisinc.existeHotel("nome1"));
        //System.out.println(hoteisinc.quantos());
        //System.out.println(hoteisinc.quantos("local1"));
        //System.out.println(hoteisinc.quantosT("HotelStandard"));
        //System.out.println(hoteisinc.getHotel("nome1"));
        //hoteisinc.mudaPara(false);
        //for(Hotel h : hoteisinc.getHoteis())
        //    System.out.println(h.precoNoite());
        //System.out.println(hoteisinc.getHoteis());
        
        // 3
        //System.out.println(hoteisinc.daoPontos());
        
        // 4
        /*(1)
        try{
            hoteisinc.exportarCSV("Ficheiro.csv");
        }
        catch(IOException e){System.out.println(e);};*/
        
        // (2)
        
        /**
         * Gravar em ficheiros de texto
         */
        try{
            hoteisinc.save("Estado.txt");
        }
        catch(FileNotFoundException e){
            System.out.println("Ficheiro não encontrado");
        }
        catch(IOException e){
            System.out.println("Erro a aceder ficheiro");
        }
        /**
         * Gravar em ficheiros de objectos
         */
        /*try{
            hoteisinc.save("Estado.txt");
        }
        catch(FileNotFoundException e){
            System.out.println("Ficheiro não encontrado!");
        }
        catch(IOException e){
            System.out.println("Erro ao ler ficheiro");
        }*/
        /**
         * Carregar do ficheiro
         */
        HoteisInc novo = null;
        Scanner sc = new Scanner(System.in);
        while(novo == null){
            System.out.println("Insira o nome de ficheiro a ler:");
            String nome = sc.nextLine();
            try{
                novo = HoteisInc.carregar(nome);
            }
            catch (FileNotFoundException e){
                System.out.println("Ficheiro não encontrado");
            }
        }
        try{
            System.out.println(novo.toString());
        }
        catch(NullPointerException e){
            System.out.println("Os hoteis estão vazios");
        }
    }
}
