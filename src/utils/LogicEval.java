package utils;

import java.util.Map;

public class LogicEval {
    /**
     * 
     * @param expression Expresión a evaluar
     * @param variables  Valores de las variables
     * @return Resultado de la expresión
     */
    public static boolean eval(String expression, Map<String, Boolean> variables) {
        String expr = expression;
        for (Map.Entry<String, Boolean> entry : variables.entrySet()) {
            expr = expr.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }
        expr = expr.replaceAll("\\s+", "");
        // expr = expr.replaceAll("&&", "&");
        // expr = expr.replaceAll("\\|\\|", "|");
        // expr = expr.replaceAll("!", "!");
        System.out.println("Evaluando...  " + expr);
        try {
            return evalExpr(expr);
        } catch (Exception e) {
            throw new RuntimeException("Expresión inválida: " + expr + " - " + e.getMessage());
        }
    }

    private static boolean evalExpr(String expr) {
        expr = expr.trim();
        while (expr.startsWith("(") && expr.endsWith(")") && outParent(expr)) {
            expr = expr.substring(1, expr.length() - 1).trim();
        }

        int parenCount = 0;
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')')
                parenCount++;
            if (c == '(')
                parenCount--;
            if (parenCount == 0 && i > 0 && expr.charAt(i) == '|' && expr.charAt(i - 1) == '|') {
                String left = expr.substring(0, i - 1).trim();
                String rigth = expr.substring(i + 1).trim();
                return evalExpr(left) || evalExpr(rigth);
            }
        }

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')')
                parenCount++;
            if (c == '(')
                parenCount--;
            if (parenCount == 0 && i > 0 && expr.charAt(i) == '&' && expr.charAt(i - 1) == '&') {
                String left = expr.substring(0, i - 1).trim();
                String rigth = expr.substring(i + 1).trim();
                return evalExpr(left) && evalExpr(rigth);
            }
        }

        if (expr.startsWith("!")) {
            return !evalExpr(expr.substring(1));
        }

        if (expr.equals("true"))
            return true;
        if (expr.equals("false"))
            return false;
        throw new RuntimeException("Expresión inválida: " + expr);
    }

    private static boolean outParent(String expr) {
        if (!expr.startsWith("(") || !expr.endsWith(")"))
            return false;
        int count = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(')
                count++;
            if (c == ')')
                count--;
            if (count == 0 && i < expr.length() - 1)
                return false;
        }
        return count == 0;
    }
}