package com.gribanov.testfornetris.controllers.restControllers;

import com.gribanov.testfornetris.logic.services.CamerasService;
import com.gribanov.testfornetris.models.Camera;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/getCameras")
public class CamerasRestController {

    private final CamerasService camerasService;

    @GetMapping("/all")
    public Collection<Camera> getCameras() throws InterruptedException {
        return camerasService.getCameras("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
    }
}
