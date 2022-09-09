package com.gribanov.testfornetris.controllers;

import com.gribanov.testfornetris.controllers.restControllers.CamerasRestController;
import com.gribanov.testfornetris.logic.services.CamerasService;
import com.gribanov.testfornetris.models.Camera;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CamerasRestController.class)
public class CamerasRestControllerTest {

    @MockBean
    private CamerasService camerasService;
    private MockMvc mockMvc;

    CamerasRestControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getCameras() throws Exception {

        when(camerasService.getCameras("http://www.mocky.io/v2/5c51b9dd3400003252129fb5")).thenReturn(List.of(
            new Camera(1L, "LIVE", "rtsp://127.0.0.1/1", "fa4b588e-249b-11e9-ab14-d663bd873d93", 120),
            new Camera(2L, "ARCHIVE", "rtsp://127.0.0.1/2", "fa4b5f64-249b-11e9-ab14-d663bd873d93", 60),
            new Camera(3L, "LIVE", "rtsp://127.0.0.1/3", "fa4b5b22-249b-11e9-ab14-d663bd873d93", 180)
        ));

        mockMvc.perform(get("/getCameras/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[*].id",containsInAnyOrder(1,2,3)));
    }

}
