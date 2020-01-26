package com.tetris.db.repositories.hibernateTable;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Figure_Table")
public class FigureTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="increment",strategy = "increment")
    @Column(name = "id")
    private int id;

    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="id")
    private FigureTypeTable figureId;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="id_Game")
    private GameTable gameId;

    public FigureTable() {

    }

    public FigureTable(FigureTypeTable figureId, GameTable gameId) {
        this.figureId = figureId;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public FigureTypeTable getFigureId() {
        return figureId;
    }

    public GameTable getGameId() {
        return gameId;
    }

    public void setFigureId(FigureTypeTable figureId) {
        this.figureId = figureId;
    }

    public void setGameId(GameTable gameId) {
        this.gameId = gameId;
    }
}
