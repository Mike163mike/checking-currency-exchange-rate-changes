# exchange-checker
***
This service is test task from Alfa-Bank.
Service for monitoring the currency rate with Feign-client technology. It starts by address: localhost on port 8080. 
It has two controllers with own endpoints. <br/>
__DevGifController__ - controller for developers. It returns 
ResponseEntity (answer has json form). There is Swagger UI configured for it. <br/>
__UserGifController__ - controller for users. It returns two view-page. First - show user all possible currencies he can 
operate with. Second (where user go by link located below first-page) - gives opportunity insert in the address bar
currency type (instead of default "USD") for knowing actual rate trend. After that user can see appropriate GIF image service retrieve 
in dependence of currency trend.  If currency type user insert was increased the GIF characteristic will be cheerful. 
If it was decrease - you'll get melancholy one.
All data service retrieve it gets from real resources by Feign-client technology. Service has necessary tests.

Full text of test task:

        Cоздать сервис, который обращается к сервису курсов валют, и отображает gif:
    • если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
    • если ниже - отсюда https://giphy.com/search/broke
    Ссылки:
    • REST API курсов валют - https://docs.openexchangerates.org/
    • REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
     Must Have
    • Сервис на Spring Boot 2 + Java / Kotlin
    • Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты 
        по отношению с которой сравнивается USD
    • Для взаимодействия с внешними сервисами используется Feign
    • Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
    • На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
    • Для сборки должен использоваться Gradle
    • Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
    Nice to Have
    • Сборка и запуск Docker контейнера с этим сервисом







