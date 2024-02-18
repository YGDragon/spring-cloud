package web.ygdragon.notepadapp.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.ygdragon.notepadapp.model.Note;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @NonNull Optional<Note> findById(@NonNull Long noteId);
}
