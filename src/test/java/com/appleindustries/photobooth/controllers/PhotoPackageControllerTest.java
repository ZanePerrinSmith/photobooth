package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.services.PhotoPackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zane
 */

class PhotoPackageControllerTest {
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
        List<PhotoPackage> photoPackages = new ArrayList<>();
        photoPackages.add(new PhotoPackage());
        photoPackages.add(new PhotoPackage());

        when(photoPackageService.listAll()).thenReturn((List) photoPackages);

        mockMvc.perform(get("/photoPackage/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showPackage() throws Exception {
    }

    @Test
    void createPhotoPackage() {
    }

    @Test
    void editPhotoPackage() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void deletePackage() {
    }
}