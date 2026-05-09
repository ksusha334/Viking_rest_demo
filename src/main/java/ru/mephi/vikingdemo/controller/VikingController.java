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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViking(@PathVariable Long id) {
        vikingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viking> updateViking(@PathVariable Long id, @RequestBody Viking viking) {
        viking.setId(id);
        Viking updated = vikingService.update(viking);
        return ResponseEntity.ok(updated);
    }
}