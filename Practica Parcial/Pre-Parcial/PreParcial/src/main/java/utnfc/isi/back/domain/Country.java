package utnfc.isi.back.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Country -> COUNTRIES (ID_COUNTRY, CODE, NAME)
 */
@Entity
@Table(name = "COUNTRIES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Country {

    @Id
    @SequenceGenerator(name = "countrySeq", sequenceName = "SEQ_COUNTRY_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrySeq")
    @Column(name = "ID_COUNTRY")
    private Integer id;           // PK

    @Column(name = "CODE", nullable = false, length = 3)
    private String code;          // índice + único según DDL

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;          // nombre del país
}
