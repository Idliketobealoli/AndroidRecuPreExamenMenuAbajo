package com.example.androidrecupreexamenmenuabajo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "nota")
public class Nota implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private Prio prio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Prio getPrio() {
        return prio;
    }

    public void setPrio(Prio prio) {
        this.prio = prio;
    }
}
