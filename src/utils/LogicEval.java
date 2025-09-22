package utils;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class LogicEval {
    public static boolean eval(String expression, Map<String, Boolean> variables){
        String expr = expression;
        for (Map.Entry<String, Boolean> entry : variables.entrySet()){
            expr= expr.replaceAll("\\b"+entry.getKey()+ "\\b", entry.getValue().toString());
        }
        expr = expr.replaceAll("\\s+", "");
        //expr = expr.replaceAll("&&", "&");
        //expr = expr.replaceAll("\\|\\|", "|");
        //expr = expr.replaceAll("!", "!");
        System.out.println("Evaluando...  "+expr);
        try{
            return evalExpr(expr);
        } catch (Exception e){
             throw new RuntimeException("Expresión inválida: " + expr+ " - " + e.getMessage());
        }
    }
    
    private static boolean evalExpr(String expr){
        expr = expr.trim();
        while (expr.startsWith("(") && expr.endsWith(")") && matchingParentheses(expr)) {
            expr = expr.substring(1, expr.length() - 1).trim();
        }

        if (expr.startsWith("!")){
            return !evalExpr(expr.substring(1).trim());
        }

        int parenCount=0;
        for (int i = expr.length()-1; i>=0; i--){
            char c= expr.charAt(i);
            if (c==')') parenCount++;
            if (c=='(') parenCount--;
            if (parenCount==0 && i>0){
                if (c == '|' && expr.charAt(i-1) == '|'){
                    String left = expr.substring(0, i-1).trim();
                    String rigth= expr.substring(i+1).trim();
                    return evalExpr(left) || evalExpr(rigth);
                }
            }
        }

        for (int i = expr.length()-1; i>=0; i--){
            char c= expr.charAt(i);
            if (c==')') parenCount++;
            if (c=='(') parenCount--;
            if (parenCount==0 && i>0){
                if (c == '&' && expr.charAt(i-1) == '&'){
                    String left = expr.substring(0, i-1).trim();
                    String rigth= expr.substring(i+1).trim();
                    return evalExpr(left) || evalExpr(rigth);
                }
            }
        }

        if (expr.equals("true")) return true;
        if (expr.equals("false")) return false;
        throw new RuntimeException("Expresión inválida: " + expr);
    }

    private static boolean matchingParentheses(String expr){
        int count=0;
        for (int i =0; i< expr.length();i++){
            if (expr.charAt(i)== '(') count++;
            if (expr.charAt(i)== ')') count--;
            if (count <0) return false;
        }
        return count == 0;
    }
}