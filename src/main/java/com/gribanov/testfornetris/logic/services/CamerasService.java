package com.gribanov.testfornetris.logic.services;

import com.gribanov.testfornetris.models.Camera;

import java.net.URI;
import java.util.Collection;

public interface CamerasService {
    Collection<Camera> getCameras(String uri) throws InterruptedException;
}
