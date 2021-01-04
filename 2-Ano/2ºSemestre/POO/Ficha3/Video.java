import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Video aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Video{
    private String nome;
    private byte [] conteudo;
    private LocalDate data;
    private int resolucao; // 720, 1080
    private int duracao; // em segundos
    private String [] comentarios;
    private int likes;
    private int dislikes;
   
    private int index; // Não necessita de get/set
 
    // (a) Construtores, get, set, clone, equals, toString
    /**
     * Cria um vídeo vazio, com 100 bytes e espaço para 100 comentários.
     */
    public Video(){
        this.nome="N/A";
        this.conteudo = new byte[100];
        this.data = LocalDate.now();
        this.resolucao = 1080;
        this.duracao = 0;
        this.comentarios = new String[100];
        this.likes = this.dislikes = 0;
    }
    public Video(String nome, byte[] conteudo, LocalDate data, int resolucao, int duracao, String [] comentarios, int likes, int dislikes){
        this.nome = nome;
        this.conteudo = conteudo;
        this.data = data;
        this.resolucao = resolucao;
        this.duracao = duracao;
        this.comentarios = comentarios;
        this.likes = likes;
        this.dislikes = dislikes;
    }
    public Video(Video outroVideo){
        this.nome = outroVideo.getNome();
        this.conteudo = outroVideo.getConteudo();
        this.data = outroVideo.getData();
        this.resolucao = outroVideo.getResolucao();
        this.duracao = outroVideo.getDuracao();
        this.comentarios = outroVideo.getComentarios();
        this.likes = outroVideo.getLikes();
        this.dislikes = outroVideo.getDislikes();
    }
    public String getNome(){
        return this.nome;
    }
    public byte [] getConteudo(){
        return this.conteudo;
    }
    public LocalDate getData(){
        return this.data;
    }
    public int getResolucao(){
        return this.resolucao;
    }
    public int getDuracao(){
        return this.duracao;
    }
    public String[] getComentarios(){
        return this.comentarios;
    }
    public int getLikes(){
        return this.likes;
    }
    public int getDislikes(){
        return this.dislikes;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setConteudo(byte [] conteudo){
        this.conteudo = conteudo;
    }
    public void setData(LocalDate data){
        this.data = data;
    }
    public void setResolucao(int resolucao){
        this.resolucao = resolucao;
    }
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }
    public void setComentarios(String [] comentarios){
        this.comentarios = comentarios;
    }
    public void setLikes(int like){
        this.likes = likes;
    }
    public void setDislikes(int dislikes){
        this.dislikes = dislikes;
    }
    public Video clone(){
        return new Video(this);
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        
        Video v = (Video) o;
        return (this.nome.equals(v.getNome()) && Arrays.equals(v.getConteudo(),this.conteudo) && this.data.equals(v.getData()) && this.resolucao == v.getResolucao() && this.duracao == v.getDuracao() && Arrays.equals(this.comentarios,v.getComentarios()) && this.likes == v.getLikes() && this.dislikes == v.getDislikes()); 
    }
    public String toString(){
        return ("Nome: " + this.nome + "\nConteudo: " + Arrays.toString(this.conteudo) + "\nData: " + this.data + "\nResolução: " + this.resolucao + "\nDuração: " + this.duracao + "\nComentarios" + Arrays.toString(comentarios) + "\nLikes: " + this.likes + "\nDislikes: " + this.dislikes);
    }
    // (b)
    public void insereComentario(String comentario){
        if(index < this.comentarios.length){
            comentarios[index] = comentario;
            index++; 
        }
    }
    // (c)
    public long qtsDiasDepois(){
        LocalDate agora = LocalDate.now();
        return data.until(agora,ChronoUnit.DAYS);
    }
    // (d)
    public void thumbsUp(){
        this.likes++;
    }
    // (e)
    public String processa(){
        return Arrays.toString(conteudo);
    }
}   
