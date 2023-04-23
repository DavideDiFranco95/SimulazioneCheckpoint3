package co.develhope.SimulazioneCheckpoint3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
}
