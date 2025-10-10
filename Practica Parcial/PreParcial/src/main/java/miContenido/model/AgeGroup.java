package miContenido.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "AGE_GROUPS")
public class AgeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "age_group_seq")
    @SequenceGenerator(name = "age_group_seq", sequenceName = "SEQ_AGE_GROUP_ID", allocationSize = 1)
    @Column(name = "ID_AGE_GROUP")
    private Integer idAgeGroup;

    @Column(name = "CODE", nullable = false, length = 16, unique = true)
    private String code;

    // Getters and setters...
}
