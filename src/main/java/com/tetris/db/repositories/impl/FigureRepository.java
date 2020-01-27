package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.hibernateTable.FigureTable;
import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
import com.tetris.db.repositories.hibernateTable.GameTable;
import com.tetris.game.Figure;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;

public class FigureRepository extends HibernateUtil {
FigureTypeRepository repository= new FigureTypeRepository();


  /*  public void saveNewFigure(GameTable game) {

        FigureTypeTable figure= new FigureTypeTable();
        figure.getGames().add(game);

        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(figure);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }



    }*/

    public List<Figure> getFiguresByGameId(int gameId) {

        List<Figure> resultList = new ArrayList<>();
        FigureTypeTable currentfigure= new FigureTypeTable();
        Set<Figure> figureSet= null;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects

            Query query = session.createQuery("from FigureTypeTable rl join Figure arc arc.game_id = :paramName and arc.figure_type_id = rl.figure_type_id");
            query.setParameter("paramName",gameId);
            List<FigureTypeTable> list= ((org.hibernate.query.Query) query).list();
        // currentfigure=(FigureTable) session.load(FigureTable.class,gameId);
            for (FigureTypeTable figure: list
                 ) { figureSet=repository.getFigures(figure.getFigureTypeId());

            }

            resultList.addAll(figureSet) ;
        }
        catch (Exception e) {
            if (transaction != null) {
          // transaction.rollback();
            }
            e.printStackTrace();
        }


        return resultList;

    }
}

