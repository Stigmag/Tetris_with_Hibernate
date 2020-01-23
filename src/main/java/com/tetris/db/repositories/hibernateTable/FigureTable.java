package com.tetris.db.repositories.hibernateTable;

import javax.persistence.*;

@Table(name = "Figure_Table")
public class FigureTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Figure")
    private int figureId;
    @Column(name = "id_Game")
    private int gameId;

    public FigureTable(int figureId, int gameId) {
        this.figureId = figureId;
        this.gameId = gameId;
    }

    public int getFigureId() {
        return figureId;
    }

    public int getGameId() {
        return gameId;
    }
}
