package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
import com.tetris.db.repositories.hibernateTable.GameTable;
import com.tetris.db.repositories.hibernateTable.MoveTable;
import com.tetris.game.handler.MoveEvent;
import com.tetris.parse.JsonParser;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class MoveRepository extends HibernateUtil {


    public void sameNewMoveEvent(MoveEvent event) {

        Transaction transaction = null;

        MoveTable moveEvent = new MoveTable(event);

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
