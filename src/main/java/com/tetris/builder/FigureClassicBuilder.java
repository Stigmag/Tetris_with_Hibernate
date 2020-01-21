package com.tetris.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tetris.db.repositories.impl.FigureRepository;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import com.tetris.parse.JsonParser;
import lombok.AllArgsConstructor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FigureClassicBuilder extends AbstractFigureBuilder {

    public FigureClassicBuilder(int gameId) {
        super(gameId);
    }

    @Override
    public Figure next(Point boardStartPoint) {

        List<Figure> listfigure= new LinkedList<>();


int k=0;


        repository.saveNewFigure(getGameId(),k++ );
        listfigure=repository.getFiguresByGameId(getGameId());

       return(listfigure.get(new Random().nextInt(listfigure.size())));

    }


}
