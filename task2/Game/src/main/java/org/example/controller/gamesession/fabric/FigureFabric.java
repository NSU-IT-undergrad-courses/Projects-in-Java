package org.example.controller.gamesession.fabric;

import org.example.model.figure.Figure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FigureFabric {
    Properties types = new Properties();

    public FigureFabric(String filepath) {
        InputStream in = this.getClass().getResourceAsStream(filepath);
        try {
            types.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Figure create(String line) {
        String[] arguments = line.split("#");
        String type = arguments[3];
        try {
            String fig_type = String.valueOf(types.get(type));
            Class<?> fig_class = Class.forName(fig_type);
            Figure fig = (Figure) fig_class.getDeclaredConstructor().newInstance();
            fig.setName(arguments[0]);
            fig.setAttack(Integer.valueOf(arguments[1]));
            fig.setDefense(Integer.valueOf(arguments[2]));
            String[] trace = arguments[4].split(" ");
            fig.setTrace(trace);
            return fig;
        } catch (Exception e) {
            throw new RuntimeException(line);
        }
    }
}
