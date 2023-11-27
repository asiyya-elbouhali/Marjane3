package com.marjane.services;

import com.marjane.dtos.CenterAdminDTO;
import com.marjane.models.Center;
import com.marjane.models.CenterAdmin;
import com.marjane.repositories.CenterAdminRepository;
import com.marjane.repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CenterAdminService {

    private final CenterAdminRepository centerAdminRepository;
    private final CenterRepository centerRepository;

    @Autowired
    public CenterAdminService(CenterAdminRepository centerAdminRepository,
                              CenterRepository centerRepository) {
        this.centerAdminRepository = centerAdminRepository;
        this.centerRepository = centerRepository;
    }

    public CenterAdminDTO addCenterAdmin(CenterAdminDTO centerAdminDTO) {
        CenterAdmin centerAdmin = mapCenterAdminDTOToEntity(centerAdminDTO);

        if (centerAdminDTO.getCenterId() != null) {
            Optional<Center> centerOptional = centerRepository.findById(centerAdminDTO.getCenterId());
            centerOptional.ifPresent(center -> {
                centerAdmin.setCenter(center);
                center.getCenterAdmins().add(centerAdmin);
            });
        }

        CenterAdmin savedCenterAdmin = centerAdminRepository.save(centerAdmin);
        return mapCenterAdminEntityToDTO(savedCenterAdmin);
    }

    private CenterAdmin mapCenterAdminDTOToEntity(CenterAdminDTO centerAdminDTO) {
        CenterAdmin centerAdmin = new CenterAdmin();
        centerAdmin.setId(centerAdminDTO.getId());
        centerAdmin.setName(centerAdminDTO.getName());
        centerAdmin.setEmail(centerAdminDTO.getEmail());
        centerAdmin.setPassword(centerAdminDTO.getPassword());

        return centerAdmin;
    }

    private CenterAdminDTO mapCenterAdminEntityToDTO(CenterAdmin centerAdmin) {
        CenterAdminDTO centerAdminDTO = new CenterAdminDTO();
        centerAdminDTO.setId(centerAdmin.getId());
        centerAdminDTO.setName(centerAdmin.getName());
        centerAdminDTO.setEmail(centerAdmin.getEmail());
        centerAdminDTO.setPassword(centerAdmin.getPassword());

        return centerAdminDTO;
    }
}
