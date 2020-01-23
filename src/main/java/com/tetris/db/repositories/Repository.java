package com.tetris.db.repositories;

import java.sql.Connection;

public interface Repository {

    default Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

}
