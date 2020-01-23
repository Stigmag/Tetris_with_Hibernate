package com.tetris.db.repositories.impl;


import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.Repository;
import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import com.tetris.parse.JsonParser;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.tetris.db.ConnectionFactory.getConnection;

public class FigureTypeRepository extends HibernateUtil {

    public void saveNewFigureType(int figureId, String figure) {
        FigureTypeTable figureType= new FigureTypeTable(figureId,figure);
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(figureType);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }


    public Set<Figure> getFigures(int figureId) {
        Set<Figure> listOfFigure = new HashSet<Figure>();
        FigureTypeTable figure=null;

        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
           figure=(FigureTypeTable) session.load(FigureTypeTable.class,figureId);

            listOfFigure.add(JsonParser.parseToFigure(figure.getFigure()));
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }







        return listOfFigure;
    }



}
