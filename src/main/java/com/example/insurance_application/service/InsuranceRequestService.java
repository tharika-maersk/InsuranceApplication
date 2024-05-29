package com.example.insurance_application.service;

import com.example.insurance_application.controller.InsuranceAppController;
import com.example.insurance_application.dto.InsuranceRequestDto;
import com.example.insurance_application.mapper.InsuranceRequestMapper;
import com.example.insurance_application.repository.InsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsuranceRequestService {

    private InsuranceRepository insuranceRepository;
    private InsuranceRequestMapper insuranceRequestMapper;

    private static final Logger log = LoggerFactory.getLogger(InsuranceRequestService.class);

    public InsuranceRequestService(InsuranceRequestMapper insuranceRequestMapper, InsuranceRepository insuranceRepository) {
        this.insuranceRequestMapper = insuranceRequestMapper;
        this.insuranceRepository = insuranceRepository;
    }

    public InsuranceRequestDto createRequest(InsuranceRequestDto insuranceRequestDto){
        log.info("insurance request service triggered");
        var insuranceRequest = insuranceRequestMapper.toInsurance(insuranceRequestDto);
        var savedInsuranceDetails = insuranceRepository.save(insuranceRequest);
        return insuranceRequestMapper.toInsuranceRequestDto(savedInsuranceDetails);
    }

    public InsuranceRequestDto getInsuranceById(Integer id){
        log.info("getting the insurance details by id");
        return insuranceRepository.findById(id)
                .map(insuranceRequestMapper::toInsuranceRequestDto)
                .orElse(null);
    }
}
