package utnfc.isi.back.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * LegoSet -> LEGO_SETS
 * PK: ID_SET (secuencia SEQ_LEGO_SET_ID)
 * FKs: THEME_ID -> THEMES, AGE_GROUP_ID -> AGE_GROUPS, COUNTRY_ID -> COUNTRIES
 * Otras columnas: PROD_ID, SET_NAME, PROD_DESC, REVIEW_DIFFICULTY,
 *                 PIECE_COUNT, STAR_RATING, LIST_PRICE
 */
@Entity
@Table(name = "LEGO_SETS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(exclude = {"theme","ageGroup","country"})
public class LegoSet {

    @Id
    @SequenceGenerator(name = "legoSeq", sequenceName = "SEQ_LEGO_SET_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "legoSeq")
    @Column(name = "ID_SET")
    private Integer id;

    @Column(name = "PROD_ID", nullable = false)
    private Integer productId;

    @Column(name = "SET_NAME", nullable = false, length = 200)
    private String setName;

    @Column(name = "PROD_DESC", length = 2048)
    private String productDescription;

    @Column(name = "REVIEW_DIFFICULTY", length = 32)
    private String reviewDifficulty;

    @Column(name = "PIECE_COUNT")
    private Integer pieceCount;

    @Column(name = "STAR_RATING", precision = 3, scale = 1)
    private java.math.BigDecimal starRating;

    @Column(name = "LIST_PRICE", precision = 10, scale = 2)
    private java.math.BigDecimal listPrice;

    // ---- Relaciones obligatorias (ManyToOne) ----
    @ManyToOne(optional = false)
    @JoinColumn(name = "THEME_ID", nullable = false)
    private Theme theme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "AGE_GROUP_ID", nullable = false)
    private AgeGroup ageGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;
}
