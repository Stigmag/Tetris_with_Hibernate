package com.tetris.db.repositories.hibernateTable;

import com.tetris.model.GameState;

import javax.persistence.*;

@Table(name = "Game_Table")
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Game")
    private int gameId;
    @Column(name = "STATE")
    private String state;

    public GameTable(int gameId, String state) {
        this.gameId = gameId;
        this.state = state;
    }


}
