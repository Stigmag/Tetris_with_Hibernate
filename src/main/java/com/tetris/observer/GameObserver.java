package com.tetris.observer;

import com.tetris.game.Game;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameObserver extends Observer{

    public GameObserver(Game subject){
        this.subjectGame = subject;
        this.subjectGame.attach(this);
    }

    @Override
    public void update() {
        log.info("MoveEvent is finished");


    }
}
