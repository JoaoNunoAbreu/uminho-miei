import java.util.*;
import java.awt.Point;
import java.lang.Math;
import java.io.*;
import java.lang.ClassNotFoundException;
import java.time.LocalDate;
public class UMCarroJa{
    private Map<Integer,Cliente> clientes; // Key = nif do cliente 
    private Map<Integer,Proprietario> proprietarios; // Key = nif do proprietário
    private Map<String,Carro> carros; // Key = matrícula
    public UMCarroJa(){
        this.clientes = new HashMap<Integer,Cliente>();
        this.proprietarios = new HashMap<Integer,Proprietario>();
        this.carros = new HashMap<String,Carro>();
    }
    public UMCarroJa(HashMap<Integer,Cliente> clientes, Map<Integer,Proprietario> proprietarios, HashMap<String,Carro> carros){
        setClientes(clientes);
        setProprietarios(proprietarios);
        setCarros(carros);
    }
    public Map<Integer,Cliente> getClientes(){
        Map<Integer,Cliente> res = new HashMap<>();
        for(Map.Entry<Integer, Cliente> entry : this.clientes.entrySet())
            res.put(entry.getKey(),entry.getValue());
        return res;
    }
    public Map<Integer,Proprietario> getProprietarios(){
        Map<Integer,Proprietario> res = new HashMap<>();
        for(Map.Entry<Integer,Proprietario> entry : this.proprietarios.entrySet())
            res.put(entry.getKey(),entry.getValue());
        return res;
    }
    public Map<String,Carro> getCarros(){
        Map<String,Carro> res = new HashMap<>();
        for(Map.Entry<String,Carro> entry : this.carros.entrySet())
            res.put(entry.getKey(),entry.getValue());
        return res;
    }
    public void setClientes(Map<Integer,Cliente> clientes){
        this.clientes = new HashMap<Integer,Cliente>();
        for(Map.Entry<Integer,Cliente> entry: clientes.entrySet())
            this.clientes.put(entry.getKey(),entry.getValue());
    }
    public void setProprietarios(Map<Integer,Proprietario> proprietarios){
        this.proprietarios = new HashMap<Integer,Proprietario>();
        for(Map.Entry<Integer,Proprietario> entry : proprietarios.entrySet())
            this.proprietarios.put(entry.getKey(),entry.getValue());
    }
    public void setCarros(Map<String,Carro> carros){
        this.carros = new HashMap<String,Carro>();
        for(Map.Entry<String,Carro> entry: carros.entrySet())
            this.carros.put(entry.getKey(),entry.getValue());
    }
    @Override
    public String toString(){
        return "Clientes totais:\n" + this.clientes.toString() + "\n"
             + "Proprietários totais:\n" + this.proprietarios.toString() + "\n"
             + "Carros totais:\n" + this.carros.toString() + "\n";
    }
    /**
     * Lista de carros a gasolina
     */
    public Set<CarroGasolina> listGasolina(){
        Set<CarroGasolina> res = new HashSet<>();
        for(Carro c: this.carros.values())
            if(c instanceof CarroGasolina){
                CarroGasolina x = (CarroGasolina) c;
                res.add(x.clone());
            }
        return res;
    }
    /**
     * Lista de carros elétricos
     */
    public Set<CarroEletrico> listEletrico(){
        Set<CarroEletrico> res = new HashSet<>();
        for(Carro c: this.carros.values())
            if(c instanceof CarroEletrico){
                CarroEletrico x = (CarroEletrico) c;
                res.add(x.clone());
            }
        return res;
    }
    /**
     * Lista de carros a gasolina
     */
    public Set<CarroHibrido> listHibrido(){
        Set<CarroHibrido> res = new HashSet<>();
        for(Carro c: this.carros.values())
            if(c instanceof CarroHibrido){
                CarroHibrido x = (CarroHibrido) c;
                res.add(x.clone());
            }
        return res;
    }
    /**
     * Lista dos 10 clientes que mais utilizam o sistema 
     * (em número de vezes)
     * (em kms percorridos)
     */
    public List<Cliente> bestClients(Comparator<Cliente> comp){
        List<Cliente> temp = new ArrayList<>();
        for(Cliente c : this.clientes.values())
            temp.add(c.clone());
        Collections.sort(temp,comp);
        
        Iterator<Cliente> it = temp.iterator();
        List<Cliente> res = new ArrayList<>();
        Cliente c;
        int count = 0;
        while(it.hasNext() && count < 10){
            c = it.next();
            res.add(c);
            count++;
        }
        return res;
    }
    /**
     * Adiciona proprietário novo à aplicação
     */
    public void addProp(Proprietario p){
        this.proprietarios.put(p.getNif(),p);
    }
    /**
     * Adiciona cliente novo à aplicação
     */
    public void addCli(Cliente c){
        this.clientes.put(c.getNif(),c);
    }
    /**
     * Adiciona carro novo à aplicação
     */
    public void addCar(Carro c){
        this.carros.put(c.getMatricula(),c);
    }
    /**
     * Guarda o estado dos clientes da Aplicação 
     */
    public void guardaEstadoClientes() throws FileNotFoundException,IOException{
         FileOutputStream fos = new FileOutputStream("EstadoClientes.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(this.clientes);
         oos.flush();
         oos.close();
    }
    /**
     * Guarda o estado dos proprietarios da Aplicação 
     */
    public void guardaEstadoProprietarios() throws FileNotFoundException,IOException{
         FileOutputStream fos = new FileOutputStream("EstadoProprietarios.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(this.proprietarios);
         oos.flush();
         oos.close();
    }
    /**
     * Guarda o estado dos clientes da Aplicação 
     */
    public void guardaEstadoCarros() throws FileNotFoundException,IOException{
         FileOutputStream fos = new FileOutputStream("EstadoCarros.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(this.carros);
         oos.flush();
         oos.close();
    }
    /**
     * Lê o estado dos clientes da Aplicação
     */
    public static Map<Integer,Cliente> carregaEstadoClientes()throws FileNotFoundException,
                                                                     IOException,
                                                                     ClassNotFoundException {
         FileInputStream fis = new FileInputStream("EstadoClientes.txt");
         ObjectInputStream ois = new ObjectInputStream(fis);
         Map<Integer,Cliente> clientes = (Map<Integer,Cliente>) ois.readObject();
         ois.close();
         return clientes;
    }
    /**
     * Lê o estado dos proprietarios da Aplicação
     */
    public static Map<Integer,Proprietario> carregaEstadoProprietarios()throws FileNotFoundException,
                                                                     IOException,
                                                                     ClassNotFoundException {
         FileInputStream fis = new FileInputStream("EstadoProprietarios.txt");
         ObjectInputStream ois = new ObjectInputStream(fis);
         Map<Integer,Proprietario> proprietarios = (Map<Integer,Proprietario>) ois.readObject();
         ois.close();
         return proprietarios;
    }
    /**
     * Lê o estado dos carros da Aplicação
     */
    public static Map<String,Carro> carregaEstadoCarros()throws FileNotFoundException,
                                                                     IOException,
                                                                     ClassNotFoundException {
         FileInputStream fis = new FileInputStream("EstadoCarros.txt");
         ObjectInputStream ois = new ObjectInputStream(fis);
         Map<String,Carro> carros = (Map<String,Carro>) ois.readObject();
         ois.close();
         return carros;
    }
    /**
     * Carregamento inicial a partir do ficheiro de logs
     */
    public void carregamentoInicial(String fich){
        BufferedReader inStream = null;
        String linhaLida = null;
        try{
            inStream = new BufferedReader(new FileReader(fich));
            while((linhaLida = inStream.readLine()) != null){
                String comando = linhaLida.substring(0,linhaLida.indexOf(":"));
                String substring = linhaLida.substring(linhaLida.indexOf(":") + 1); // Posição a seguir à que tem ":"
                String[] parts = substring.split(",");
                switch(comando){
                    case "NovoProp":
                        Proprietario p = new Proprietario();
                        p.setNome(parts[0]);
                        p.setNif(Integer.parseInt(parts[1]));
                        p.setEmail(parts[2]);
                        p.setMorada(parts[3]);
                        
                        this.proprietarios.put(Integer.parseInt(parts[1]),p);
                        IO.write("logsPOO_resultados.bak","NovoProp:" + p.toCSV());
                        break;
                    case "NovoCliente":
                        Cliente c = new Cliente();
                        c.setNome(parts[0]);
                        c.setNif(Integer.parseInt(parts[1]));
                        c.setEmail(parts[2]);
                        c.setMorada(parts[3]);
                        c.setPosicao(new Point.Double(Double.parseDouble(parts[4]),Double.parseDouble(parts[5])));
                        this.clientes.put(Integer.parseInt(parts[1]),c);
                        IO.write("logsPOO_resultados.bak","NovoCliente:" + c.toCSV());
                        break;
                    case "NovoCarro":
                        Carro car = null;
                        if(parts[0].equals("Gasolina"))
                            car = new CarroGasolina();
                        else if(parts[0].equals("Electrico"))
                            car = new CarroEletrico();
                        else if(parts[0].equals("Hibrido"))
                            car = new CarroHibrido();
                        if(car != null){
                            car.setMarca(parts[1]);
                            car.setMatricula(parts[2]);
                            car.setNif(Integer.parseInt(parts[3]));
                            car.setVelMedKm(Double.parseDouble(parts[4]));
                            car.setPrecoKm(Double.parseDouble(parts[5]));
                            car.setConsumoKm(Double.parseDouble(parts[6]));
                            car.setAutonomia(Double.parseDouble(parts[7]));
                            car.setPosicao(new Point.Double(Double.parseDouble(parts[8]),Double.parseDouble(parts[9])));
                            this.carros.put(parts[2],car);
                            IO.write("logsPOO_resultados.bak","NovoCarro:" + car.toCSV());
                        }
                        break;
                    case "Aluguer":
                        int nifCliente = Integer.parseInt(parts[0]);
                        Cliente c1 = this.clientes.get(nifCliente);
                        
                        Point.Double pI = c1.getPosicao();
                        Point.Double pF = new Point.Double(Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
                        
                        String preferencia = parts[4];
                        Carro carro = null;
                        
                        try{
                            if(preferencia.equals("MaisBarato")){
                                carro = c1.carroMaisBarato(this,parts[3],pF);
                            }
                            else if(preferencia.equals("MaisPerto")){
                                carro = c1.carroMaisPerto(this,parts[3],pF); 
                            }
                        }
                        catch(NullPointerException e){System.out.println("Nenhum carro é capaz de fazer a viagem");}
                        Aluguer a = new Aluguer(pI,pF,carro.preco(pF),carro.tempo(pF),nifCliente,LocalDate.now(),preferencia);
                        c1.setPosicao(pF); // Altera a posicao do cliente depois do aluguer
                        this.carros.get(carro.getMatricula()).setPosicao(pF); // Altera a posicao do carro depois do aluguer
                        this.carros.get(carro.getMatricula()).setAutonomia(carro.novaAutonomia(pF)); // Altera autonomia do carro depois de aluguer
                        
                        c1.addAluguer(a);
                        this.carros.get(carro.getMatricula()).addAluguer(a);
                        this.carros.get(carro.getMatricula()).getProprietario(this).addAluguer(a);
                        IO.write("logsPOO_resultados.bak","Aluguer:" + a.toCSV());
                        
                        break;
                    case "Classificar":
                        if(parts[0].contains("-")){ // Cliente a classificar um carro
                            Carro car1 = this.carros.get(parts[0]);
                            car1.setClassificacao(Double.parseDouble(parts[1]));
                        }
                        else{ // Cliente a classificar um proprietário
                            Proprietario prop = this.proprietarios.get(Integer.parseInt(parts[0]));
                            if(prop != null)
                                prop.setClassificacao(Double.parseDouble(parts[1]));
                            else{ // Proprietário a classificar cliente
                                Cliente cli = this.clientes.get(Integer.parseInt(parts[0]));
                                cli.setClassificacao(Double.parseDouble(parts[1]));
                            }
                        }
                        IO.write("logsPOO_resultados.bak","Classificar:" + parts[0] + parts[1]);
                        break;
                    default: 
                        System.out.println("Comando inválido");
                        break;
                }
            }
        }
        catch(IOException e){System.out.println("Erro a ler do ficheiro");};
    }
}
