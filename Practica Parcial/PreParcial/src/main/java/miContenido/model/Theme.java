package miContenido.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "THEMES")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theme_seq")
    @SequenceGenerator(name = "theme_seq", sequenceName = "SEQ_THEME_ID", allocationSize = 1)
    @Column(name = "ID_THEME")
    private Integer idTheme;

    @Column(name = "NAME", nullable = false, length = 120, unique = true)
    private String name;

    public void setId(int i) {
        this.idTheme = i;
    }

    // Getters and setters...
}
