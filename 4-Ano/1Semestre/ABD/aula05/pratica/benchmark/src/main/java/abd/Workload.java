package abd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Workload {

    private static final int MAX = (int) Math.pow(2,5);
    private static final String data = "'Nam quis nulla. Integer malesuada. In in enim a arcu imperdiet malesuada. Sed vel lectus. Donec odio urna, tempus molestie, porttitor ut, iaculis quis, sem. Phasellus rhoncus. Aenean id metus id velit ullamcorper pulvinar. Vestibulum fermentum tortor id mi. Pellentesque ipsum. Nulla non arcu lacinia neque faucibus fringilla. Nulla non lectus sed nisl molestie malesuada. Proin in tellus sit amet nibh dignissim sagittis. Vivamus luctus egestas leo. Maecenas sollicitudin. Nullam rhoncus aliquam metus. Etiam egestas wisi a erat. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam feugiat, turpis at pulvinar vulputate, erat libero tristique tellus, nec bibendum odio risus sit amet ante. Aliquam erat volutpat. Nunc auctor. Mauris pretium quam et urna. Fusce nibh. Duis risus. Curabitur sagittis hendrerit ante. Aliquam erat volutpat. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonummy ac, erat. Duis condimentum augue id magna semper rutrum. Nullam justo enim, consectetuer nec, ullamcorper ac, vestibulum in, elit. Proin pede metus, vulputate'";
    private static int nr_invoices = 0;

    public static void populate(Random random, Connection c) throws Exception {
        Statement s = c.createStatement();

        s.executeUpdate("DROP TABLE IF EXISTS client CASCADE;");
        s.executeUpdate("DROP TABLE IF EXISTS product CASCADE;");
        s.executeUpdate("DROP TABLE IF EXISTS invoice CASCADE;");

        s.executeUpdate("CREATE TABLE IF NOT EXISTS client " +
                "(id int, name varchar, address varchar,data varchar);");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS product " +
                "(id int, description varchar, data varchar);");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS invoice " +
                "(id int, product_id int, client_id int,data varchar);");


        for(int i = 0; i < MAX; i++){
            s.executeUpdate("INSERT INTO client VALUES  (" + i + ", 'Simpson', 'Ali ao lado'," + data + ");");
            s.executeUpdate("INSERT INTO product VALUES (" + i + ", 'Descrição'," + data + ");");
        }

        s.close();
    }

    public static void transaction(Random rand, Connection c) throws Exception {
        Statement s = c.createStatement();
        int next = rand.nextInt(3);
        switch(next) {
            case 0:
                sell(rand,s);
                break;
            case 1:
                account(rand,s);
                break;
            case 2:
                top10(s);
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
        s.executeUpdate("insert into invoice values(" + nr_invoices++ + "," + client_id + ","  + product_id + "," + data + ");");
    }

    public static void account(Random rand,Statement s) throws SQLException {
        int client_id = rand.nextInt(MAX)|rand.nextInt(MAX);
        s.executeUpdate("SELECT product.description FROM invoice INNER JOIN product ON invoice.product_id=product.id WHERE invoice.client_id = " + client_id + ";");
        //s.executeUpdate("select p.id from product p, client c, invoice i where p.id = i.product_id and c.id = i.client_id and client_id = " + client_id + ";");
    }

    public static void top10(Statement s) throws SQLException {
        String sql = "" +
                "SELECT product_id, COUNT(product_id) AS value_occurrence " +
                "FROM invoice " +
                "GROUP BY product_id " +
                "ORDER BY value_occurrence DESC " +
                "LIMIT 10;";
        s.executeUpdate(sql);
    }
}