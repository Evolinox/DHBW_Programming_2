package Enumerations;

public enum Tastes {
    LONDON_DRY_GIN, LOVAGE, MALACCA_GIN, BLACKCURRANT_ROYAL_GIN, RANGPUR, FLOR_DE_SEVILLA;

    @Override
    public String toString() {
        return switch (this) {
            case LONDON_DRY_GIN -> "London Dry Gin";
            case LOVAGE -> "Lovage";
            case MALACCA_GIN -> "Malacca Gin";
            case BLACKCURRANT_ROYAL_GIN -> "Blackcurrant Royal Gin";
            case RANGPUR -> "Rangpur";
            case FLOR_DE_SEVILLA -> "Flor De Sevilla";
        };
    }
}
