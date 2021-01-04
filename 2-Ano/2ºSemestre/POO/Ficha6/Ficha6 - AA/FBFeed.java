import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.Iterator;
/**
 * Escreva a descrição da classe FBFeed aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class FBFeed
{
    private List<FBPost> feed;
    public FBFeed(){
        this.feed = new ArrayList<>();
    } 
    public FBFeed(List<FBPost> feed){
        setFeed(feed);
    }
    public FBFeed(FBFeed outro){
        this.feed = outro.getFeed();
    }
    public List<FBPost> getFeed(){
        List<FBPost> res = new ArrayList<>();
        feed.stream().forEach(s -> res.add(s));
        return res;
    }
    public void setFeed(List<FBPost> f){
        this.feed = new ArrayList<>();
        for(FBPost a : f)
            this.feed.add(a);
    }
    public String toString(){
        return this.feed.toString();
    }
    public boolean equals(Object o){
        if(o == this) return false;
        if(o == null || o.getClass() != this.getClass()) return false;
        FBFeed f = (FBFeed) o;
        return this.equals(f);
    }
    public FBFeed clone(){
        return new FBFeed(this);
    }
    // (a)
    public int nrPosts(String user){
        int count = 0;
        for(FBPost a : this.feed)
            if(a.getUsername().equals(user)) count++;
        return count;
    }
    // OU
    public int nrPosts2(String user){
        return (int) feed.stream()
                         .filter(p->p.getUsername().equals(user))
                         .count();
    }
    // (b)
    public List<FBPost> postsOf(String user){
        List<FBPost> postList = new ArrayList<>();
        for(FBPost a : this.feed)
            if(a.getUsername().equals(user)){
                postList.add(a.clone());
            }
        return postList;    
    }
    // OU
    public List<FBPost> postsOf2(String user){
        return feed.stream()
                   .filter(p->p.getUsername().equals(user))
                   .collect(Collectors.toList());
    }
    // (c)
    public List<FBPost> postsOf(String user,LocalDateTime inicio, LocalDateTime fim){
        List<FBPost> postList = new ArrayList<>();
        for(FBPost a : this.feed)
            if(a.getUsername().equals(user) && a.getData().isAfter(inicio) && a.getData().isBefore(fim)){
                postList.add(a.clone());
            }
        return postList;
    }
    // (d)
    public FBPost getPost(int id){
        for(FBPost a : this.feed)
            if(a.getId() == id) return a.clone();
        return null;        
    }
    // OU
    public FBPost getPost1(int id){
        FBPost r = null;
        Iterator<FBPost> it = this.feed.iterator();
        while(it.hasNext() && r != null){
            FBPost p = it.next();
            if(p.getId() == id) r = p.clone();
        }
        return r;
    }
    // OU
    public FBPost getPost2(int id){
        return this.feed.stream()
                        .filter(p->p.getId()==id)
                        .findFirst().get().clone();
    }
    // OU
    /**
     * Devolve referência para um FBPost
     * Private, apenas é utilizado por outros métodos desta classe
     */
    private FBPost getPostFP(int id){
        return this.feed.stream()
                   .filter(p->p.getId()==id)
                   .findFirst().get();
    }
    public FBPost getPost3(int id){
        return this.getPostFP(id).clone();
    }
    // (e)
    public void comment1(FBPost post, String comentario){
        int ind = feed.indexOf(post);
        FBPost p = this.feed.get(ind);
        List<String> cms = p.getComentarios();
        cms.add(comentario);
        p.setComentarios(cms);
    }
    // OU
    public void comment2(FBPost post, String comentario){
        FBPost p = this.getPostFP(post.getId());
        List<String> cms = p.getComentarios();
        cms.add(comentario);
        p.setComentarios(cms);
    }
    // (f)
    public void comment3(int postid, String comentario){
        FBPost p = getPostFP(postid);
        List<String> cms = p.getComentarios();
        cms.add(comentario);
        p.setComentarios(cms);
    }
    // (g)
    public void like(FBPost post){
        FBPost p = this.getPostFP(post.getId());
        p.setLikes(p.getLikes() + 1);
    }
    // (h)
    public void like(int postid){
        FBPost p = this.getPostFP(postid);
        p.setLikes(p.getLikes() + 1);
    }
    // (i)
    //public List<Integer> top5Comments(){
    //    List<Integer> res = new ArrayList<>();
    //}
}