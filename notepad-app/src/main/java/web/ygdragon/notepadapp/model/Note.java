package web.ygdragon.notepadapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @NonNull
    private String topic;

    @NonNull
    private String textNote;

    private LocalDateTime creationTime;

    public Note() {
        creationTime = LocalDateTime.now();
    }

}
