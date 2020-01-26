package com.tetris.parse;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tetris.game.Figure;

import java.io.IOException;

public class JsonParser {
    public static Figure parseToFigure(String figureCoordinate) {
        ObjectMapper mapper = new ObjectMapper();
        Figure figure;
        Gson gson = new Gson();



        try {
        figure = gson.fromJson(figureCoordinate,Figure.class);
        return figure;
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;

    }

    public static String parseToJson(Figure f){
        String jsonInString= null;
        try{
            ObjectMapper mapper = new ObjectMapper();

            // mapper.writeValue(new File("c:\\figure.json"), f);

            jsonInString = mapper.writeValueAsString(f);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
