# Dota 2 Microservices with Spring Cloud
## Table of Contents
* [About](#about)
* [Technology](#technology)
* [Quickstart](#quickstart)
* [Architecture](#architecture)
## About
  Проект создан на базе микросервисной архитектуры, где каждый микросервис - это RESTful сервис,
  с помощью которого можно получить статистику героя. Например, за любой период можно узнать у героя: 
- Процент побед 
- Влияние на игру 
- Экономику
- Аттрибуты и характеристику
- Добычу золота
- Сколько игр сыгранно и какой процент побед против любого героя
## Technology
В данном проекте используется:
* Java 17
* Apache Maven
* JUnit 5
* Spring Framework(Boot, Web, Cloud, Webflux)
* Springdoc
* Jsoup
* Json
* Lombok
* Docker

## Quickstart
**Для запуска проекта потребуется Maven, JDK17+, любая среда разработки и установленный Docker.**
1.  Собираем проект и создаем docker images:

        $ mvn clean package -Pbuild-image jib:dockerBuild

2.  Запускаем все контейнеры с помощью docker-compose:

        $ docker-compose up
Для того чтобы увидеть запущенные сервисы переходим по **localhost:8888/eureka/web**

![Eureka](https://user-images.githubusercontent.com/52253002/235492772-f5c16927-7672-4f89-8699-49e891ed648f.png)

Для того чтобы посмотреть конечные точки какого-либо микросервиса переходим по **localhost:8888/swagger-ui.html**

![SwaggerUI](https://user-images.githubusercontent.com/52253002/235499686-6003aaa4-1c58-49ed-8577-89349618aeff.png)
## Architecture
Проект, основанный на микросервисной архитектуре, состоит из следующих модулей:
* **discovery-service** - модуль, который использует Spring Cloud Netflix Eureka в качестве встроенного сервера обнаружения.
* **config-service** - модуль, который использует Spring Cloud Config Server для запуска сервера конфигурации. 
* **gateway-service** - модуль Spring Cloud Netflix Eureka для запуска приложения Spring Boot, который действует как прокси/шлюз в архитектуре.
* **hero-service** - модуль, с помощью которого можно получить у героя аттрибуты, характеристику, изображение, роли и многое другое. Он взаимодействует с другим внешним API сервисом Dota 2 и выдает ответ в *json* формате.
* **counter-service** - модуль, с помощью которого можно получить процент побед и сколько игр сыгранно против любого героя. Он собирает информацию с других источников Dota 2 и выдает ответ в *json* формате.
* **economy-service** - модуль, с помощью которого можно получить экономику(добычу золота и опыт в минуту) героя. Он собирает информацию с других источников Dota 2 и выдает ответ в *json* формате.
* **farm-service** - модуль, с помощью которого можно получить фарм(количество убитых вражеских и своих крипов) героя. Он собирает информацию с других источников Dota 2 и выдает ответ в *json* формате.
* **impact-service** - модуль, с помощью которого можно получить влияние(убийства, помощи, смерти, средняя продолжительность матча) на игру героя. Он собирает информацию с других источников Dota 2 и выдает ответ в *json* формате.
* **lane-service** - модуль, с помощью которого можно получить статистику героя на любой линии(убийства, процент побед, золото и опыт в минуту). Он собирает информацию с других источников Dota 2 и выдает ответ в *json* формате.

Следующий рисунок иллюстрирует описанную выше архитектуру.
      
![Архитектура](https://user-images.githubusercontent.com/52253002/235482353-2d9d01c3-16f6-46a4-b719-ffddd60dfb61.png)

