import java.util.Arrays;

import models.Data;
import view.Table;
import view.Screen;
import view.Dialogs;

/**
 * 
 * 
 * Desarrollado por el equipo 4
 * Héctor Jesús García Monroy
 * Emiliano Hidalgo Gasca
 * Gael González Méndez
 */
public class Main {
        public static void main(String[] args) {
                /**
                 * Ejecutable principal
                 * 
                 */
                System.out.println("Hello world");
                int propositions;
                String expression;
                Data data;
                Table table;
                Screen screen;
                String[] headers = { "p", "q", "r", "s" };
                String expressionMessage;
                String joinedHeaders;

                propositions = Dialogs.getIntegerInRange(
                                "Número de proposiciones",
                                "Ingresa el número de proposiciones",
                                1, 4,
                                true);

                headers = Arrays.copyOfRange(headers, 0, propositions);
                joinedHeaders = String.join(", ", headers);

                expressionMessage = String.format("Escribe la operación lógica \nValores:%s\nOperadores: &&,||, !)",
                                joinedHeaders);
                expression = Dialogs.getString("Expresión", expressionMessage);

                data = new Data(Arrays.copyOfRange(headers, 0, propositions), propositions);
                data.addOperation(expression);

                Dialogs.showDialog("Resultado",
                                String.format("La expresión ingresada es una %s", data.getLastPropositionType()));

                screen = new Screen("Proposiciones - Equipo 4", 500, 400);

                table = new Table(data);

                screen.add(table);
                screen.showScreen();

        }
}
