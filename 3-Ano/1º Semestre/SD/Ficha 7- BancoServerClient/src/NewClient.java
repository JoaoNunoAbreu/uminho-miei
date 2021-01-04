public class NewClient{

    public static void main(String[] args) throws Exception {
        BancoRemoto br = new BancoRemoto("localhost",12345);

        System.out.println(br.createAccount(10));

        System.out.println(br.createAccount(20));

        System.out.println(br.createAccount(30));

        System.out.println(br.totalBalance(new int[]{0,1,2}));

        System.out.println(br.closeAccount(1));

        br.transferir(5,0,2);

        br.credito(0,50);

        br.debito(0,20);

        System.out.println(br.consulta(0));

        System.out.println(br.movimentos(0));
    }
}
