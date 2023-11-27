package com.marjane.services;

import com.marjane.dtos.CenterDTO;
import com.marjane.models.Center;
import com.marjane.repositories.CenterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CenterService {

    private final CenterRepository centerRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public CenterDTO addCenter(CenterDTO centerDTO) {
        // Convert DTO to Entity
        Center center = convertToEntity(centerDTO);

        // Save the Entity
        Center savedCenter = centerRepository.save(center);

        // Convert Entity to DTO
        return convertToDTO(savedCenter);
    }

    public List<CenterDTO> getAllCenters() {
        List<Center> centers = centerRepository.findAll();
        return centers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CenterDTO getCenterById(Long id) {
        Center center = centerRepository.findById(id).orElse(null);
        return center != null ? convertToDTO(center) : null;
    }

    public CenterDTO updateCenter(Long id, CenterDTO centerDTO) {
        Center existingCenter = centerRepository.findById(id).orElse(null);
        if (existingCenter != null) {
            // Update fields
            BeanUtils.copyProperties(centerDTO, existingCenter, "id");
            return convertToDTO(centerRepository.save(existingCenter));
        }
        return null;
    }

    public boolean deleteCenter(Long id) {
        if (centerRepository.existsById(id)) {
            centerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Conversion methods
    private CenterDTO convertToDTO(Center center) {
        CenterDTO centerDTO = new CenterDTO();
        BeanUtils.copyProperties(center, centerDTO);
        return centerDTO;
    }

    private Center convertToEntity(CenterDTO centerDTO) {
        Center center = new Center();
        BeanUtils.copyProperties(centerDTO, center);
        return center;
    }
}

