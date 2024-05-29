package com.example.insurance_application.mapper;

import com.example.insurance_application.controller.InsuranceAppController;
import com.example.insurance_application.dto.InsuranceRequestDto;
import com.example.insurance_application.entity.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsuranceRequestMapper {
    private static final Logger log = LoggerFactory.getLogger(InsuranceRequestMapper.class);

    public Insurance toInsurance(InsuranceRequestDto insuranceRequestDto){
        log.info("converting insurance request dto to insurance object");
        if(insuranceRequestDto == null){
            throw new NullPointerException("Insurance Dto shouldn't be null");
        }
        Insurance insurance = new Insurance();
        insurance.setName(insuranceRequestDto.name());
        insurance.setAmount(insuranceRequestDto.amount());
        return insurance;
    }
    public InsuranceRequestDto toInsuranceRequestDto(Insurance insurance){
        log.info("insrunce to InsuranceRequestDto");
        return new InsuranceRequestDto(
                insurance.getName(),
                insurance.getAmount()
        );
    }
}
