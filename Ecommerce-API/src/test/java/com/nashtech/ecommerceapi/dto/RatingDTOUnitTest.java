package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingDTOUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertProductEntityToPostDto_thenCorrect() {
        Product product = new Product();
        product.setProductId(1l);

        Account account = new Account();
        account.setAccountId(1l);

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setAccount(account);
        rating.setDate(LocalDateTime.now());
        rating.setStar(4);

        RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);

        assertEquals(rating.getRatingId(), ratingDTO.getRatingId());
        assertEquals(rating.getProduct().getProductId(), ratingDTO.getProductId());
        assertEquals(rating.getAccount().getAccountId(), ratingDTO.getAccountId());
        assertEquals(rating.getStar(), ratingDTO.getStar());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setAccountId(1);
        ratingDTO.setProductId(1);
        ratingDTO.setStar(4);

        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        assertEquals(rating.getRatingId(), ratingDTO.getRatingId());
        assertEquals(rating.getProduct().getProductId(), ratingDTO.getProductId());
        assertEquals(rating.getAccount().getAccountId(), ratingDTO.getAccountId());
        assertEquals(rating.getStar(), ratingDTO.getStar());
    }
}
