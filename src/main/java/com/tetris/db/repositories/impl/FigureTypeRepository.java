package com.tetris.db.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tetris.db.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import com.tetris.parse.JsonParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.tetris.db.ConnectionFactory.getConnection;

public class FigureTypeRepository implements Repository {
    public void saveNewFigureType(int figureId, String figure) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO FIGURE_TYPE(FIGURE_ID,COORDINATE) VALUES(?,?)")) {
            {

                try {
                    int i = 0;
                    statement.setInt(++i, figureId);
                    statement.setString(++i, figure);

                    statement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public Set<Figure> getFigures(int figureId) {
        Set<Figure> listofFigure = new HashSet<Figure>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FIGURE_TYPE WHERE FIGURE_ID= ? ")) {

            statement.setInt(1, figureId);


            ResultSet result2 = statement.executeQuery();

            while (result2.next()) {
                listofFigure.add(JsonParser.parseToFigure(result2.getString("COORDINATE")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());


            // return result.stream().distinct().collect(Collectors.toList());
        }

        return listofFigure;
    }



}
