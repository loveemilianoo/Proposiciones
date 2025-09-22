public class TablaProposiciones {
    private int numProposiciones;
    private boolean[][] tabla;

    public TablaProposiciones(int numProposiciones) {
        if (numProposiciones < 1 || numProposiciones > 4) {
            throw new IllegalArgumentException("Deben ser entre 1-4 Propocisiones");
        }
        this.numProposiciones = numProposiciones;
        this.tabla = geneTab(numProposiciones);
    }

    private boolean[][] geneTab(int n) {
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

    public int getNumProposiciones() {
        return numProposiciones;
    }
    public boolean[][] getTabla() {
        return tabla;
    }
        public String[] getHeaders() {
        String[] headers = new String[numProposiciones];
        char var = 'p';
        for (int i = 0; i < numProposiciones; i++) {
            headers[i] = String.valueOf((char)(var + i));
        }
        return headers;
    }
}