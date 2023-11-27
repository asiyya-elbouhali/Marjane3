package com.marjane.controllers;

import com.marjane.dtos.CenterDTO;
import com.marjane.services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {

    private final CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping
    public ResponseEntity<CenterDTO> addCenter(@RequestBody CenterDTO centerDTO) {
        CenterDTO addedCenter = centerService.addCenter(centerDTO);
        return new ResponseEntity<>(addedCenter, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CenterDTO>> getAllCenters() {
        List<CenterDTO> centers = centerService.getAllCenters();
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(@PathVariable Long id) {
        CenterDTO center = centerService.getCenterById(id);
        if (center != null) {
            return new ResponseEntity<>(center, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CenterDTO> updateCenter(@PathVariable Long id, @RequestBody CenterDTO centerDTO) {
        CenterDTO updatedCenter = centerService.updateCenter(id, centerDTO);
        if (updatedCenter != null) {
            return new ResponseEntity<>(updatedCenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        if (centerService.deleteCenter(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

