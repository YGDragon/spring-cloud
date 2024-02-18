package web.ygdragon.notepadapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.ygdragon.notepadapp.model.Note;
import web.ygdragon.notepadapp.service.NoteService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notepad")
public class NoteController {
    private final NoteService noteService;

    /**
     * Getting all notes
     *
     * @return notepad - HTML page
     */
    @GetMapping
    public String viewNotepad(Model model) {
        model.addAttribute(
                "notes",
                noteService.findAllNotes());
        return "notepad";
    }

    /**
     * Getting one note by ID
     *
     * @param noteId ID of findable note
     * @return note - HTML page
     */
    @GetMapping("note/{noteId}")
    public String viewNote(Model model, @PathVariable Long noteId) {
        model.addAttribute(
                "note",
                noteService.findNoteByID(noteId));
        return "note";
    }
    /**
     * transition to create.html
     *
     * @return HTML view "/create"
     */
    @GetMapping("/create")
    public String viewCreate() {
        return "create";
    }

    /**
     * Adding new note to the track
     *
     * @param note Addable note
     * @return notepad - HTML page
     */
    @PostMapping("/create")
    public String addDevice(Note note) {
        noteService.createNote(note);
        return "redirect:/notepad";
    }

    /**
     * transition to update.html
     *
     * @return HTML view "/update"
     */
    @GetMapping("/update/{noteId}")
    public String redirectUpdate(Model model, @PathVariable Long noteId) {
        model.addAttribute("note", new Note());
        model.addAttribute("ID", noteId);
        return "update";
    }

    /**
     * Edit data of note
     *
     * @param note   New data from body
     * @param noteId ID note from request
     * @return Updatable note and Http status
     */
    @PostMapping("/update/{noteId}")
    public String updateDevice(Note note, @PathVariable Long noteId) {
        noteService.updateNote(note, noteId);
        return "redirect:/notepad";
    }

    /**
     * Deleting note by ID
     *
     * @param noteId ID of deletable note
     * @return Http status
     */
    @GetMapping("/delete/{noteId}")
    public String deleteByID(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/notepad";
    }
}
