package com.tetris.db.repositories.hibernateTable;

import com.tetris.game.handler.MoveEvent;

import javax.persistence.*;

@Table(name = "Move_Table")
public class MoveTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Game")
    private int gameId;
    @Column(name = "MoveEvent")
    private MoveEvent event;

    public MoveTable(int gameId, MoveEvent event) {
        this.gameId = gameId;
        this.event = event;
    }


}
