package com.marjane.services;

import com.marjane.dtos.GeneralAdminDTO;
import com.marjane.models.GeneralAdmin;
import com.marjane.repositories.GeneralAdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneralAdminService {

    private final GeneralAdminRepository adminRepository;

    @Autowired
    public GeneralAdminService(GeneralAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<GeneralAdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GeneralAdminDTO getAdminById(Long id) {
        Optional<GeneralAdmin> admin = adminRepository.findById(id);
        return admin.map(this::convertToDTO).orElse(null);
    }

    public GeneralAdminDTO createAdmin(GeneralAdminDTO adminDTO) {
        if (adminDTO == null || adminDTO.getName() == null || adminDTO.getEmail() == null || adminDTO.getPassword() == null) {
            throw new IllegalArgumentException("Invalid GeneralAdminDTO provided");
        }

        GeneralAdmin admin = convertToEntity(adminDTO);
        GeneralAdmin savedAdmin = adminRepository.save(admin);

        return convertToDTO(savedAdmin);
    }



    public GeneralAdminDTO updateAdmin(Long id, GeneralAdminDTO adminDTO) {
        Optional<GeneralAdmin> adminFromDB = adminRepository.findById(id);
        if (adminFromDB.isPresent()) {
            GeneralAdmin admin = adminFromDB.get();
            BeanUtils.copyProperties(adminDTO, admin);
            return convertToDTO(adminRepository.save(admin));
        }
        return null;
    }

    public boolean deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private GeneralAdminDTO convertToDTO(GeneralAdmin admin) {
        GeneralAdminDTO adminDTO = new GeneralAdminDTO(admin.getId(), admin.getName(), admin.getEmail(), admin.getPassword());
        adminDTO.setId(admin.getId());
        return adminDTO;
    }

    public GeneralAdmin convertToEntity(GeneralAdminDTO adminDTO) {
        if (adminDTO == null || adminDTO.getName() == null || adminDTO.getEmail() == null || adminDTO.getPassword() == null) {
            throw new IllegalArgumentException("Invalid GeneralAdminDTO provided");
        }

        GeneralAdmin admin = new GeneralAdmin();
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());


        return admin;
    }

}
