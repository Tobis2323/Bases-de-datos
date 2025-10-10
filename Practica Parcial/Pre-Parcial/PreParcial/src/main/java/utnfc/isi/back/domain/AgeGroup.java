package utnfc.isi.back.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * AgeGroup -> AGE_GROUPS (ID_AGE_GROUP, CODE)
 * CODE puede ser:
 *  - "12"    => valor exacto (min=max=12)
 *  - "6-12"  => rango cerrado [6,12]
 *  - "13+"   => abierto superior [13, +inf)
 */
@Entity
@Table(name = "AGE_GROUPS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AgeGroup {

    @Id
    @SequenceGenerator(name = "ageSeq", sequenceName = "SEQ_AGE_GROUP_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ageSeq")
    @Column(name = "ID_AGE_GROUP")
    private Integer id;

    @Column(name = "CODE", nullable = false, length = 16)
    private String code;

    // Campos derivados NO persistidos: minAge / maxAge (puede ser null si es abierto)
    @Transient
    private Integer minAge;

    @Transient
    private Integer maxAge; // null => sin tope

    @PostLoad @PostPersist
    private void parseCode() {
        // Al cargar/guardar, parseamos CODE a min/max una vez
        if (code == null || code.isBlank()) {
            minAge = null; maxAge = null; return;
        }
        String c = code.trim();

        if (c.endsWith("+")) { // p.e. "13+"
            minAge = Integer.parseInt(c.substring(0, c.length() - 1)); // sin el '+'
            maxAge = null; // sin tope
            return;
        }
        if (c.contains("-")) { // p.e. "6-12"
            String[] parts = c.split("-"); // Dividir en dos partes
            minAge = Integer.parseInt(parts[0].trim());
            maxAge = Integer.parseInt(parts[1].trim());
            return;
        }
        // caso exacto "12"
        int v = Integer.parseInt(c);
        minAge = v;
        maxAge = v;
    }

    /** Sugerencia del enunciado: ¿la edad `age` pertenece al rango? */
    public boolean matchesAge(int age) {
        if (minAge == null && maxAge == null) return false; // sin datos
        if (maxAge == null) return age >= minAge;           // abierto superior
        if (minAge.equals(maxAge)) return age == minAge;    // exacto
        return age >= minAge && age <= maxAge;              // rango cerrado
    }
}
