package com.bishevents.Mapper;

import com.bishevents.DTO.ReviewDTO;
import com.bishevents.entity.Event;
import com.bishevents.entity.Review;

public class ReviewMapper {
    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(review.getId(), review.getEvent().getId(), review.getParticipantsCount());
    }

    public static Review toEntity(ReviewDTO reviewDTO, Event event) {
        Review review = new Review();
        review.setId(reviewDTO.id());
        review.setEvent(event);
        review.setParticipantsCount(reviewDTO.participantsCount());
        return review;
    }
}
