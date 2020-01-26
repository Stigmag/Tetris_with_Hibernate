package com.tetris.db.repositories.impl;


import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.hibernateTable.FigureTable;
import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
import com.tetris.game.Figure;
import com.tetris.parse.JsonParser;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



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


        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FigureTypeTable where id_Figure = :paramName");
            query.setParameter("paramName", figureId);
            List<FigureTypeTable> list= ((org.hibernate.query.Query) query).list();
            // save the student objects
          // figure=(FigureTypeTable) session.load(FigureTypeTable.class,figureId);
            for (FigureTypeTable figure: list
                 ) {  listOfFigure.add(JsonParser.parseToFigure(figure.getFigureStructure()));

            }

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
