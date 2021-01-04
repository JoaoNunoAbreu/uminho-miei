package com.company;

interface Bank{
    int createAccount(double inicial);
    double closeAccount(int id) throws InvalidAccount;
    void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds;
    double totalBalance(int accounts[]);
}
