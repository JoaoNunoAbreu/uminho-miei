import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe GestaoFB aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestaoFB{
    private Map<String,List<FBPost>> map;
    
    public GestaoFB(){
        this.map = new HashMap<>();
    }
    /*public GestaoFB(GestaoFB outro){
        setMap(outro);
    }*/
    public GestaoFB(Map<String,List<FBPost>> map){
        this.map = map;
    }
    public void addPost(String user, FBPost post){
        for(Map.Entry<String,List<FBPost>> entry : this.map.entrySet()){
            String str = entry.getKey();
            List<FBPost> lPosts = entry.getValue();
            if(str.equals(user)){
                lPosts.add(post);
            }
        }
    }
    public void removePosts(String user, LocalDateTime di, LocalDateTime df){
        for(Map.Entry<String,List<FBPost>> entry : this.map.entrySet()){
            String str = entry.getKey();
            List<FBPost> lPosts = entry.getValue();
            if(str.equals(user) && getData(lPosts))
        }
    }
}
