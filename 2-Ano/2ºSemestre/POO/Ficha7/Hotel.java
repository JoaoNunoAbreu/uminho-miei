public abstract class Hotel implements Comparable<Hotel>{
    private int id;
    private String nome;
    private String localidade;
    private int categoria;
    private int quartosDisponiveis;
    private double precoPorQuarto;   
    public Hotel(){
        this.id = 0;
        this.nome = "N/A";
        this.localidade = "N/A";
        this.categoria = 0;
        this.quartosDisponiveis = 0;
        this.precoPorQuarto = 0;
    }
    public Hotel(int id, String nome, String localidade, int categoria, int quartosDisponiveis,double precoPorQuarto){
        this.id = id;
        this.nome = nome;
        this.localidade = localidade;
        this.categoria = categoria;
        this.quartosDisponiveis = quartosDisponiveis;
        this.precoPorQuarto = precoPorQuarto;
    }
    public Hotel(Hotel outro){
        this.id = outro.getId();
        this.nome = outro.getNome();
        this.localidade = outro.getLocalidade();
        this.categoria = outro.getCategoria();
        this.quartosDisponiveis = outro.getQuartosDisponiveis();
        this.precoPorQuarto = outro.getPrecoPorQuarto();
    }
    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getLocalidade(){
        return this.localidade;
    }
    public int getCategoria(){
        return this.categoria;
    }
    public int getQuartosDisponiveis(){
        return this.quartosDisponiveis;
    }
    public double getPrecoPorQuarto(){
        return this.precoPorQuarto;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setLocalidade(String localidade){
        this.localidade = localidade;
    }
    public void setCategoria(int categoria){
        this.categoria = categoria;
    }
    public void setQuartosDisponiveis(int quartosDisponiveis){
        this.quartosDisponiveis = quartosDisponiveis;
    }
    public void setPrecoPorQuarto(double precoPorQuarto) throws ValorInvalidoException{
        if(precoPorQuarto < 0)
            throw new ValorInvalidoException("O preço tem de ser positivo");
        this.precoPorQuarto = precoPorQuarto;
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        
        Hotel h = (Hotel) o;
        return this.id == h.getId() && this.nome.equals(h.getNome())
               && this.localidade.equals(h.getLocalidade())
               && this.categoria == h.getCategoria()
               && this.quartosDisponiveis == h.getQuartosDisponiveis()
               && this.precoPorQuarto == h.getPrecoPorQuarto();
    }
    @Override
    public String toString(){
        return "Id: " + this.id + "\n"
            + "Nome: " + this.nome + "\n"
            + "Localidade: " + this.localidade + "\n"
            + "Categoria: " + this.categoria + "\n"
            + "Quartos disponiveis: " + this.quartosDisponiveis + "\n"
            + "Preco por quarto: " + this.precoPorQuarto + "\n";
    }
    @Override
    public int compareTo(Hotel hotel){
        return this.nome.compareTo(hotel.getNome());
    }
    public abstract double precoNoite();
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(",");
        sb.append(this.nome);
        sb.append(",");
        sb.append(this.localidade);
        sb.append(",");
        sb.append(this.categoria);
        sb.append(",");
        sb.append(this.quartosDisponiveis);
        sb.append(",");
        sb.append(this.precoPorQuarto);
        return sb.toString();
    }
}
