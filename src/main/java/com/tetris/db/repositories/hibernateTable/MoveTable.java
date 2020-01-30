package com.tetris.db.repositories.hibernateTable;

import com.tetris.game.handler.MoveEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name="Move_table")
@Data

public class MoveTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "move_id")
    private int moveId;

    @Column(name = "move_event_type")
    @Enumerated(EnumType.STRING)
    private MoveEvent moveEventType;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameTable game;

    public MoveTable(GameTable game, MoveEvent moveEventType) {

this.game=game;

        this.moveEventType = moveEventType;
    }

    public MoveTable(MoveEvent moveEventType, GameTable game) {
        this.moveEventType = moveEventType;
        this.game = game;
    }
    public MoveTable() {



    }


    public int getMoveId() {
        return moveId;
    }

    public MoveEvent getMoveEventType() {
        return moveEventType;
    }

    public GameTable getGame() {
        return game;
    }

    public void setGame(GameTable game) {
        this.game = game;
    }
}


