# Numerology (Spring Boot)

Сервис рассчитывает базовые нумерологические показатели по имени и дате рождения, сохраняет результат в памяти и дополняет его аналитикой от OpenAI (GPT-4o). Доступны REST API и веб-форма на Thymeleaf.

## Основные возможности
- Подсчет числа имени, осознанности и миссии с расшифровкой.
- Транслитерация имени с кириллицы на латиницу для корректного расчета.
- Генерация текстового анализа через OpenAI Chat Completions.
- REST endpoint `/api/numerology` для интеграций.
- Веб-страница `/` для ручного ввода и просмотра результата.

## Требования
- JDK 17+
- Доступ в интернет для обращения к OpenAI API

## Настройка OpenAI
Укажите ключ и модель в `src/main/resources/application.properties` или через переменные окружения:
```
spring.ai.openai.api-key=<your-openai-key>
spring.ai.openai.model=gpt-4o
```
Рекомендуется не хранить ключ в репозитории и задавать его через переменные окружения (`SPRING_AI_OPENAI_API-KEY`).

## Запуск приложения
```bash
# из корня проекта
./gradlew bootRun      # Linux/macOS
gradlew.bat bootRun    # Windows
```
Приложение поднимется на `http://localhost:8080`.

## REST API
- **POST** `/api/numerology`
  - Тело запроса (JSON):
    ```json
    {
      "name": "Айжан",
      "day": 12,
      "month": 7,
      "year": 1995
    }
    ```
  - Ответ: объект с рассчитанными числами, их описанием и полем `aiAnalysis` с выводом GPT.

## Веб-интерфейс
- Страница `/` (Thymeleaf): форма для имени и даты рождения, под ней отображается расчет и текстовый разбор GPT-4o.

## Структура
- `src/main/java/kz/narxoz/numerology/service/NumerologyService` — расчет чисел и смыслы.
- `src/main/java/kz/narxoz/numerology/service/OpenAiAnalysisService` — вызов OpenAI и формирование промпта.
- `src/main/java/kz/narxoz/numerology/controller/NumerologyController` — REST API.
- `src/main/java/kz/narxoz/numerology/controller/NumerologyViewController` — контроллер веб-страницы.
- `src/main/resources/templates/index.html` — шаблон формы и результата.
- `src/main/java/kz/narxoz/numerology/repository/InMemoryNumerologyResultRepository` — хранение результатов в памяти.

## Тестирование
```bash
./gradlew test
```

## Примечания
- Данные хранятся только в памяти процесса (нет БД).
- Для корректной работы AI-аналитики нужен валидный ключ OpenAI и интернет-соединение.***
