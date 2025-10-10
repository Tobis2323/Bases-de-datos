package utnfc.isi.back.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Theme -> THEMES (ID_THEME, NAME)
 */
@Entity
@Table(name = "THEMES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Theme {

    @Id
    @SequenceGenerator(name = "themeSeq", sequenceName = "SEQ_THEME_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "themeSeq")
    @Column(name = "ID_THEME")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 120)
    private String name;
}
