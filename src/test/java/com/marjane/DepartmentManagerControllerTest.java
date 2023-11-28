package com.marjane;

import com.marjane.controllers.DepartmentManagerController;
import com.marjane.dtos.PromotionDTO;
import com.marjane.enumeration.PromotionStatus;
import com.marjane.services.DepartmentManagerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class DepartmentManagerControllerTest {

 @Mock
 private DepartmentManagerService departmentManagerService;

 @InjectMocks
 private DepartmentManagerController departmentManagerController;

 @Test
 public void testAcceptOrRejectPromotion() {
   PromotionDTO promotionDTO = new PromotionDTO();
  promotionDTO.setId(3L);
  promotionDTO.setStatus(PromotionStatus.ACCEPTED);

   Mockito.when(departmentManagerService.acceptOrRejectPromotion(anyLong(), anyBoolean()))
          .thenReturn(promotionDTO);

   ResponseEntity<PromotionDTO> responseEntity = departmentManagerController.acceptOrRejectPromotion(3L, true);

   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  assertEquals(promotionDTO, responseEntity.getBody());
 }
}
