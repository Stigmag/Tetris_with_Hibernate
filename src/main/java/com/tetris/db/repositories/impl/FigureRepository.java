package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.game.handler.MoveEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FigureRepository implements Repository {
FigureTypeRepository repository= new FigureTypeRepository();
    public void saveNewFigure(int gameId, int figureId) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO FIGURE(GAME_ID,FIGURE_ID) VALUES(?,?)")) {
            {
                try {
                    int i = 0;
                    statement.setInt(++i, gameId);
                    statement.setInt(++i, figureId);

                    statement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public List<Figure> getFiguresByGameId(int gameId) {

        List<Figure> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FIGURE WHERE GAME_ID= ? ")) {

            statement.setInt(1, gameId);


            ResultSet result2 = statement.executeQuery();

            while (result2.next()) {
                Set<Figure> currentfigure=repository.getFigures(result2.getInt("FIGURE_ID"));

           resultList.addAll(currentfigure) ;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());


            // return result.stream().distinct().collect(Collectors.toList());
        }
        return resultList;

    }
}

