package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.Repository;
import com.tetris.game.handler.MoveEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoveRepository implements Repository {

    public void sameNewMoveEvent(int gameId, MoveEvent event) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO MOVE(GAME_ID,MOVE_EVENT) VALUES(?,?)")) {
            {
                try {
                    int i = 0;
                    statement.setInt(++i, gameId);
                    statement.setString(++i, event.toString());
                    statement.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}
