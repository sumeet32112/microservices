package com.lcwd.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private Long rating;
    private String feedback;
    private Hotel hotel;

}
