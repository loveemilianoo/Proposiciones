import javax.swing.*;

import java.awt.*;

public class Table extends JPanel {
    private String[] headers = { "p", "q", "r", "s" };
    private boolean[][] values = {
            { true, false, true, false },
            { true, false, true, false }
    };

    public Table() {
        createComponent();
    }

    public Table(String[] headers, boolean[][] values) {
        this.headers = headers;
        this.values = values;
        createComponent();

    }

    private void createComponent() {
        String[][] content = new String[values.length][headers.length];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < headers.length; j++) {
                content[i][j] = values[i][j] ? "0" : "1";
            }
        }

        JTable jTable = new JTable(content, headers) {
            // ? Override this method prevents the user to modify the table
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
    }

    public String[] getHeaders() {
        return headers;
    }

    public boolean[][] getValues() {
        return values;
    }

    public static void main(String[] args) {

        Screen screen = new Screen("Tabla", 600, 400);
        Table table = new Table();

        screen.add(table);
        screen.showScreen();

    }

}
