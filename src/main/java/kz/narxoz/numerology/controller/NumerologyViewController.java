package kz.narxoz.numerology.controller;

import kz.narxoz.numerology.dto.NumerologyForm;
import kz.narxoz.numerology.dto.NumerologyRequest;
import kz.narxoz.numerology.dto.NumerologyResponse;
import kz.narxoz.numerology.mapper.NumerologyMapper;
import kz.narxoz.numerology.model.NumerologyResult;
import kz.narxoz.numerology.service.NumerologyService;
import kz.narxoz.numerology.service.OpenAiAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class NumerologyViewController {

    private final NumerologyService numerologyService;
    private final OpenAiAnalysisService openAiAnalysisService;
    private final NumerologyMapper mapper;

    public NumerologyViewController(NumerologyService numerologyService,
                                    OpenAiAnalysisService openAiAnalysisService,
                                    NumerologyMapper mapper) {
        this.numerologyService = numerologyService;
        this.openAiAnalysisService = openAiAnalysisService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new NumerologyForm());
        }
        return "index";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute("form") NumerologyForm form, Model model) {
        try {
            NumerologyRequest request = new NumerologyRequest(
                    form.getName(),
                    form.getDay() == null ? 0 : form.getDay(),
                    form.getMonth() == null ? 0 : form.getMonth(),
                    form.getYear() == null ? 0 : form.getYear()
            );

            NumerologyResult result = numerologyService.calculate(request);
            String aiAnalysis = openAiAnalysisService.generateAnalysis(result);
            NumerologyResponse response = mapper.toResponse(result, aiAnalysis);

            model.addAttribute("result", response);
        } catch (ResponseStatusException ex) {
            model.addAttribute("error", ex.getReason());
        } catch (Exception ex) {
            model.addAttribute("error", "Не удалось выполнить расчет: " + ex.getMessage());
        }

        return "index";
    }
}
