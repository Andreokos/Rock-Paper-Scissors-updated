package game;


import com.github.freva.asciitable.AsciiTable;

public class Table {

    public static final Main table = new Main();

    public static String tables(String[] arguments) {

        String[] headers = new String[arguments.length+1];
        String[][] data = new String[arguments.length][arguments.length+1];

        for (int i = 0; i < arguments.length; i++) {
            headers[0] = "    \\PC\n User\\";
            headers[i+1] = arguments[i];
            for (int k = 0; k-1 < arguments.length; k++) {
                if (k == 0) {
                    data[i][k] = arguments[i];
                } else {
                    int ind = Main.winner(i, k-1, arguments);
                    data[i][k] = Main.result(ind);
                }
            }
        }
        return AsciiTable.getTable(headers, data);
    }


    public static void main(String[] args) {
    }
    }
