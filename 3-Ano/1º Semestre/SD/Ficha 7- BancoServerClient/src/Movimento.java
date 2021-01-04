public class Movimento {

    private int id_op;
    private String descritivo;
    private double saldo_final;

    public Movimento(int id_op, String descritivo, double saldo_final){
        this.id_op = id_op;
        this.descritivo = descritivo;
        this.saldo_final = saldo_final;
    }

    public Movimento(String str){
        String [] parts = str.split(";");
        this.id_op = Integer.parseInt(parts[0]);
        this.descritivo = parts[1];
        this.saldo_final = Double.parseDouble(parts[2]);
    }

    public String toString(){
        return "" + id_op + ";" + descritivo + ";" + saldo_final;
    }
}
