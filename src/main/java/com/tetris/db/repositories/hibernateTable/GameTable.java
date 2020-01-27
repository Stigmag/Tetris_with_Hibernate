package com.tetris.db.repositories.hibernateTable;



import com.tetris.model.GameState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table
@Data
@ToString(exclude = {"figures"})
public class GameTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private GameState state;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "figure",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "figure_type_id")}
    )
  private List<FigureTypeTable> figures;


    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MoveTable> moves;


    public GameTable( GameState state) {

        this.state = state;
    }

    public GameTable() {
    }

    public int getGameId() {
        return gameId;
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