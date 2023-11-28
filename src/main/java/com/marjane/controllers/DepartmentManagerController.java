package com.marjane.controllers;

import com.marjane.dtos.DepartmentManagerDTO;
import com.marjane.dtos.PromotionDTO;
import com.marjane.services.DepartmentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department-managers")
public class DepartmentManagerController {

    private final DepartmentManagerService departmentManagerService;

    @Autowired
    public DepartmentManagerController(DepartmentManagerService departmentManagerService) {
        this.departmentManagerService = departmentManagerService;
    }

    @PostMapping
    public ResponseEntity<DepartmentManagerDTO> addDepartmentManager(@RequestBody DepartmentManagerDTO departmentManagerDTO) {
        DepartmentManagerDTO newDepartmentManager = departmentManagerService.addDepartmentManager(departmentManagerDTO);
        return new ResponseEntity<>(newDepartmentManager, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DepartmentManagerDTO> getAllDepartmentManagers() {
        return departmentManagerService.getAllDepartmentManagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentManagerDTO> getDepartmentManagerById(@PathVariable("id") Long id) {
        DepartmentManagerDTO departmentManager = departmentManagerService.getDepartmentManagerById(id);
        return departmentManager != null ? new ResponseEntity<>(departmentManager, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentManagerDTO> updateDepartmentManager(@PathVariable("id") Long id, @RequestBody DepartmentManagerDTO departmentManagerDTO) {
        DepartmentManagerDTO updatedDepartmentManager = departmentManagerService.updateDepartmentManager(id, departmentManagerDTO);
        return updatedDepartmentManager != null ? new ResponseEntity<>(updatedDepartmentManager, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/promotions/{promotionId}")
    public ResponseEntity<PromotionDTO> acceptOrRejectPromotion(
             @PathVariable("promotionId") Long promotionId,
            @RequestParam("accept") boolean accept) {
        PromotionDTO updatedPromotion = departmentManagerService.acceptOrRejectPromotion( promotionId, accept);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentManager(@PathVariable("id") Long id) {
        boolean deleted = departmentManagerService.deleteDepartmentManager(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
