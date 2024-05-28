package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("note")
public class NoteRestController {

    private final NoteService noteService;

    @PostMapping("save")
    public ResponseEntity save(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @GetMapping("list")
    public ResponseEntity list() {
        return noteService.allNote();
    }

}
