import java.util.*;
import java.io.*;
public class HoteisInc implements Serializable{
    private List<Hotel> hoteis;
    private static Map<String,Comparator<Hotel>> comparators;
    public HoteisInc(){
        this.hoteis = new ArrayList<>();
    }
    public HoteisInc(List hoteis){
        setHoteis(hoteis);
    }
    public void setHoteis(List<Hotel> h){
        this.hoteis = new ArrayList<>();
        for(Hotel a : h)
            this.hoteis.add(a);
    }
    public String toString(){
        return "Hoteis: \n" + this.hoteis.toString();
    }
    // (a)
    public boolean existeHotel(String cod){
        boolean r = false;
        for(Hotel h : this.hoteis)
            if(h.getNome().equals(cod)) r = true;
        return r;
    }
    // (b)
    public int quantos(){
        return this.hoteis.size();
    }
    // (c)
    public int quantos(String loc){
        int count = 0;
        for(Hotel h : this.hoteis)
            if(h.getLocalidade().equals(loc)) count++;
        return count;
    }
    // (d)
    public int quantosT(String tipo){
        int count = 0;
        for(Hotel h: this.hoteis)
            if(h.getClass().getSimpleName().equals(tipo)) count++;
        return count;
    }
    // (e)
    public Hotel getHotel(String cod){
        for(Hotel h : this.hoteis)
            if(h.getNome().equals(cod))return h;
        return null;
    }
    // (f)
    public void adiciona(Hotel h){
        this.hoteis.add(h);
    }
    // (g)
    public List<Hotel> getHoteis(){
        List<Hotel> r = new ArrayList<>();
        for(Hotel h : this.hoteis)
            r.add(h);
        return r;
    }
    // (h)
    public void adiciona(Set<Hotel> hs){
        for (Hotel s : hs)
            this.hoteis.add(s);
    }
    // (i)
    public void mudaPara(boolean epoca){
        for(Hotel h : this.hoteis){
            if(h.getClass().getSimpleName().equals("HotelStandard"))
                ((HotelStandard) h).setEpoca(epoca);
        }
    }
    // 2. (a)
    public TreeSet<Hotel> ordenaHoteis(){
        TreeSet<Hotel> r = new TreeSet<>();
        for(Hotel h : this.hoteis)
            r.add(h);
        return r;
    }
    // 2. (b)
    public TreeSet<Hotel> ordenaHoteis(Comparator<Hotel> c){
        TreeSet<Hotel> r = new TreeSet<>(c);
        for(Hotel h : this.hoteis)
            r.add(h);
        return r;
    }
    // 2. (c)
    static{
        comparators = new HashMap<>();
        comparators.put("numQuartos", new ComparatorNumQuartos());
        comparators.put("numEstrelas", (a,b) -> a.getCategoria() - b.getCategoria());
        // Adicionar mais comparators...
    }
    public static Comparator getComparator(String nome){
        return HoteisInc.comparators.get(nome);
    }
    // 2. (d)
    public Iterator<Hotel> ordenaHoteis(String criterio){
        Comparator<Hotel> c = HoteisInc.getComparator(criterio);
        TreeSet<Hotel> res = ordenaHoteis(c);
        return res.iterator();
    }
    // 3.
    public List<CartaoHoteis> daoPontos(){
        List<CartaoHoteis> res = new ArrayList<>();
        for(Hotel h : this.hoteis){
            if(h instanceof CartaoHoteis)
                res.add((CartaoHoteis)h);
        }
        return res;
    }
    // Fase 4
    // (1)
    public void exportarCSV(String nome) throws IOException{
        PrintWriter fich = new PrintWriter(nome);
        for(Hotel h: this.hoteis){
            fich.println(h.toCSV());
        }
        fich.flush();
        fich.close();
    }
    // (2)
    public void save(String nome) throws FileNotFoundException,IOException{
        FileOutputStream fos = new FileOutputStream(nome);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        fos.close();
        oos.close();
    }
    // Trata algumas exceptions neste contexto e lança outras para quem chama a função
    public static HoteisInc carregar(String nome) throws FileNotFoundException{
        HoteisInc h = new HoteisInc();
        try{
            FileInputStream fis = new FileInputStream(nome);
            ObjectInputStream ois = new ObjectInputStream(fis);
            h = (HoteisInc) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e){
            System.out.println("Classes incompatíveis");
        }
        catch (IOException e){
            //System.out.println("Erro a aceder ficheiro");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Existe outro erro");
        }
        return h;
    }
}
