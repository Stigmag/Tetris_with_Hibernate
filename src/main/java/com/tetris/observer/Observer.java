package com.tetris.observer;

import com.tetris.game.Game;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.db.DbMoveHandler;

public abstract class Observer {
    protected DbMoveHandler subjectMove;
    protected Game subjectGame;

    public abstract void update();
}
