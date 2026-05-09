/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import org.springframework.web.bind.annotation.*;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.LambdaStatisticsService;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.List;
/**
 *
 * @author march
 */
@RestController
@RequestMapping("/api/statistics")
public class LambdaStatisticsController {

    private final LambdaStatisticsService statsService;
    private final VikingService vikingService;

    public LambdaStatisticsController(LambdaStatisticsService statsService, VikingService vikingService) {
        this.statsService = statsService;
        this.vikingService = vikingService;
    }

    @GetMapping("/age/count")
    public long countByAge(@RequestParam int age, @RequestParam String condition) {
        return statsService.countByAge(vikingService.findAll(), age, condition);
    }

    @GetMapping("/beard-hair/count")
    public long countByBeardAndHair(@RequestParam BeardStyle beard, @RequestParam HairColor hair) {
        return statsService.countByBeardAndHair(vikingService.findAll(), beard, hair);
    }

    @GetMapping("/axes/count")
    public long countByAxes(@RequestParam int axesCount) {
        return statsService.countByAxesCount(vikingService.findAll(), axesCount);
    }

    @GetMapping("/random-above-height")
    public Viking getRandomAboveHeight(@RequestParam int minHeight) {
        return statsService.getRandomVikingAboveHeight(vikingService.findAll(), minHeight);
    }

    @GetMapping("/legendary-gear")
    public List<Viking> getWithLegendaryGear() {
        return statsService.getVikingsWithLegendaryGear(vikingService.findAll());
    }

    @GetMapping("/red-bearded-sorted")
    public List<Viking> getRedBeardedSortedByAge() {
        return statsService.getRedBeardedSortedByAge(vikingService.findAll());
    }

    @GetMapping("/max-id")
    public Long getMaxId() {
        return statsService.getMaxId(vikingService.findAll());
    }

    @GetMapping("/even-ids")
    public List<Long> getEvenIds() {
        return statsService.getEvenIds(vikingService.findAll());
    }
}
