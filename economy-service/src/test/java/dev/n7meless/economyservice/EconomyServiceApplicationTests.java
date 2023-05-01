package dev.n7meless.economyservice;

import dev.n7meless.economyservice.dto.Economy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = EconomyServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.discovery.enabled=false",
        "parse.dotabuff.url=https://www.dotabuff.com/heroes/"})
public class EconomyServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenGetEconomyWithHeroName() {
        ResponseEntity<Economy> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/economy/hero?name=axe", Economy.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturn200WhenGetAllEconomies() {
        ResponseEntity<List> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/economy", List.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
