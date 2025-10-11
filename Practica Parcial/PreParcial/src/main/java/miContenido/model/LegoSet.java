package miContenido.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.*;

@Entity
@Data
@Table(name = "LEGO_SETS")
public class LegoSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lego_set_seq")
    @SequenceGenerator(name = "lego_set_seq", sequenceName = "SEQ_LEGO_SET_ID", allocationSize = 1)
    @Column(name = "ID_SET")
    private Integer idSet;

    @Column(name = "PROD_ID", nullable = false)
    private Integer prodId;

    @Column(name = "SET_NAME", nullable = false, length = 200)
    private String setName;

    @Column(name = "PROD_DESC", length = 2048)
    private String prodDesc;

    @Column(name = "REVIEW_DIFFICULTY", length = 32)
    private String reviewDifficulty;

    @Column(name = "PIECE_COUNT")
    private Integer pieceCount;

    @Column(name = "STAR_RATING", precision = 3, scale = 1)
    private BigDecimal starRating;

    @Column(name = "LIST_PRICE", precision = 10, scale = 2)
    private BigDecimal listPrice;

    @Column(name = "NUM_REVIEWS")
    private Integer numReviews;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "THEME_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Theme theme;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "AGE_GROUP_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AgeGroup ageGroup;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Country country;



    // Getters and setters...
}
