import java.io.*;
import java.time.LocalDate;
import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
public class IO{
    /**
     * Menu principal da aplicação
     */
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------ UMCarroJa ------------------\n");
        System.out.println("1 - Carregar dados iniciais");
        System.out.println("2 - Registar um proprietário");
        System.out.println("3 - Registar um cliente");
        System.out.println("4 - Login proprietário");
        System.out.println("5 - Login cliente");
        System.out.println("6 - Total facturado por uma viatura num determinado período");
        System.out.println("7 - Listagem dos 10 clientes que mais utilizam o sistema (em número de vezes e em kms percorridos)");
        System.out.println("8 - Gravar o estado da aplicação em ficheiro");
        System.out.println("9 - Carregar o estado da aplicação a partir de ficheiro");
        System.out.println("10 - Mostrar estado da aplicação");
        System.out.println("0 - Sair\n");
        System.out.println("-----------------------------------------------");
        
        int res = sc.nextInt();
        while(res < 0 || res > 10){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextInt();
        }
        return res;
    }
    /**
     * Menu de um proprietário
     */
    public static int menuProp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Login efectuado com sucesso!\n");
        System.out.println("----------------- Proprietário ----------------\n");
        System.out.println("1 - Registar viatura");
        System.out.println("2 - Classificar cliente após aluguer");
        System.out.println("3 - Histórico de alugueres entre datas");
        System.out.println("4 - Sinalizar que um carro está disponível/indisponível");
        System.out.println("5 - Abastecer um carro");
        System.out.println("6 - Alterar o preço por km");
        System.out.println("7 - Ver quanto custaram as viagens\n");
        System.out.println("-----------------------------------------------");
        
        int res = sc.nextInt();
        while(res < 1 || res > 7){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextInt();
        }
        return res;
    }
    /**
     * Menu de um cliente
     */
    public static int menuClient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Login efectuado com sucesso!\n");
        System.out.println("------------------- Cliente -------------------\n");
        System.out.println("1 - Solicitar um aluguer. Se este for aceite, classificar carro e proprietário");
        System.out.println("2 - Histórico de alugueres entre datas\n");
        System.out.println("-----------------------------------------------");
        
        int res = sc.nextInt();
        while(res < 1 || res > 2){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextInt();
        }
        return res;
    }
    /**
     * Menu dos tipos de alugueres
     */
    public static int menuTipoAlugueres(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------- Tipo de Alugueres --------------\n");
        System.out.println("1 - Aluguer do carro mais próximo");
        System.out.println("2 - Aluguer do carro mais próximo com um determinado tipo de combustível");
        System.out.println("3 - Aluguer do carro mais barato");
        System.out.println("4 - Aluguer do carro mais barato com um determinado tipo de combustível");
        System.out.println("5 - Aluguer do carro mais barato dentro de uma distância percorrível a pé");
        System.out.println("6 - Aluguer de um carro específico");
        System.out.println("7 - Aluguer de um carro com uma autonomia desejada\n");
        System.out.println("-----------------------------------------------");
        
        int res = sc.nextInt();
        while(res < 1 || res > 7){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextInt();
        }
        return res;
    }
    public static int menuTopClient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- Top Clientes ----------------\n");
        System.out.println("1 - Top 10 Clientes ordenados por kms percorridos");
        System.out.println("2 - Top 10 Clientes ordenados por numero de vezes que usaram a aplicação\n");
        System.out.println("-----------------------------------------------");
        
        int res = sc.nextInt();
        while(res < 1 || res > 2){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextInt();
        }
        return res;
    }
    /**
     * Cria um proprietário
     */
    public static Proprietario novoProp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Morada: ");
        String morada = sc.nextLine();
        System.out.println("Data de nascimento: ");
        String data = sc.nextLine();
        LocalDate localDate = LocalDate.parse(data);
        System.out.println("Nif: ");
        int nif = sc.nextInt();
        
        Proprietario p = new Proprietario();
        p.setNome(nome);
        p.setEmail(email);
        p.setPassword(password);
        p.setMorada(morada);
        p.setData(localDate);
        p.setNif(nif);
        try{
            write("logsPOO_resultados.bak","NovoProp:" + p.toCSV());
        }
        catch(IOException e){System.out.println("Erro ao escrever no ficheiro");}
        return p;
    }
    /**
     * Cria um cliente
     */
    public static Cliente novoCli(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Morada: ");
        String morada = sc.nextLine();
        System.out.println("Data de nascimento: ");
        String data = sc.nextLine();
        LocalDate localDate = LocalDate.parse(data);
        System.out.println("Nif: ");
        int nif = sc.nextInt();
        System.out.println("Posição x: ");
        double x = sc.nextDouble();
        System.out.println("Posição y: ");
        double y = sc.nextDouble();
        Point.Double posicao = new Point.Double(x,y);
        
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setEmail(email);
        c.setPassword(password);
        c.setMorada(morada);
        c.setData(localDate);
        c.setNif(nif);
        c.setPosicao(posicao);
        
        try{
            write("logsPOO_resultados.bak","NovoCliente:" + c.toCSV());
        }
        catch(IOException e){System.out.println("Erro ao escrever no ficheiro");}
        
        return c;
    }
    /**
     * Cria um carro
     */
    public static Carro novoCarro(Proprietario p) throws NullPointerException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Marca: ");
        String marca = sc.nextLine();
        System.out.println("Tipo: ");
        String tipo = sc.nextLine();
        System.out.println("Matricula: ");
        String matricula = sc.nextLine();
        int nif = p.getNif();
        System.out.println("VelocidadeMediaKm: ");
        double velocidade = sc.nextDouble();
        System.out.println("PrecoKm: ");
        double preco = sc.nextDouble();
        System.out.println("ConsumoPorKm: ");
        double consumo = sc.nextDouble();
        System.out.println("Posição x: ");
        double x = sc.nextDouble();
        System.out.println("Posição y: ");
        double y = sc.nextDouble();
        Point.Double posicao = new Point.Double(x,y);
        System.out.println("Autonomia: ");
        double autonomia = sc.nextDouble();
        
        Carro c = null;
        if(tipo.equals("Eletrico")) c = new CarroEletrico();
        else if(tipo.equals("Hibrido")) c = new CarroHibrido();
        else if(tipo.equals("Gasolina")) c = new CarroGasolina();
        
        c.setMarca(marca);
        c.setMatricula(matricula);
        c.setNif(nif);
        c.setVelMedKm(velocidade);
        c.setPrecoKm(preco);
        c.setConsumoKm(consumo);
        c.setPosicao(posicao);
        c.setAutonomia(autonomia);
        
        try{
            write("logsPOO_resultados.bak","NovoCarro:" + c.toCSV());
        }
        catch(IOException e){System.out.println("Erro ao escrever no ficheiro");}
        
        return c;
    }
    /**
     * Tenta dar login de um proprietário
     * Retorna o próprio em caso de sucesso
     */
    public static Proprietario logInProprietarios(UMCarroJa ucj) throws WrongPasswordException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Email para login: ");
        String email = sc.nextLine();
        System.out.println("Password para login: ");
        String password = sc.nextLine();
        Proprietario prop = null;
        
        Map<String,Proprietario> map = ucj.getProprietarios().values().stream().collect(Collectors.toMap(p -> p.getEmail(),p -> p.clone()));
        try{
            if(password.equals(map.get(email).getPassword()))
                prop = map.get(email);
            else throw new WrongPasswordException("Password incorrecta!");
        }
        catch(NullPointerException e){System.out.println("Email não registado!");}
        return prop;
    }
    /**
     * Tenta dar login de um cliente
     * Retorna o próprio em caso de sucesso
     */
    public static Cliente logInClientes(UMCarroJa ucj) throws WrongPasswordException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Email para login: ");
        String email = sc.nextLine();
        System.out.println("Password para login: ");
        String password = sc.nextLine();
        Cliente cli = null;
        
        Map<String,Cliente> map = ucj.getClientes().values().stream().collect(Collectors.toMap(c -> c.getEmail(),c -> c.clone()));
        try{
            if(password.equals(map.get(email).getPassword()))
                cli = map.get(email);
            else throw new WrongPasswordException("Password incorrecta!");
        }
        catch(NullPointerException e){System.out.println("Email não registado");}
        return cli;
    }
    /**
     * Devolve o valor de uma nova classificação
     */
    public static double novaClassificacao(String msg){
        Scanner sc = new Scanner(System.in);
        System.out.println("Classificação " + msg);
        
        double res = sc.nextDouble();
        while(res < 0 || res > 100){
            System.out.println("Valor lido fora do intervalo. Escolher outro valor:");
            res = sc.nextDouble();
        }
        return res;
    }
    /**
     * Devolve um cliente lendo o valor do NIF
     */
    public static Cliente cliente(UMCarroJa ucj){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nif do cliente:");
        int nif = sc.nextInt();
        return ucj.getClientes().get(nif);
    }
    /**
     * Devolve um carro lendo a sua matrícula
     */
    public static Carro carro(UMCarroJa ucj) throws CarroNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Matrícula:");
        String matricula = sc.nextLine();
        if(ucj.getCarros().get(matricula) == null)
            throw new CarroNotFoundException("Carro não existe!");
        return ucj.getCarros().get(matricula);
    }
    /**
     * Devolve um LocalDate a partir do stdin
     */
    public static LocalDate data(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Data:");
        String data = sc.nextLine();
        LocalDate localDate = LocalDate.parse(data);
        
        return localDate;
    }
    /**
     * Devolve um destino Point.Double a partir do stdin
     */
    public static Point.Double ponto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Coordenada X do destino:");
        double x = sc.nextDouble();
        System.out.println("Coordenada Y do destino:");
        double y = sc.nextDouble();
        
        return new Point.Double(x,y);
    }
    /**
     * Devolve um tipo de combustível
     */
    public static String tipo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Tipo de combustível:");
        String tipo = sc.nextLine();
        return tipo;
    }
    /**
     * Devolve uma distância percorrível a pé
     */
    public static Double distanciaPercorrivel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Distância percorrível a pé:");
        double dist = sc.nextDouble();
        return dist;
    }
    /**
     * Devolve uma autonomia
     */
    public static double autonomia(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Autonomia:");
        double autonomia = sc.nextDouble();
        return autonomia;
    }
    /**
     * Devolve um preço por km
     */
    public static double precoPorKm(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Preço por km:");
        double preco = sc.nextDouble();
        return preco;
    }
    /**
     * Escreve texto num ficheiro
     */
    public static void write(String filename, String text) throws IOException{
        PrintWriter fich = new PrintWriter(new FileWriter(filename, true));
        fich.println(text);
        fich.flush();
        fich.close();
    }
    /**
     * Devolve uma disponibilidade de um carro
     */
    public static boolean disponibilidade(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Disponibilidade:");
        boolean r = sc.nextBoolean();
        return r;
    }
}
