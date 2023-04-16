package dev.n7meless.imactservice.controller;

import dev.n7meless.imactservice.dto.Impact;
import dev.n7meless.imactservice.service.ImpactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/impact")
public class ImpactController {
    private final ImpactService farmService;

    public ImpactController(ImpactService farmService) {
        this.farmService = farmService;
    }


    @GetMapping
    public List<Impact> getAllFarm(@RequestParam String date) {
        return farmService.getAllImpact(date);
    }
}
