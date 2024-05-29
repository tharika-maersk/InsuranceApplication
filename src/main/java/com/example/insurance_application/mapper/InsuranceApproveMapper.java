package com.example.insurance_application.mapper;

import com.example.insurance_application.dto.InsuranceApproveDto;
import com.example.insurance_application.entity.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsuranceApproveMapper {
    private static final Logger log = LoggerFactory.getLogger(InsuranceApproveMapper.class);

    public Insurance toInsurance(InsuranceApproveDto insuranceApproveDto){
        log.info("converting insurance review dto to insurance object");
        if(insuranceApproveDto == null){
            throw new NullPointerException("Insurance Dto shouldn't be null");
        }
        Insurance insurance = new Insurance();
        insurance.setStatus(insuranceApproveDto.status());
        return insurance;
    }
    public InsuranceApproveDto toInsuranceApproveDto(Insurance insurance){
        log.info("insurance to toInsuranceReviewDto");
        return new InsuranceApproveDto(
                insurance.getStatus()
        );
    }
}
