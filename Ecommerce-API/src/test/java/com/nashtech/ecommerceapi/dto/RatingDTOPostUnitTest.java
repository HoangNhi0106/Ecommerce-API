package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Account;
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
public class RatingDTOPostUnitTest {
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

        RatingDTOPost ratingDTOPost = modelMapper.map(rating, RatingDTOPost.class);

        assertEquals(rating.getRatingId(), ratingDTOPost.getRatingId());
        assertEquals(rating.getProduct().getProductId(), ratingDTOPost.getProductId());
        assertEquals(rating.getAccount().getAccountId(), ratingDTOPost.getAccountId());
        assertEquals(rating.getStar(), ratingDTOPost.getStar());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        RatingDTOPost ratingDTOPost = new RatingDTOPost();
        ratingDTOPost.setAccountId(1);
        ratingDTOPost.setProductId(1);
        ratingDTOPost.setStar(4);

        Rating rating = modelMapper.map(ratingDTOPost, Rating.class);
        assertEquals(rating.getRatingId(), ratingDTOPost.getRatingId());
        assertEquals(rating.getProduct().getProductId(), ratingDTOPost.getProductId());
        assertEquals(rating.getAccount().getAccountId(), ratingDTOPost.getAccountId());
        assertEquals(rating.getStar(), ratingDTOPost.getStar());
    }
}
