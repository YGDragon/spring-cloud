package web.ygdragon.notepadapp.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.ygdragon.notepadapp.aspects.TrackUserAction;
import web.ygdragon.notepadapp.exception.NoteNotFoundException;
import web.ygdragon.notepadapp.model.Note;
import web.ygdragon.notepadapp.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    /**
     * Finding one note by ID
     *
     * @param id ID finding note
     * @return Findable note
     */
    public @NonNull Note findNoteByID(Long id) {
        return noteRepository.findById(id).orElse(new Note());
    }

    /**
     * Find all notes
     *
     * @return List of available notes
     */
    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Creating new note
     *
     * @param note Data for new note
     * @return New note
     */
    @TrackUserAction
    public Note createNote(Note note) {
        Note newNote = new Note();
        newNote.setTopic(note.getTopic());
        newNote.setTextNote(note.getTextNote());
        return noteRepository.save(newNote);
    }

    /**
     * Updating note
     *
     * @param note   Updatable note
     * @param noteId ID of finding note
     * @return Updated notes
     */
    @TrackUserAction
    public Note updateNote(Note note, Long noteId) {
        Note uNote;
        try {
            uNote = noteRepository.findById(noteId).orElse(note);
            if (!uNote.equals(note)) {
                uNote.setTopic(note.getTopic());
                uNote.setTextNote(note.getTextNote());
                return noteRepository.save(uNote);
            } else {
                System.out.println("Error>> ID not found");
            }
        } catch (NoteNotFoundException e) {
            throw new NoteNotFoundException(noteId);
        }
        return uNote;
    }

    /**
     * Deleting note
     *
     * @param id ID of deleting note
     */
    @TrackUserAction
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
