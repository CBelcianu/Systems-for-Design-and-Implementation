package ro.ubb.labproblems;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.labproblems.UI.Console;

public class Main2 {
    public static void main(String args[]) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.labproblems.Config"
                );

        Console console = context.getBean(Console.class);
        console.runConsole();

    }
}
