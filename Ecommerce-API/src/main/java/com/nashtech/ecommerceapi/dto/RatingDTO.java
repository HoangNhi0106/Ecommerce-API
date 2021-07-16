package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RatingDTO {
    private long ratingId;
    private long productId;
    private long accountId;
    private Date date;
    private float star;
}
