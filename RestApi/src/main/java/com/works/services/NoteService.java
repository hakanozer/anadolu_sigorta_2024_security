package com.works.services;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository noteRepository;

    public ResponseEntity addNote(Note note) {
        noteRepository.save(note);
        if (note.getNid() == null) {
            return new ResponseEntity(note, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(note, HttpStatus.OK);
    }

    public ResponseEntity allNote() {
        List<Note> noteList = noteRepository.findAll();
        return new ResponseEntity(noteList, HttpStatus.OK);
    }

}
