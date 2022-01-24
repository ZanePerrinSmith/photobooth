package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.AbstractRestControllerTest;
import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.services.PhotoPackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zane
 */

class PhotoPackageControllerTest extends AbstractRestControllerTest {
    @Mock
    private PhotoPackageService photoPackageService;
    @InjectMocks
    private PhotoPackageController photoPackageController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(photoPackageController).build();
    }

    @Test
    void listPackages() throws Exception {
        List<PhotoPackage> expectedPhotoPackages = new ArrayList<>();
        expectedPhotoPackages.add(new PhotoPackage());
        expectedPhotoPackages.add(new PhotoPackage());

        when(photoPackageService.listAll()).thenReturn((List) expectedPhotoPackages);

        MvcResult mvcResult = mockMvc.perform(get("/photoPackage/list"))
                .andExpect(status().isOk())
                .andReturn();
        PhotoPackage[] photoPackagesResult = mvcResultAsObject(mvcResult, PhotoPackage[].class);

        assertEquals(expectedPhotoPackages.size(), photoPackagesResult.length);
    }

    @Test
    void showPackage() throws Exception {
        Integer id = 1;
        when(photoPackageService.getById(id)).thenReturn(new PhotoPackage());

        MvcResult mvcResult = mockMvc.perform(get("/photoPackage/show/1"))
                .andExpect(status().isOk())
                .andReturn();
        PhotoPackage photoPackageResult = mvcResultAsObject(mvcResult, PhotoPackage.class);

        assertNotNull(photoPackageResult);
    }

    @Test
    void createPhotoPackage() throws Exception {
        when(photoPackageService.saveOrUpdate(any(PhotoPackage.class))).thenReturn(new PhotoPackage());

        MvcResult mvcResult = mockMvc.perform(post("/photoPackage/create"))
                .andExpect(status().isOk())
                .andReturn();
        PhotoPackage photoPackageResult = mvcResultAsObject(mvcResult, PhotoPackage.class);

        assertNotNull(photoPackageResult);
    }

    @Test
    void editPhotoPackage() throws Exception {
        Integer id = 1;
        when(photoPackageService.getById(id)).thenReturn(new PhotoPackage());
        when(photoPackageService.merge(any(PhotoPackage.class), any(PhotoPackage.class))).thenReturn(new PhotoPackage());
        when(photoPackageService.saveOrUpdate(any(PhotoPackage.class))).thenReturn(new PhotoPackage());

        MvcResult mvcResult = mockMvc.perform(put("/photoPackage/edit/1"))
                .andExpect(status().isOk())
                .andReturn();
        PhotoPackage photoPackageResult = mvcResultAsObject(mvcResult, PhotoPackage.class);

        assertNotNull(photoPackageResult);
    }

    @Test
    void saveOrUpdate() throws Exception {
        Integer id = 1;
        PhotoPackage expectedPhotoPackage = new PhotoPackage();
        PhotoPackageEnum type = PhotoPackageEnum.PRINT;
        BigDecimal price = new BigDecimal(5);

        expectedPhotoPackage.setId(1);
        expectedPhotoPackage.setType(type);
        expectedPhotoPackage.setPrice(price);


        when(photoPackageService.saveOrUpdate(any(PhotoPackage.class))).thenReturn(expectedPhotoPackage);

        MvcResult mvcResult = mockMvc.perform(post("/photoPackage/saveOrUpdate")
                        .param("id", String.valueOf(id))
                        .param("type", String.valueOf(type))
                        .param("price", String.valueOf(price)))
                .andExpect(status().isOk())
                .andReturn();

        PhotoPackage photoPackageResult = mvcResultAsObject(mvcResult, PhotoPackage.class);
        assertNotNull(photoPackageResult);

        ArgumentCaptor<PhotoPackage> photoPackageArgumentCaptor = ArgumentCaptor.forClass(PhotoPackage.class);
        verify(photoPackageService).saveOrUpdate(photoPackageArgumentCaptor.capture());

        PhotoPackage boundPhotoPackage = photoPackageArgumentCaptor.getValue();

        assertEquals(id, boundPhotoPackage.getId());
        assertEquals(type, boundPhotoPackage.getType());
        assertEquals(price, boundPhotoPackage.getPrice());
    }

    @Test
    void deletePackage() throws Exception {
        Integer id = 1;
        when(photoPackageService.getById(id)).thenReturn(new PhotoPackage());

        MvcResult mvcResult = mockMvc.perform(delete("/photoPackage/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals("Deleted " + new PhotoPackage(), content);
    }
}