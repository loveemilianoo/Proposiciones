import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Tabla {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantas propocisiones simples tiene la operación");
        int prop = sc.nextInt();

        if (prop <= 0) {
            System.out.println("Las propocisiones deben ser mayor a 0");
            if (prop > 4) {
                System.out.println("No es valido");
                return;
            }
            return;
        }

        boolean[][] tabla = geneTab(prop);

        String[] columns = new String[prop];
        for (int i = 0; i < prop; i++) {
            columns[i] = "X" + (i + 1);
        }

        Object[][] datos = new Object[tabla.length][prop];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < prop; j++) {
                datos[i][j] = tabla[i][j] ? "V" : "F";
            }
        }

        SwingUtilities.invokeLater(() -> {
            JTable jTable = new JTable(datos, columns);
            JFrame frame = new JFrame("Tabla de verdad");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(jTable), BorderLayout.CENTER);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

    public static boolean[][] geneTab(int n) {
        int filas = (int) Math.pow(2, n);
        boolean[][] tabla = new boolean[filas][n];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < n; j++) {
                int columna = n - 1 - j;
                int bit = (i >> j) & 1;
                tabla[i][columna] = (bit == 1);
            }
        }
        return tabla;
    }
}