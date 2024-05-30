package com.example.insurance_application.service;

import com.example.insurance_application.dto.InsuranceRequestDto;
import com.example.insurance_application.mapper.InsuranceRequestMapper;
import com.example.insurance_application.repository.InsuranceRepository;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsuranceRequestService {
    private ServiceWorkflow serviceWorkflow;
    private InsuranceRepository insuranceRepository;
    private InsuranceRequestMapper insuranceRequestMapper;

    private static final Logger log = LoggerFactory.getLogger(InsuranceRequestService.class);

    public InsuranceRequestService(InsuranceRequestMapper insuranceRequestMapper, InsuranceRepository insuranceRepository, ServiceWorkflow serviceWorkflow) {
        this.insuranceRequestMapper = insuranceRequestMapper;
        this.insuranceRepository = insuranceRepository;
        this.serviceWorkflow = serviceWorkflow;
    }

    public InsuranceRequestDto createRequest(InsuranceRequestDto insuranceRequestDto){
        log.info("insurance request service triggered");
        var insuranceRequest = insuranceRequestMapper.toInsurance(insuranceRequestDto);
        var savedInsuranceDetails = insuranceRepository.save(insuranceRequest);
        serviceWorkflow.insuranceRequestWorkflow(savedInsuranceDetails.getId().toString());
        return insuranceRequestMapper.toInsuranceRequestDto(savedInsuranceDetails);
    }

    public InsuranceRequestDto getInsuranceById(Integer id){
        log.info("getting the insurance details by id");
        return insuranceRepository.findById(id)
                .map(insuranceRequestMapper::toInsuranceRequestDto)
                .orElse(null);
    }

}
