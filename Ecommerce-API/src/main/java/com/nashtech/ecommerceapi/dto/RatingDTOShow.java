package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class RatingDTOShow {
    private long ratingId;

    private long productId;

    private String pname;

    private String username;

    private Date date;

    private int star;

    private String comment;
}
