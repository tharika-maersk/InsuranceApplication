package com.example.insurance_application.dto;

public record InsuranceReviewDto(
        String status,
        String approvedBy,
        Double amountApproved) {
}
