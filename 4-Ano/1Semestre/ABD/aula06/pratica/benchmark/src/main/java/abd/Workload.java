package abd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class Workload {

    private static final int MAX = (int) Math.pow(2,5);
    private static int nr_invoices = 0;
    private final Random rand;
    private final Connection c;

    public Workload(Random rand, Connection c) throws Exception {
        this.rand = rand;
        this.c = c;

        //---- DEMO WORKLOAD ----
        // initialize connection, e.g. c.setAutoCommit(false);
        // or create prepared statements...
        //-----------------------
    }

    public static void populate(Random rand, Connection c) throws Exception {
        Statement s = c.createStatement();

        s.executeUpdate("DROP TABLE IF EXISTS Client CASCADE;");
        s.executeUpdate("DROP TABLE IF EXISTS Product CASCADE;");
        s.executeUpdate("DROP TABLE IF EXISTS Invoice CASCADE;");
        s.executeUpdate("DROP TABLE IF EXISTS InvoiceLine CASCADE;");

        s.executeUpdate("CREATE TABLE Client (id int, name varchar, address varchar);");
        s.executeUpdate("CREATE TABLE Product (id int, description varchar, stock int, min int, max int);");
        s.executeUpdate("CREATE TABLE Invoice (id int, client_id int);");
        s.executeUpdate("CREATE TABLE InvoiceLine (id int, invoice_id int, product_id int);");    
        s.executeUpdate("CREATE TABLE Orders (id int, product_id int, supplier int, items int);");    

        for(int i = 0; i < MAX; i++){
            s.executeUpdate("INSERT INTO Client VALUES  (" + i + ", 'Simpson', 'Ali ao lado');");
            s.executeUpdate("INSERT INTO Product VALUES (" + i + ", 'Descrição',15,10,20);");
        }

        s.close();
    }

    public void transaction() throws Exception {
        Statement s = c.createStatement();
        int next = rand.nextInt(3);
        switch(next) {
            case 0:
                //sell(rand,s);
                break;
            case 1:
                account(rand,s);
                break;
            case 2:
                //top10(s);
                break;
            default:
                System.out.println("Operação não existente!");
                break;
        }
        s.close();
    }

    public static void sell(Random rand,Statement s) throws SQLException {
        int client_id = rand.nextInt(MAX)|rand.nextInt(MAX);
        int product_id = rand.nextInt(MAX)|rand.nextInt(MAX);
        s.executeUpdate("insert into Invoice values(" + nr_invoices++ + "," + client_id + ","  + product_id + "," + data + ");");
    }

    public static void account(Random rand,Statement s) throws SQLException {
        int client_id = rand.nextInt(MAX)|rand.nextInt(MAX);
        s.executeUpdate("SELECT product.description FROM Invoice INNER JOIN product ON invoice.product_id=product.id WHERE invoice.client_id = " + client_id + ";");
        //s.executeUpdate("select p.id from product p, client c, invoice i where p.id = i.product_id and c.id = i.client_id and client_id = " + client_id + ";");
    }

    public static void top10(Statement s) throws SQLException {
        String sql = "" +
                "SELECT product_id, COUNT(product_id) AS value_occurrence " +
                "FROM Invoice " +
                "GROUP BY product_id " +
                "ORDER BY value_occurrence DESC " +
                "LIMIT 10;";
        s.executeUpdate(sql);
    }
}
