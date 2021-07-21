package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class RatingDTO {
    private long ratingId;

    @NotBlank
    private long productId;

    @NotBlank
    private long accountId;

    private Date date;

    @Min(value = 0, message = "The star must be a positive ingeter")
    @Max(value = 5, message = "Maximum star is 5")
    private int star;

    private String comment;
}
