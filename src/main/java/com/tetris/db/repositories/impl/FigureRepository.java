package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.Repository;
import com.tetris.db.repositories.hibernateTable.FigureTable;
import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
import com.tetris.game.Figure;
import com.tetris.game.handler.MoveEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FigureRepository extends HibernateUtil {
FigureTypeRepository repository= new FigureTypeRepository();
    public void saveNewFigure(int gameId, int figureId) {

        FigureTable figure= new FigureTable(figureId,gameId);
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



    }

    public List<Figure> getFiguresByGameId(int gameId) {

        List<Figure> resultList = new ArrayList<>();



        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
           FigureTable currentfigure=session.load(FigureTable.class,gameId);
            Set<Figure> figureSet=repository.getFigures(currentfigure.getFigureId());
            resultList.addAll(figureSet) ;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return resultList;

    }
}

