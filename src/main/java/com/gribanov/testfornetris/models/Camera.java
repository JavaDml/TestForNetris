package com.gribanov.testfornetris.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
    private Long id;
    private String urlType;
    private String videoUrl;
    private String value;
    private Integer ttl;

    public Camera(Long id) {
        this.id = id;
    }
}
