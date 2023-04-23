package co.develhope.SimulazioneCheckpoint3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

public class Newsletter {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;
}
