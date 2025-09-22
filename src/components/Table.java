import javax.swing.*;
import java.awt.*;
import utils.LogicEval;

public class Table extends JFrame {
    private String[] headers;
    private boolean[][] values;
    private String operacion;


    public Table(String title, int width, int height, int numProps, String operacion) {
        super(title);

        TablaProposiciones tabla = new TablaProposiciones(numProps);
        this.headers = tabla.getHeaders();
        this.values = tabla.getTabla();
        this.operacion= operacion;

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        createComponent();
    }

    public Table(String[] headers, boolean[][] values) {
        this.headers = headers;
        this.values = values;
        createComponent();
    }

    private void createComponent() {
        String [] newHeaders = new String[headers.length +1];
        System.arraycopy(headers,0, newHeaders, 0, headers.length);
        newHeaders[headers.length]= operacion;

        String[][] content = new String[values.length][headers.length+1];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < headers.length; j++) {
                content[i][j] = values[i][j] ? "V" : "F";
            }
            java.util.Map <String, Boolean> vars= new java.util.HashMap<>();
            for (int k=0; k< headers.length; k++){
                vars.put(headers[k], values[i][k]);
            }
            boolean res= LogicEval.eval(operacion, vars);
            content[i][headers.length] = res ? "V" : "F";
        }
        
        JTable jTable = new JTable(content, newHeaders) {
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
    public void showScreen() {
        setVisible(true);
    }

public static void main(String[] args) {
    String input = JOptionPane.showInputDialog(null, "¿Cuántas proposiciones?", "Input", JOptionPane.QUESTION_MESSAGE);
    if (input == null) return;
    int prop;
    try {
        prop = Integer.parseInt(input);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Valor no válido.");
        return;
    }
    if (prop < 1 || prop > 4) {
        JOptionPane.showMessageDialog(null, "El número de proposiciones debe ser entre 1 y 4.");
        return;
    }

    String operacion = JOptionPane.showInputDialog(null, "Escribe la operación lógica (p,q,r,s,&&,||,!)","Operación",JOptionPane.QUESTION_MESSAGE);
    if (operacion == null || operacion.trim().isEmpty()){
        return;
    }

    try{
        Table table = new Table("Proposiciones - Equipo 7", 500, 400, prop, operacion);
        table.showScreen();
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();
    }

    
}
}