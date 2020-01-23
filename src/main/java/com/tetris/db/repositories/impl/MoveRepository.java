package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.Repository;
import com.tetris.db.repositories.hibernateTable.GameTable;
import com.tetris.db.repositories.hibernateTable.MoveTable;
import com.tetris.game.handler.MoveEvent;
import com.tetris.model.GameState;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MoveRepository extends HibernateUtil {

    public void sameNewMoveEvent(int gameId, MoveEvent event) {
        MoveTable moveEvent = new MoveTable(gameId, event);

        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(moveEvent);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }
}
