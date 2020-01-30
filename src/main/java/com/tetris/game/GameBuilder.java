package com.tetris.game;

import com.tetris.builder.FigureBuilderFactory;
import com.tetris.db.repositories.impl.GameRepository;
import com.tetris.game.handler.db.DbMoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;
import com.tetris.observer.GameObserver;
import com.tetris.observer.MoveEventObserver;

import java.util.Optional;

public class GameBuilder {

    private static int HEIGHT = 15;
    private static int WIDTH = 10;

    private static final GameRepository gameRepository = new GameRepository();

    public static Game build() {
        Optional<Integer> activeGameId =  gameRepository.getActiveGameId();
        return activeGameId.map(GameBuilder::restoreGame).orElseGet(GameBuilder::buildNewGame);
    }

    private static Game restoreGame(int id) {
        DbMoveHandler   handler=  new DbMoveHandler(id);
        new MoveEventObserver(handler);

        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getRestoreBuilder(id));
        Game game= new Game(handler, board);
        new GameObserver(game);
        return game;

    }

    private static Game buildNewGame() {
        int gameId = gameRepository.createNewGame();
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getClassicBuilder(gameId));
        return new Game(new UserMoveHandler(gameId), board);
    }
}
