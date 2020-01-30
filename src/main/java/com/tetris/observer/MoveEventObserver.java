package com.tetris.observer;

import com.tetris.game.Game;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.db.DbMoveHandler;
import com.tetris.model.GameState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveEventObserver extends Observer {

    private GameObserver game;

    public MoveEventObserver(DbMoveHandler subject){
        this.subjectMove = subject;
        this.subjectMove.attach(this);
    }

    @Override
    public void update() {
       log.info("MoveEvent is finished");
       subjectMove.event= null;
}

}
