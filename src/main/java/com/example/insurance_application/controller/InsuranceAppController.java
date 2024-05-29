package com.example.insurance_application.controller;

import com.example.insurance_application.dto.InsuranceApproveDto;
import com.example.insurance_application.dto.InsuranceRequestDto;
import com.example.insurance_application.dto.InsuranceReviewDto;
import com.example.insurance_application.service.InsuranceApproveService;
import com.example.insurance_application.service.InsuranceRequestService;
import com.example.insurance_application.service.InsuranceReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsuranceAppController {
    private InsuranceRequestService insuranceRequestService;
    private InsuranceReviewService insuranceReviewService;
    private InsuranceApproveService insuranceApproveService;

    private static final Logger log = LoggerFactory.getLogger(InsuranceAppController.class);

    public InsuranceAppController(InsuranceRequestService insuranceRequestService, InsuranceReviewService insuranceReviewService, InsuranceApproveService insuranceApproveService) {
        this.insuranceRequestService = insuranceRequestService;
        this.insuranceReviewService = insuranceReviewService;
        this.insuranceApproveService = insuranceApproveService;
    }


    @PostMapping("/createRequest")
    public InsuranceRequestDto createInsuranceRequest(
            @RequestBody InsuranceRequestDto insuranceRequestDto
            ){
        log.info("Creating a request for an insurance claim");
        return this.insuranceRequestService.createRequest(insuranceRequestDto);
    }

    @GetMapping("/insurance/{insurance-id}")
    public InsuranceRequestDto getInsuranceById(
            @PathVariable("insurance-id") Integer id
    ){
        log.info("get request to get details by id ");
        return insuranceRequestService.getInsuranceById(id);
    }

    @PatchMapping("/insurance/{insurance-id}/review")
    public InsuranceReviewDto reviewRequest(
            @PathVariable("insurance-id") Integer id ,
            @RequestBody InsuranceReviewDto insuranceReviewDto
    ){
        log.info("review the insurance request");
        return insuranceReviewService.reviewInsuranceRequest(id, insuranceReviewDto);
    }

    @PatchMapping("/insurance/{insurance-id}/approve")
    public InsuranceApproveDto approveRequest(
            @PathVariable("insurance-id") Integer id ,
            @RequestBody InsuranceApproveDto insuranceApproveDto
    ){
        log.info("review the insurance request");
        return insuranceApproveService.approveInsuranceRequest(id, insuranceApproveDto);
    }
}
