package com.rtllabs.roomdatabasedemo.dataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
