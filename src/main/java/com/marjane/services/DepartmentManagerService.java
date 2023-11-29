package com.marjane.services;

import com.marjane.dtos.DepartmentManagerDTO;
import com.marjane.dtos.PromotionDTO;
import com.marjane.enumeration.PromotionStatus;
import com.marjane.models.CenterAdmin;
import com.marjane.models.DepartmentManager;
import com.marjane.models.Promotion;
import com.marjane.observer.PromotionObservable;
import com.marjane.observer.PromotionObserver;
import com.marjane.repositories.CenterAdminRepository;
import com.marjane.repositories.DepartmentManagerRepository;
import com.marjane.repositories.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentManagerService implements PromotionObservable {

    private final DepartmentManagerRepository departmentManagerRepository;
    private final PromotionRepository promotionRepository;
    private List<CenterAdmin> observers = new ArrayList<>();

    private final CenterAdminService centerAdminService;

    private final CenterAdminRepository centerAdminRepository ;
    private final ModelMapper modelMapper;
     @Autowired
    public DepartmentManagerService(
             DepartmentManagerRepository departmentManagerRepository,
             PromotionRepository promotionRepository,
             CenterAdminService centerAdminService, CenterAdminRepository centerAdminRepository, ModelMapper modelMapper
    ) {
        this.departmentManagerRepository = departmentManagerRepository;
        this.promotionRepository = promotionRepository;
         this.centerAdminService = centerAdminService;
         this.centerAdminRepository = centerAdminRepository;

         this.modelMapper = modelMapper;
    }


    @Override
    public void notifyObservers(PromotionDTO promotionDTO) {
         observers = centerAdminRepository.findAll();
        for (CenterAdmin observer : observers) {
            System.out.println("Notification sent to Center Admin: Promotion status changed to " + promotionDTO.getStatus());
            //observeCe.onPromotionStatusChange(promotionDTO);
            centerAdminService.onPromotionStatusChange(promotionDTO);
        }
    }
    public DepartmentManagerDTO addDepartmentManager(DepartmentManagerDTO departmentManagerDTO) {
        DepartmentManager departmentManager = modelMapper.map(departmentManagerDTO, DepartmentManager.class);
        DepartmentManager savedDepartmentManager = departmentManagerRepository.save(departmentManager);
        return modelMapper.map(savedDepartmentManager, DepartmentManagerDTO.class);
    }

    public List<DepartmentManagerDTO> getAllDepartmentManagers() {
        List<DepartmentManager> departmentManagers = departmentManagerRepository.findAll();
        return departmentManagers.stream()
                .map(departmentManager -> modelMapper.map(departmentManager, DepartmentManagerDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentManagerDTO getDepartmentManagerById(Long id) {
        Optional<DepartmentManager> optionalDepartmentManager = departmentManagerRepository.findById(id);
        return optionalDepartmentManager.map(departmentManager -> modelMapper.map(departmentManager, DepartmentManagerDTO.class)).orElse(null);
    }

    public DepartmentManagerDTO updateDepartmentManager(Long id, DepartmentManagerDTO departmentManagerDTO) {
        Optional<DepartmentManager> optionalDepartmentManager = departmentManagerRepository.findById(id);
        if (optionalDepartmentManager.isPresent()) {
            DepartmentManager existingManager = optionalDepartmentManager.get();
            existingManager.setName(departmentManagerDTO.getName());
             DepartmentManager updatedManager = departmentManagerRepository.save(existingManager);
            return modelMapper.map(updatedManager, DepartmentManagerDTO.class);
        }
        return null;
    }

    public boolean deleteDepartmentManager(Long id) {
        if (departmentManagerRepository.existsById(id)) {
            departmentManagerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PromotionDTO acceptOrRejectPromotion(Long promotionId, boolean accept) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));

        if (accept) {
            promotion.setStatus(PromotionStatus.ACCEPTED);
        } else {
            promotion.setStatus(PromotionStatus.REJECTED);
        }

        Promotion savedPromotion = promotionRepository.save(promotion);

        PromotionDTO updatedPromotionDTO = modelMapper.map(savedPromotion, PromotionDTO.class);

         notifyObservers(updatedPromotionDTO);

        return updatedPromotionDTO;
    }





}
