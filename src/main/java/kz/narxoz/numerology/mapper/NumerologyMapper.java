package kz.narxoz.numerology.mapper;

import kz.narxoz.numerology.dto.NumerologyResponse;
import kz.narxoz.numerology.model.NumerologyResult;
import org.springframework.stereotype.Component;

@Component
public class NumerologyMapper {

    public NumerologyResponse toResponse(NumerologyResult result, String aiAnalysis) {
        return new NumerologyResponse(
                result.getId(),
                result.getOriginalName(),
                result.getTransliteratedName(),
                result.getNameNumber(),
                result.getNameMeaning(),
                result.getConsciousnessNumber(),
                result.getConsciousnessMeaning(),
                result.getMissionNumber(),
                result.getMissionMeaning(),
                aiAnalysis
        );
    }
}
