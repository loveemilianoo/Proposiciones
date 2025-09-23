package view;

import javax.swing.*;
import java.awt.*;
import models.Data;

public class Table extends JPanel {
    private Data data;

    /**
     * Crea la interfaz que incluye la tabla de verdad
     * 
     * @param data Clase que contiene los valores a mostrar
     */
    public Table(Data data) {
        this.data = data;
        createComponent();
    }

    /**
     * Crea el componente con los valores descritos
     */
    private void createComponent() {
        String[] headers = this.data.getHeaders();
        String[][] values = this.data.getValuesAsString();

        JTable jTable = new JTable(values, headers) {
            // ? Override this method prevents the user to modify the table
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}