package kz.narxoz.numerology.dto;

public record NumerologyResponse(
        Long id,
        String originalName,
        String transliteratedName,
        int nameNumber,
        String nameMeaning,
        int consciousnessNumber,
        String consciousnessMeaning,
        int missionNumber,
        String missionMeaning,
        String aiAnalysis
) {
}
