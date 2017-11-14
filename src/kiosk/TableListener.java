package kiosk;

import java.util.ArrayList;

public class TableListener {
    public ArrayList<ArrayList<Table>> tables = new ArrayList<ArrayList<Table>>();
    private static final int[] TABLE_TYPE_COL = {10, 10, 8, 2, 2};
    private Listener listener;
    Table table;

    public TableListener(Listener listener) {
        this.listener = listener;
        int tableRow = 0;
        for (int i = 0; i < 5; i++) {
            this.tables.add(new ArrayList<Table>());
        }
        for (int index = 0; index < 5; index++) {
            for (int tableCol = 0; tableCol < TABLE_TYPE_COL[index]; tableCol++) {
                this.table = new Table("#table_" + String.valueOf(tableRow) + "_" + String.valueOf(tableCol), true, index);
                this.tables.get(index).add(table);
            }
            tableRow++;
        }
    }

    public Table checkEmptyTable(Integer row) {
        for (Table table : this.tables.get(row)) {
            if (table.getEmpty() && table.getTicketNo() == null) {
                return table;
            }
        }
        return null;
    }

    public void checkTableStatus(int row) {
        for (Table table : this.tables.get(row)) {
            if (table.getEmpty() && table.getTicketNo() != null) {
                System.out.println(table + " client not found do clean up");
                table.clearUp();
            }
        }
    }

    public void tableAssign(String tableNo) {
        Integer row = Integer.parseInt(tableNo.split("_")[1]);
        Integer col = Integer.parseInt(tableNo.split("_")[2]);
        table = this.tables.get(row).get(col);
        table.setEmpty(false);
        this.listener.controller.setSeat(table);
    }

    public void checkOut(String tableNo) {
        Integer row = Integer.parseInt(tableNo.split("_")[1]);
        Integer col = Integer.parseInt(tableNo.split("_")[2]);
        table = this.tables.get(row).get(col);
        //table.setEmpty(true);
        table.clearUp();
        this.listener.controller.setSeat(table);
        System.out.println("checkout" + table);
    }

}
