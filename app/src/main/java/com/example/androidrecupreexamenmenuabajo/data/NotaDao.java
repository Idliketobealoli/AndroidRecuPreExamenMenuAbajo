package com.example.androidrecupreexamenmenuabajo.data;

import androidx.room.*;

import java.util.List;

@Dao
public interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Nota nota);
    @Delete
    void delete(Nota nota);
    @Delete
    void reset(List<Nota> notas);
    @Query("SELECT * FROM nota")
    List<Nota> findAll();
    @Query("SELECT * FROM nota WHERE title = :title")
    List<Nota> findByTitle(String title);
}
