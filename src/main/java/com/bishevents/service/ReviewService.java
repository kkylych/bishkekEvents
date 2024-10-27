package com.bishevents.service;
import com.bishevents.DTO.ReviewDTO;
import com.bishevents.Mapper.ReviewMapper;
import com.bishevents.entity.Event;
import com.bishevents.entity.Review;
import com.bishevents.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(ReviewMapper::toDto);
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Event event = new Event(); // Здесь необходимо передать правильное событие
        Review review = ReviewMapper.toEntity(reviewDTO, event);
        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.toDto(savedReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
