package com.tetris.db.repositories.impl;

import com.tetris.db.repositories.HibernateUtil;

import com.tetris.db.repositories.hibernateTable.FigureTypeTable;
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

    public GameTable game;

    int number=9;
    public int createNewGame() {
        int gameId=number++;
        game = new GameTable(gameId,GameState.ACTIVE);



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

        addAllTypeFigure(new Point(15 / 2, 0),game);

        return gameId;
    }

    public void finishGame(int gameId) {


    }
    FigureTypeTable figureType= null;

    public  void addAllTypeFigure(Point boardStartPoint,GameTable game)
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
        List<FigureTypeTable> list= new LinkedList<>();
        List<GameTable> gameList= new LinkedList<>();
        for (Figure f:listfigure)
        {

          FigureTypeTable curentFigure= repository.saveNewFigureType(i++, JsonParser.parseToJson(f));


         Transaction transaction = null;

            try (Session session = getSessionFactory().openSession()) {

                transaction = session.beginTransaction();



                session.save(game);
                list.add(curentFigure);

                try{


                    game.setFigures(list);
                    session.save(game);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    session.getSessionFactory().getCurrentSession();
                }

                transaction.commit();



            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

        }
        }



    }


