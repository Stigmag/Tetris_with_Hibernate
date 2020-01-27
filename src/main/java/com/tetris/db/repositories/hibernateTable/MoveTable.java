package com.tetris.db.repositories.hibernateTable;

import com.tetris.game.handler.MoveEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table
@Data
public class MoveTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "move_id")
    private int moveId;

    @Column(name = "move_event_type")
    @Enumerated(EnumType.STRING)
    private MoveEvent moveEventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false, unique = true)
    private GameTable game;

    public MoveTable( MoveEvent moveEventType) {


        this.moveEventType = moveEventType;
    }

    public MoveTable(MoveEvent moveEventType, GameTable game) {
        this.moveEventType = moveEventType;
        this.game = game;
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


