import java.util.List;

interface Bank{
    int createAccount(double inicial);
    double closeAccount(int id) throws InvalidAccount;
    void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds;
    double totalBalance(int[] accounts) throws InvalidAccount ;
    void credito(int n, double quantia) throws InvalidAccount;
    void debito(int n, double quantia) throws InvalidAccount, NotEnoughFunds;
    double consulta(int n) throws InvalidAccount;
    List<Movimento> movimentos(int id) throws InvalidAccount;
}
