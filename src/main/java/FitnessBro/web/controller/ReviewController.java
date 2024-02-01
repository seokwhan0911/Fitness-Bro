package FitnessBro.web.controller;

import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.service.ReviewService.ReviewService;
import FitnessBro.web.dto.CoachResponseDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping
    public ResponseEntity<String> writeReview(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + "님의 리뷰 등록이 완료 되었습니다.");
    }
}