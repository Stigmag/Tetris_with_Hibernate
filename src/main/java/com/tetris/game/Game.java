package com.tetris.game;

import com.tetris.db.repositories.HibernateUtil;
import com.tetris.db.repositories.impl.MoveRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.model.GameState;
import com.tetris.observer.Observer;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.tetris.model.GameState.ACTIVE;
import static com.tetris.model.GameState.STOP;


public class Game {

    private final MoveHandler moveHandler;
    private final Board board;

    public Game(MoveHandler moveHandler, Board board) {
        this.moveHandler = moveHandler;
        this.board = board;
    }

    public List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void start() {
        GameState state = ACTIVE;

        while (state == ACTIVE) {

            state = board.doGame(moveHandler.getNewMoveEvent());
        }
        if(state==STOP)
        {
            state= board.doGame(MoveEvent.MOVE_DOWN);
        }
    }
}
