package com.tetris.db.repositories.hibernateTable;



import com.tetris.model.GameState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table
@Data

public class GameTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private GameState state;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "figure",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "figure_type_id")}
    )
  public List<FigureTypeTable> figures;


    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MoveTable> moves;


    public GameTable(int gameId, GameState state) {
        this.gameId = gameId;
        this.state = state;
    }

    public GameTable() {
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setFigures(List<FigureTypeTable> figures) {
        this.figures = figures;
    }

    public void setMoves(List<MoveTable> moves) {
        this.moves = moves;
    }

    @Override
    public final String toString() {
        return "GameTable{" +
                "gameId=" + gameId +
                ", state=" + state +
                ", figures=" + figures +
                ", moves=" + moves +
                '}';
    }
}