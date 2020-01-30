package com.tetris.builder;

import com.tetris.game.Figure;
import com.tetris.model.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FigureRestoreBuilder extends AbstractFigureBuilder {
    private final FigureBuilder builder;

    public FigureRestoreBuilder(int gameId, FigureBuilder builder) {
        super(gameId);
        this.builder = builder;
    }


    @Override
    public Figure next(Point boardStartPoint) {
        List<Figure> listfigure= new LinkedList<>();
        //  repository.saveNewFigure(new GameTable(gameRepository.game.getGameId(),gameRepository.game.getState()));
        listfigure=repository.getFiguresByGameId(getGameId());

        return(listfigure.get(new Random().nextInt(listfigure.size())));
    }
}
