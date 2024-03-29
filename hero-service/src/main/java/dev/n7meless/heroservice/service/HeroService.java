package dev.n7meless.heroservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.n7meless.heroservice.dto.Benchmark;
import dev.n7meless.heroservice.dto.Hero;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@EnableCaching
@RequiredArgsConstructor
public class HeroService {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${api.opendota.url}")
    private String opendotaUrl;

    @SneakyThrows
    @Cacheable(value = "heroes")
    public List<Hero> getAllHeroes() {
        var uriString = UriComponentsBuilder.fromUriString(opendotaUrl)
                .path("heroStats")
                .toUriString();

        String response = sendRequest(uriString);
        List<Hero> heroes = objectMapper.readValue(response, new TypeReference<List<Hero>>() {
        });
        return heroes;
    }

    @SneakyThrows
    public Benchmark getBenchmarkByHeroId(Long heroId) {
        var uriString = UriComponentsBuilder.fromUriString(opendotaUrl)
                .path("benchmarks")
                .queryParam("hero_id", heroId)
                .toUriString();

        String response = sendRequest(uriString);
        JSONObject result = new JSONObject(response);
        var newJson = result.get("result").toString();
        Benchmark benchmark = objectMapper.readValue(newJson, Benchmark.class);
        return benchmark;
    }

    private String sendRequest(String url) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
