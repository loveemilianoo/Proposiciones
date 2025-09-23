package models;

import java.util.Arrays;
import java.util.Map;

import utils.LogicEval;

public class Data {

    private String[] headers;
    private boolean[][] values;
    private int propositions;
    private String lastPropositionType;

    public Data() {
    }

    /**
     * Crea la clase con los encabezados y el número de proposiciones
     * 
     * @param headers      Encabezados
     * @param propositions Número de proposiciones admitidas
     */
    public Data(String[] headers, int propositions) {
        this(headers, generateTable(propositions), propositions);

    }

    public Data(String[] headers, boolean[][] values, int propositions) {
        if (propositions < 1 || propositions > 4) {
            throw new IllegalArgumentException("Deben ser entre 1 a 4 proposiciones");
        }

        this.headers = headers;
        this.values = values;
        this.propositions = propositions;

    }

    /**
     * Añade una operación y sus resultados, se actualizan tanto encabezados como
     * valores
     * 
     * @param operation Operación a evaluar
     */
    public void addOperation(String operation) {
        String[] newHeaders = new String[headers.length + 1];
        System.arraycopy(headers, 0, newHeaders, 0, headers.length);
        newHeaders[headers.length] = operation;
        setHeaders(newHeaders);

        boolean[][] newValues = new boolean[values.length][headers.length + 1];

        for (int i = 0; i < values.length; i++) {
            newValues[i] = Arrays.copyOf(values[i], headers.length);
        }

        setValues(newValues);

        int trueValues = 0;

        for (int i = 0; i < this.values.length; i++) {
            Map<String, Boolean> vars = new java.util.HashMap<>();

            for (int j = 0; j < this.propositions; j++) {
                vars.put(this.headers[j], this.values[i][j]);
            }
            int columns = this.values[i].length;

            boolean result = LogicEval.eval(operation, vars);

            if (result)
                ;
            trueValues++;
            this.values[i][columns - 1] = result;

        }

        if (trueValues == 0) {
            lastPropositionType = "Negación";
            return;
        }
        if (trueValues == Math.pow(2, propositions)) {
            lastPropositionType = "Contradicción";
            return;
        }

        lastPropositionType = "Tautología";

    }

    /**
     * Permite obtener la matriz remplazando los valores true y False
     * 
     * @param t Texto con el que se remplazará true
     * @param f Texto con el que se remplazará false
     * @return La matriz de datos con el nuevo formato
     */
    public String[][] getValuesAsString(String t, String f) {

        String[][] content = new String[values.length][headers.length];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < headers.length; j++) {
                content[i][j] = values[i][j] ? t : f;
            }
        }

        return content;

    }

    /**
     * Permite obtener la matriz remplazando los valores true y False por "T" y "F'"
     * 
     * @return La matriz de datos con el nuevo formato
     */
    public String[][] getValuesAsString() {
        return this.getValuesAsString("V", "F");
    }

    private static boolean[][] generateTable(int n) {
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

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public boolean[][] getValues() {
        return values;
    }

    public void setValues(boolean[][] values) {
        this.values = values;
    }

    public int getPropositions() {
        return propositions;
    }

    public void setPropositions(int propositions) {
        this.propositions = propositions;
    }

    public String getLastPropositionType() {
        return lastPropositionType;
    }

}
