package kz.narxoz.numerology.controller;

import kz.narxoz.numerology.dto.NumerologyRequest;
import kz.narxoz.numerology.dto.NumerologyResponse;
import kz.narxoz.numerology.mapper.NumerologyMapper;
import kz.narxoz.numerology.model.NumerologyResult;
import kz.narxoz.numerology.service.NumerologyService;
import kz.narxoz.numerology.service.OpenAiAnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/numerology")
public class NumerologyController {

    private final NumerologyService numerologyService;
    private final OpenAiAnalysisService openAiAnalysisService;
    private final NumerologyMapper mapper;

    public NumerologyController(NumerologyService numerologyService,
                                OpenAiAnalysisService openAiAnalysisService,
                                NumerologyMapper mapper) {
        this.numerologyService = numerologyService;
        this.openAiAnalysisService = openAiAnalysisService;
        this.mapper = mapper;
    }

    @PostMapping
    public NumerologyResponse calculate(@RequestBody NumerologyRequest request) {
        NumerologyResult result = numerologyService.calculate(request);
        String aiAnalysis = openAiAnalysisService.generateAnalysis(result);
        return mapper.toResponse(result, aiAnalysis);
    }
}
