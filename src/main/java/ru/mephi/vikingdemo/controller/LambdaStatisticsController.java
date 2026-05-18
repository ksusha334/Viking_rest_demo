/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import io.swagger.v3.oas.annotations.Parameter;
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
public long countByAge( @Parameter(description = "Возраст для сравнения (например, 30)") @RequestParam int age,
        @Parameter(description = "Условие: older (больше), younger (меньше), between (от age до age+10), outside (вне диапазона)")
        @RequestParam String condition) {
    return statsService.countByAge(vikingService.findAll(), age, condition);
}

    @GetMapping("/axes/count")
    public long countByAxes(
            @Parameter(description = "Количество топоров: 1 или 2")
            @RequestParam int axesCount) {
        return statsService.countByAxesCount(vikingService.findAll(), axesCount);
    }

    @GetMapping("/beard-hair/count")
    public long countByBeardAndHair(@RequestParam BeardStyle beard, @RequestParam HairColor hair) {
        return statsService.countByBeardAndHair(vikingService.findAll(), beard, hair);
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
    public int getMaxId() {
        return statsService.getMaxId(vikingService.findAll());
    }

    @GetMapping("/even-ids")
    public int[] getEvenIds() {
        return statsService.getEvenIds(vikingService.findAll());
    }
}
