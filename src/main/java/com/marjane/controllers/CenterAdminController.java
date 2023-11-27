package com.marjane.controllers;

import com.marjane.dtos.CenterAdminDTO;
import com.marjane.services.CenterAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/center-admins")
public class CenterAdminController {

    private final CenterAdminService centerAdminService;

    @Autowired
    public CenterAdminController(CenterAdminService centerAdminService) {
        this.centerAdminService = centerAdminService;
    }

    @PostMapping
    public ResponseEntity<CenterAdminDTO> addCenterAdmin(@RequestBody CenterAdminDTO centerAdminDTO) {
        CenterAdminDTO addedCenterAdmin = centerAdminService.addCenterAdmin(centerAdminDTO);
        return new ResponseEntity<>(addedCenterAdmin, HttpStatus.CREATED);
    }

 }
