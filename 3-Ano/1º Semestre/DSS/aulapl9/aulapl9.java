class AutoInt{

    TabMovimentos tabMov;

    Collection<String> comTantasParagens(int n){
        return tabMov.comTP(n);
    }
}

class TabelaMovimento{

    Collection<Movimento> movs;

    Collection<String> comTP(int n){

        for(Movimento m : movs){
            Bilhete b = m.getBilhetes();
        }
    }
}