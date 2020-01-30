package com.tetris.game.handler.db;

import com.tetris.observer.Observer;
import com.tetris.db.repositories.impl.MoveRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;

import java.util.ArrayList;
import java.util.List;

public class DbMoveHandler implements MoveHandler {

    private final UserMoveHandler userMoveHandler;
    private final int restoreGameId;
    private final MoveRepository moveRepository = new MoveRepository();
    int i=0;

   public MoveEvent event;

   public List<MoveEvent> moveEventList= null;
    private List<Observer> observers = new ArrayList<Observer>();

    public DbMoveHandler(int restoreGameId) {
        this.restoreGameId = restoreGameId;
        userMoveHandler = new UserMoveHandler(restoreGameId);
       moveEventList= moveRepository.getMoveEvent(restoreGameId);
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public MoveEvent getNewMoveEvent() {



        do {

            event = moveEventList.get(i);


            if(i==(moveEventList.size()-1)){
                notifyAllObservers();
            break;}
            i++;
        } while (event == null );

        return event;

    }

}
