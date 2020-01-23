package com.tetris.db.repositories.hibernateTable;

import javax.persistence.*;
@Table(name = "FigureType_Table")
public class FigureTypeTable {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_Figure")
        private int figureId;
        @Column(name = "COORDINATE")
        private String figure;

        public FigureTypeTable(int figureId, String figure) {
            this.figureId = figureId;
            this.figure = figure;
        }

    public int getFigureId() {
        return figureId;
    }

    public String getFigure() {
        return figure;
    }
}
