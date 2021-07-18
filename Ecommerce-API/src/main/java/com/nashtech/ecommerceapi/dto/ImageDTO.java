package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private String imageId;
    private String url;
    private String contentType;
    private Long size;
}
