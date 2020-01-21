package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.Repository;
import com.tetris.game.Board;
import com.tetris.game.Figure;
import com.tetris.model.GameState;
import com.tetris.model.Point;
import com.tetris.parse.JsonParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GameRepository implements Repository {
    FigureTypeRepository repository= new FigureTypeRepository();


    public Optional<Integer> getActiveGameId() {



        return Optional.empty();
    }
int number=9;
    public int createNewGame() {
        int gameId=number++;
       addAllTypeFigure(new Point(15 / 2, 0));
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO GAME(GAME_ID,STATE ) VALUES(?,?)")) {
            {
                try {
                    int i = 0;
                    statement.setInt(++i,gameId);
                   statement.setString(++i, GameState.ACTIVE.toString());
                    statement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gameId;
    }

    public void finishGame(int gameId) {


    }
    public  void addAllTypeFigure(Point boardStartPoint)
    {
        List<Point> points= new LinkedList<>();
        points.add(new Point(0,0));
        points.add(new Point(0,1));
        points.add(new Point(1,0));
        points.add(new Point(1,1));
        Figure figure= new Figure(points,new Point(0,0),boardStartPoint);
        points.clear();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,0));
        points.add(new Point(1,1));
        Figure figure2= new Figure(points,new Point(0,0),boardStartPoint);
        List<Figure> listfigure= new LinkedList<>();
        listfigure.add(figure);
        listfigure.add(figure2);
        int i=0;
        for (Figure f:listfigure)
        {

           repository.saveNewFigureType(i++, JsonParser.parseToJson(f) );

        }
    }

}
