package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
        String url = "http://RATINGSERVICE/ratings/users/" + userId;
        Rating[] ratings = restTemplate.getForObject(url,Rating[].class);
        logger.info("{} ",ratings);

        List<Rating> newRating = Arrays.stream(ratings).toList();

         newRating.forEach(rating ->{
            String hotelUrl = "http://HOTELSERVICE/hotels/"+ rating.getHotelId();
            ResponseEntity<Hotel>  hotel= restTemplate.getForEntity(hotelUrl,Hotel.class);
            rating.setHotel(hotel.getBody());
        });

        user.setRatings(newRating);
        return user;
    }
}
