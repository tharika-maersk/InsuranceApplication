package com.example.insurance_application.mapper;

import com.example.insurance_application.dto.InsuranceReviewDto;
import com.example.insurance_application.entity.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsuranceReviewMapper {
    private static final Logger log = LoggerFactory.getLogger(InsuranceReviewMapper.class);

    public Insurance toInsurance(InsuranceReviewDto insuranceReviewDto){
        log.info("converting insurance review dto to insurance object");
        if(insuranceReviewDto == null){
            throw new NullPointerException("Insurance Dto shouldn't be null");
        }
        Insurance insurance = new Insurance();
        insurance.setStatus(insuranceReviewDto.status());
        insurance.setApprovedBy(insuranceReviewDto.approvedBy());
        insurance.setAmountApproved(insuranceReviewDto.amountApproved());
        return insurance;
    }
    public InsuranceReviewDto toInsuranceReviewDto(Insurance insurance){
        log.info("insurance to toInsuranceReviewDto");
        return new InsuranceReviewDto(
                insurance.getStatus(),
                insurance.getApprovedBy(),
                insurance.getAmountApproved()
        );
    }
}
