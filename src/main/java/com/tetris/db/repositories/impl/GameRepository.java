package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;

import com.tetris.db.repositories.hibernateTable.GameTable;
import com.tetris.game.Figure;
import com.tetris.model.GameState;
import com.tetris.model.Point;
import com.tetris.parse.JsonParser;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class GameRepository extends HibernateUtil
{
    FigureTypeRepository repository= new FigureTypeRepository();


    public Optional<Integer> getActiveGameId() {

        return Optional.empty();
    }



int number=9;
    public int createNewGame() {
        int gameId=number++;
       addAllTypeFigure(new Point(15 / 2, 0));
        GameTable game = new GameTable(gameId,GameState.ACTIVE.toString());

        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(game);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return gameId;
    }

    public void finishGame(int gameId) {


    }
    public  void addAllTypeFigure(Point boardStartPoint)
    {
        List<Point> points= new LinkedList<>();
        points.add(new Point(0,0));
        points.add(new Point(0,1));
        points.add(new Point(1,0));
        points.add(new Point(1,1));
        Figure figure= new Figure(points,new Point(0,0),boardStartPoint);
        points.clear();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,0));
        points.add(new Point(1,1));
        Figure figure2= new Figure(points,new Point(0,0),boardStartPoint);
        List<Figure> listfigure= new LinkedList<>();
        listfigure.add(figure);
        listfigure.add(figure2);
        int i=0;
        for (Figure f:listfigure)
        {

           repository.saveNewFigureType(i++, JsonParser.parseToJson(f) );

        }
    }

}
