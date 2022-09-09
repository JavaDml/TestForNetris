package com.gribanov.testfornetris.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CameraUrls {
    private Long id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}
