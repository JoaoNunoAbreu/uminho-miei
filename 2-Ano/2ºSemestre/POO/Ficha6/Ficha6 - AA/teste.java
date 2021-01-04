import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe testePrograma4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class teste
{
    public static void main(String [] args){
        ArrayList<String> comments = new ArrayList<>();
        comments.add("Teste1");
        comments.add("Teste2");
        comments.add("Teste3");
        
        FBPost a1 = new FBPost(123,"Joao",LocalDateTime.now(),"Hello World",2,comments);
        FBPost a2 = new FBPost(444,"Joao",LocalDateTime.now(),"Bye World",100,comments);
        FBPost a3 = new FBPost();
        
        ArrayList<FBPost> posts = new ArrayList<>();
        posts.add(a1);
        posts.add(a2);
        posts.add(a3);
        FBFeed feed = new FBFeed(posts);
        
        System.out.println(feed.toString());
        System.out.println("-----------------");
        System.out.println("O user Joao fez " + feed.nrPosts("Joao") + " posts.");
        System.out.println("O user Joao fez os seguintes posts " + feed.postsOf("Joao"));
        System.out.println("-----------------");
        System.out.println(feed.getPost(555));
        System.out.println("-----------------");
        feed.comment2(a3,"vazio");
        feed.comment3(444,"AAAAAAAA");
        feed.like(a3);
        feed.like(444);
        System.out.println(feed.toString());
    }
}
