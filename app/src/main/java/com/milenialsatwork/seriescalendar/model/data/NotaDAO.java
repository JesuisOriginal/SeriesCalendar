package com.milenialsatwork.seriescalendar.model.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotaDAO {

    private final static ArrayList<Series> notes = new ArrayList<>();

    public List<Series> todos() {
        return (List<Series>) notes.clone();
    }

    public void insert(Series... notes) {
        NotaDAO.notes.addAll(Arrays.asList(notes));
    }

    public void altera(int position, Series card) {
        notes.set(position, card);
    }

    public void remove(int position) {
        notes.remove(position);
    }

    public void swap(int starterPosition, int finalPosition) {
        Collections.swap(notes, starterPosition, finalPosition);
    }

    public void removeTodos() {
        notes.clear();
    }
}
