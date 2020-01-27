package com.tetris.db.repositories.hibernateTable;

import com.tetris.db.repositories.HibernateUtil;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"games"})
@Table(name = "figure_type")

public class FigureTypeTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "figure_type_id")
    private int figureTypeId;

    @Column(name = "figure_structure")
    private String figureStructure;

    @ManyToMany( fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "figure",
            joinColumns = {@JoinColumn(name = "figure_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id")}
    )
    private List<GameTable> games;

    public FigureTypeTable(int figureTypeId, String figureStructure) {
        this.figureTypeId = figureTypeId;
        this.figureStructure = figureStructure;
    }

    public FigureTypeTable(int figureTypeId, List<GameTable> games) {
        this.figureTypeId = figureTypeId;
        this.games = games;
    }

    public int getFigureTypeId() {
        return figureTypeId;
    }

    public String getFigureStructure() {
        return figureStructure;

    }

    public FigureTypeTable() {
    }



    public List<GameTable> getGames() {

        return games;
    }

    public void setGames(List<GameTable> games) {
        this.games = games;
    }
}