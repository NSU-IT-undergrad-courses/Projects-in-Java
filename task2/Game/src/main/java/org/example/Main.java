package org.example;


import org.example.model.figure.Figure;
import org.example.model.figure.FigureFabric;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

class Main {
    public static void main(String args[]) throws FileNotFoundException {
        FigureFabric fabric = new FigureFabric("/fabric/types.properties");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n\r");
        String line = scanner.nextLine();
        System.out.println(line);
        for (int i = 0; i < 10; i++){
            try {
                System.out.println("Niggers "+i);
                line = scanner.nextLine();
                System.out.println(line);
                Figure fig =  fabric.create(line);
                System.out.println("Figure: "+fig.toString()+" i: "+i);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}