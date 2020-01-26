package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.hibernateTable.MoveTable;
import com.tetris.game.handler.MoveEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MoveRepository extends HibernateUtil {

    public void sameNewMoveEvent(MoveEvent event) {
        int k=0;
        MoveTable moveEvent = new MoveTable(k++, event);

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
