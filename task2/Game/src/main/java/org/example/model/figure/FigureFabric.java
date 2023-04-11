package org.example.model.figure;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
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

    public Figure create(String line) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String [] arguments = line.split("#");
        String type = arguments[3];
        System.out.println(type);
        try{
            String fig_type = String.valueOf(types.get(type));
            Class<?> command_class = Class.forName(fig_type);
            Figure fig = (Figure) command_class.getDeclaredConstructor().newInstance();
            fig.setName(arguments[0]);
            fig.setAttack(Integer.valueOf(arguments[1]));
            fig.setDefense(Integer.valueOf(arguments[2]));
            return fig;
        }catch(Exception e){
            throw new RuntimeException(line);
        }
    }
}
