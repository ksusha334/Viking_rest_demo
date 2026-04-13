package ru.mephi.vikingdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.List;

@RestController
@RequestMapping("/api/vikings")
public class VikingController {

    private final VikingService vikingService;

    public VikingController(VikingService vikingService) {
        this.vikingService = vikingService;
    }

    @GetMapping
    public List<Viking> getAllVikings() {
        return vikingService.findAll();
    }

    @PostMapping
    public ResponseEntity<Viking> addViking(@RequestBody Viking viking) {
        Viking saved = vikingService.save(viking);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteViking(@PathVariable int index) {
        vikingService.deleteById(index);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{index}")
    public ResponseEntity<Viking> updateViking(@PathVariable int index, @RequestBody Viking viking) {
        Viking updated = vikingService.update(index, viking);
        return ResponseEntity.ok(updated);
    }
}