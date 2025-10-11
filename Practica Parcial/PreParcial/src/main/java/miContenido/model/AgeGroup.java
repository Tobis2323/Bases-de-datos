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

    /**
     * Retorna true si la edad indicada pertenece al rango representado por CODE.
     * Formatos soportados:
     *  - "N"      => exacto (min = max = N)
     *  - "A-B"    => intervalo cerrado [A, B]
     *  - "N+"     => intervalo abierto superior [N, +∞)
     * Reglas: si max es null => sin tope superior (age >= min).
     */
    public boolean matchesAge(int age) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        String c = code.trim();
        try {
            Range r = parseRange(c);
            if (r == null || r.min == null) return false;
            if (r.max == null) {
                return age >= r.min; // sin tope superior
            }
            if (r.min.equals(r.max)) {
                return age == r.min; // valor exacto
            }
            return age >= r.min && age <= r.max; // intervalo cerrado
        } catch (NumberFormatException ex) {
            // Formato inválido en CODE
            return false;
        }
    }

    // --- Helpers privados ---
    private static Range parseRange(String codeLiteral) {
        String s = codeLiteral.trim();
        // Aceptar espacios alrededor de '-' o '+'
        // Caso sufijo '+' => N+
        if (s.endsWith("+")) {
            String num = s.substring(0, s.length() - 1).trim();
            Integer min = parsePositiveInt(num);
            return new Range(min, null);
        }
        // Caso con guión 'A-B'
        int dash = s.indexOf('-');
        if (dash >= 0) {
            String left = s.substring(0, dash).trim();
            String right = s.substring(dash + 1).trim();
            Integer min = parsePositiveInt(left);
            Integer max = parsePositiveInt(right);
            // Normalizar si viniera invertido
            if (min != null && max != null && min > max) {
                int tmp = min; min = max; max = tmp;
            }
            return new Range(min, max);
        }
        // Caso número exacto
        Integer v = parsePositiveInt(s);
        return new Range(v, v);
    }

    private static Integer parsePositiveInt(String s) {
        if (s == null) return null;
        String t = s.trim();
        if (t.isEmpty()) return null;
        int val = Integer.parseInt(t);
        if (val < 0) throw new NumberFormatException("edad negativa");
        return val;
    }

    @Getter
    @AllArgsConstructor
    private static class Range {
        private final Integer min;
        private final Integer max; // null => sin tope superior
    }
}
