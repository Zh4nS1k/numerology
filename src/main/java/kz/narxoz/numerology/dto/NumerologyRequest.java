package kz.narxoz.numerology.dto;

public record NumerologyRequest(
        String name,
        int day,
        int month,
        int year
) {
}
