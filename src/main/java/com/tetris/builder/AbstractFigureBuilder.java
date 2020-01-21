package com.tetris.builder;

import com.tetris.db.repositories.impl.FigureRepository;
import com.tetris.db.repositories.impl.FigureTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public abstract class AbstractFigureBuilder implements FigureBuilder {
    final FigureRepository repository = new FigureRepository();
final FigureTypeRepository repositoryType=new FigureTypeRepository();
    @Getter
    private final int gameId;
}
