package com.gribanov.testfornetris.logic.servicesImpl;

import com.gribanov.testfornetris.logic.services.CamerasService;
import com.gribanov.testfornetris.models.Camera;
import com.gribanov.testfornetris.models.CameraUrls;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@AllArgsConstructor
@Service
public class CamerasServiceImpl implements CamerasService {

    private final RestTemplate restTemplate;
    private final ExecutorService executorService;


    public Collection<Camera> getCameras(String uri) throws InterruptedException {
        CameraUrls[] cameraUrls = restTemplate.getForObject(uri, CameraUrls[].class);
        Map<Long, Camera> map = new ConcurrentHashMap<>();
        Set<Callable<Void>> tasks = new HashSet<>();
        assert cameraUrls != null;
        for (CameraUrls cameraUrl : cameraUrls) {
            Long id = cameraUrl.getId();
            map.put(id, new Camera(id));
            tasks.add(() -> {
                Camera camera = restTemplate.getForObject(cameraUrl.getSourceDataUrl(), Camera.class);
                map.merge(id,
                        camera,
                        (v1, v2) -> {
                            v1.setUrlType(v2.getUrlType());
                            v1.setVideoUrl(v2.getVideoUrl());
                            return v1;
                        });
                return null;
            });
            tasks.add(() -> {
                Camera camera = restTemplate.getForObject(cameraUrl.getTokenDataUrl(), Camera.class);
                map.merge(id,
                        camera,
                        (v1, v2) -> {
                            v1.setValue(v2.getValue());
                            v1.setTtl(v2.getTtl());
                            return v1;
                        });
                return null;
            });
        }
        List<Future<Void>> futures = executorService.invokeAll(tasks);
        return map.values();
    }
}
