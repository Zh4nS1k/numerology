package kz.narxoz.numerology.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.narxoz.numerology.model.NumerologyResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiAnalysisService {

    private static final String OPENAI_CHAT_URL = "https://api.openai.com/v1/chat/completions";
    private static final String SYSTEM_PROMPT = """
            Ты опытный нумеролог и консультант.
            Давай содержательный, но лаконичный разбор с привязкой к конкретным числам и имени.
            Стиль: уважительный, уверенный, без эзотерического жаргона, 4-6 абзацев плюс список рекомендаций в конце.
            """;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String apiKey;
    private final String model;

    public OpenAiAnalysisService(
            @Value("${spring.ai.openai.api-key:}") String apiKey,
            @Value("${spring.ai.openai.model:gpt-4o}") String model
    ) {
        this.apiKey = apiKey;
        this.model = model;
    }

    public String generateAnalysis(NumerologyResult result) {
        if (apiKey == null || apiKey.isBlank()) {
            return "OpenAI API key is not configured.";
        }

        String prompt = buildPrompt(result);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", SYSTEM_PROMPT),
                        Map.of("role", "user", "content", prompt)
                )
        );

        try {
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            String response = restTemplate.postForObject(OPENAI_CHAT_URL, entity, String.class);
            return extractContent(response);
        } catch (RestClientException ex) {
            return "Не удалось отправить запрос в OpenAI: " + ex.getMessage();
        } catch (IOException ex) {
            return "Ошибка чтения ответа OpenAI: " + ex.getMessage();
        }
    }

    private String buildPrompt(NumerologyResult result) {
        return """
                Данные для разбора:
                - Имя: %s (транслитерация: %s)
                - Число имени: %d - %s
                - Число сознания: %d - %s
                - Число миссии: %d - %s

                Сформируй глубокий анализ:
                1) Краткий вывод в 2-3 предложения, опираясь на числа.
                2) Сильные стороны и зоны роста, с прямыми ссылками на числа и имя.
                3) Как числа взаимно усиливают или компенсируют друг друга.
                4) Практичные шаги на ближайший месяц (3-5 пунктов).
                5) Тон: поддерживающий, конкретный, без общих фраз.
                """.formatted(
                result.getOriginalName(),
                result.getTransliteratedName(),
                result.getNameNumber(),
                result.getNameMeaning(),
                result.getConsciousnessNumber(),
                result.getConsciousnessMeaning(),
                result.getMissionNumber(),
                result.getMissionMeaning()
        );
    }

    private String extractContent(String response) throws IOException {
        JsonNode root = objectMapper.readTree(response);
        JsonNode choices = root.get("choices");
        if (choices != null && choices.isArray() && !choices.isEmpty()) {
            JsonNode message = choices.get(0).get("message");
            if (message != null && message.get("content") != null) {
                return message.get("content").asText();
            }
        }
        return "OpenAI не вернул содержательный ответ.";
    }
}
