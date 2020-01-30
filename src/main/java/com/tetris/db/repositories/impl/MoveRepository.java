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
import java.util.LinkedList;
import java.util.List;

public class MoveRepository extends HibernateUtil {
public GameTable currecntGame= null;
 public  MoveTable moveEvent= null;

    public void sameNewMoveEvent(int gameId, MoveEvent event) {


        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            if(currecntGame== null){
            // save the student objects
                currecntGame= session.byId(GameTable.class).getReference(gameId);}
            // start a transaction
           moveEvent = new MoveTable(currecntGame,event);
            List<MoveTable> moveList=currecntGame.getMoves();

            //moveEvent.setMoveId(moveList.size());
            moveList.add(moveEvent);
           // moveEvent.setGame(game);


            currecntGame.setMoves(moveList);
           // session.saveOrUpdate(game);
            session.saveOrUpdate(moveEvent);


            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }


    }
public List<MoveEvent> getMoveEvent(int gameId)
{
    List<MoveEvent> moveEventList= new LinkedList<>();
    Transaction transaction = null;
    try (Session session = getSessionFactory().openSession()) {
        // start a transaction
        transaction = session.beginTransaction();
        Query query = session.createQuery("from MoveTable where game_id= :paramName ");
        query.setParameter("paramName",gameId);
        List<MoveTable> list = ((org.hibernate.query.Query) query).list();
        // save the student objects
        // figure=(FigureTypeTable) session.load(FigureTypeTable.class,figureId);
        for (MoveTable table : list
        )
        {
            moveEventList.add(table.getMoveEventType());

        }

    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }



    return moveEventList;
}

    }


