package miContenido.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @SequenceGenerator(name = "country_seq", sequenceName = "SEQ_COUNTRY_ID", allocationSize = 1)
    @Column(name = "ID_COUNTRY")
    private Integer idCountry;

    @Column(name = "CODE", nullable = false, length = 3, unique = true)
    private String code;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    // Getters and setters...
}

