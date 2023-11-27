package com.marjane.controllers;

import com.marjane.dtos.GeneralAdminDTO;
import com.marjane.services.GeneralAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class GeneralAdminController {

    private final GeneralAdminService adminService;

    @Autowired
    public GeneralAdminController(GeneralAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<GeneralAdminDTO>> getAllAdmins() {
        List<GeneralAdminDTO> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralAdminDTO> getAdminById(@PathVariable Long id) {
        GeneralAdminDTO admin = adminService.getAdminById(id);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<GeneralAdminDTO> createAdmin(@RequestBody GeneralAdminDTO adminDTO) {
        GeneralAdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralAdminDTO> updateAdmin(@PathVariable Long id, @RequestBody GeneralAdminDTO adminDTO) {
        GeneralAdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (adminService.deleteAdmin(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
