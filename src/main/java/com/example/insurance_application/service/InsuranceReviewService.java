package com.example.insurance_application.service;

import com.example.insurance_application.dto.InsuranceReviewDto;
import com.example.insurance_application.entity.Insurance;
import com.example.insurance_application.mapper.InsuranceReviewMapper;
import com.example.insurance_application.repository.InsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InsuranceReviewService {
    private InsuranceRepository insuranceRepository;
    private InsuranceReviewMapper insuranceReviewMapper;
    private ServiceWorkflow serviceWorkflow;

    private static final Logger log = LoggerFactory.getLogger(InsuranceReviewService.class);

    public InsuranceReviewService(InsuranceRepository insuranceRepository, InsuranceReviewMapper insuranceReviewMapper, ServiceWorkflow serviceWorkflow) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceReviewMapper = insuranceReviewMapper;
        this.serviceWorkflow = serviceWorkflow;
    }

    public InsuranceReviewDto reviewInsuranceRequest(Integer id, InsuranceReviewDto insuranceReviewDto){
        log.info("insurance review service triggered");
        serviceWorkflow.insuranceReviewWorkflow(id.toString());
        var reviewInsurance = insuranceReviewMapper.toInsurance(insuranceReviewDto);
        Insurance insurance = insuranceRepository.findById(id)
                .orElse(null);
        Objects.requireNonNull(insurance).setStatus(reviewInsurance.getStatus());
        insurance.setAmountApproved(reviewInsurance.getAmountApproved());
        insurance.setApprovedBy(reviewInsurance.getApprovedBy());
        insuranceRepository.save(insurance);
        return insuranceReviewMapper.toInsuranceReviewDto(insurance);
    }

}
