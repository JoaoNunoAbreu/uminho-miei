import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class ParserAdjacencia {

    public static void main(String[] args) throws IOException {
        if (args.length != 1)
            System.out.println("Número de argumentos inválidos!");
        else {
            File excelFile = new File(args[0]);
            FileInputStream fis = new FileInputStream(excelFile);
            boolean flag = false;
            String pattern = "####";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            List<Move> moves = new ArrayList<>();

            // we create an XSSF Workbook object for our XLSX Excel File
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            for (int i = 0; i < 39; i++) {
                List<Info> res = new ArrayList<>();
                // we get first sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                int carreira = Integer.parseInt(workbook.getSheetName(i));

                //System.out.println("% ------------ " + carreira + " ---------------");

                for (Row row : sheet) {
                    Info info = new Info();
                    if (!flag) flag = true;
                    else {
                        // iterate on cells for the current row
                        Iterator<Cell> cellIterator = row.cellIterator();

                        int count = 0;
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            String clean = cell.toString().replace("\n", "").replace("\r", "");
                            if(count == 0) {
                                String format = decimalFormat.format(Float.parseFloat(clean));
                                info.setGid(Integer.parseInt(format));
                            }
                            if(count == 1){
                                try{
                                    info.setLat(Float.parseFloat(clean));
                                }
                                catch (NumberFormatException e){
                                    info.setLat(0);
                                }
                            }
                            if(count == 2){
                                try{
                                    info.setLon(Float.parseFloat(clean));
                                }
                                catch (NumberFormatException e){
                                    info.setLon(0);
                                }
                            }
                            count++;
                        }
                        if(info.getLat() != 0 && info.getLon() != 0) {
                            res.add(info);
                        }
                    }
                }

                /* Em cada sheet fazer */
                flag = false;

                for (int idx = 0; idx < res.size() - 1; ++idx) {
                    if(res.get(idx).distanceTo(res.get(idx+1)) > 0) {
                        //if(!moves.contains(new Move(res.get(idx).getGid(),res.get(idx+1).getGid()))) {
                            System.out.println("move(" + res.get(idx).getGid() + "," + res.get(idx + 1).getGid() + "," + res.get(idx).distanceTo(res.get(idx + 1)) + "," + carreira + ").");
                            moves.add(new Move(res.get(idx).getGid(), res.get(idx + 1).getGid()));
                        //}
                        //if(!moves.contains(new Move(res.get(idx+1).getGid(),res.get(idx).getGid()))) {
                        //    System.out.println("move(" + res.get(idx + 1).getGid() + "," + res.get(idx).getGid() + "," + res.get(idx).distanceTo(res.get(idx + 1)) + "," + carreira + ").");
                        //    moves.add(new Move(res.get(idx + 1).getGid(), res.get(idx).getGid()));
                        //}
                    }
                }

                /* ------------------ */

            }
            workbook.close();
            fis.close();
        }
    }
}


class Info{
    private int gid;
    private double lat;
    private double lon;

    Info() {
        this.gid = 0;
        this.lat = 0;
        this.lon = 0;
    }

    void setGid(int gid) {
        this.gid = gid;
    }

    void setLat(double lat) {
        this.lat = lat;
    }

    void setLon(double lon) {
        this.lon = lon;
    }

    int getGid() {
        return gid;
    }

    double getLat() {
        return lat;
    }

    double getLon() {
        return lon;
    }

    double distanceTo(Info i){
        double y1 = this.getLon();
        double y2 = i.getLon();
        double x1 = this.getLat();
        double x2 = i.getLat();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    @Override
    public String toString() {
        return "Info{" +
                "gid=" + gid +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

class Move{
    private int Nodo;
    private int Nodo1;

    Move(int nodo, int nodo1) {
        Nodo = nodo;
        Nodo1 = nodo1;
    }

    public int getNodo() {
        return Nodo;
    }

    public int getNodo1() {
        return Nodo1;
    }

    public void setNodo(int nodo) {
        Nodo = nodo;
    }

    public void setNodo1(int nodo1) {
        Nodo1 = nodo1;
    }

    @Override
    public String toString() {
        return "(" + Nodo + "," + Nodo1 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Nodo == move.Nodo &&
                Nodo1 == move.Nodo1;
    }
}