import java.util.List;

interface BancoRemotoInter {
    int createAccount(double inicial) throws BancoRemotoException;
    double closeAccount(int id) throws InvalidAccount, BancoRemotoException;
    void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds,BancoRemotoException;
    double totalBalance(int[] accounts) throws InvalidAccount, BancoRemotoException;
    void credito(int n, double quantia) throws InvalidAccount, BancoRemotoException;
    void debito(int n, double quantia) throws InvalidAccount, NotEnoughFunds, BancoRemotoException;
    double consulta(int n) throws InvalidAccount,BancoRemotoException;
    List<Movimento> movimentos(int id) throws InvalidAccount,BancoRemotoException;
}
