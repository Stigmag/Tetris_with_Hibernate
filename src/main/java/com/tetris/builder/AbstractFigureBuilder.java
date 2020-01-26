package com.tetris.builder;

import com.tetris.db.repositories.impl.FigureRepository;
import com.tetris.db.repositories.impl.FigureTypeRepository;
import com.tetris.db.repositories.impl.GameRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public abstract class AbstractFigureBuilder implements FigureBuilder {
    final FigureRepository repository = new FigureRepository();
final FigureTypeRepository repositoryType=new FigureTypeRepository();
final GameRepository gameRepository= new GameRepository();
    @Getter
    private final int gameId;

}
