package com.marjane;

import com.marjane.controllers.GeneralAdminController;
import com.marjane.dtos.GeneralAdminDTO;
import com.marjane.services.GeneralAdminService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GeneralAdminControllerTest {

    @Mock
    private GeneralAdminService adminService;

    @InjectMocks
    private GeneralAdminController adminController;

    @Test
    public void testCreateAdmin() {
         GeneralAdminDTO adminDTO = new GeneralAdminDTO();
        adminDTO.setId(1L);
        adminDTO.setName("test ");
        adminDTO.setEmail("test@gmail.com");
        adminDTO.setPassword("password123");

        when(adminService.createAdmin(adminDTO)).thenReturn(adminDTO);

         ResponseEntity<GeneralAdminDTO> response = adminController.createAdmin(adminDTO);

         assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(adminDTO, response.getBody());

        verify(adminService, times(1)).createAdmin(adminDTO);
    }
}
