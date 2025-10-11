package miContenido.model;

public class CountryCostRating {
    private final String code;
    private final String name;
    private final Double avgCostPerStar;

    public CountryCostRating(String code, String name, Double avgCostPerStar) {
        this.code = code;
        this.name = name;
        this.avgCostPerStar = avgCostPerStar;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getAvgCostPerStar() {
        return avgCostPerStar;
    }
}
