package com.example.insurance_application.service;

import com.example.insurance_application.dto.InsuranceApproveDto;
import com.example.insurance_application.entity.Insurance;
import com.example.insurance_application.mapper.InsuranceApproveMapper;
import com.example.insurance_application.repository.InsuranceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InsuranceApproveService {
    private InsuranceRepository insuranceRepository;
    private InsuranceApproveMapper insuranceApproveMapper;

    private static final Logger log = LoggerFactory.getLogger(InsuranceApproveService.class);

    public InsuranceApproveService(InsuranceRepository insuranceRepository, InsuranceApproveMapper insuranceApproveMapper) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceApproveMapper = insuranceApproveMapper;
    }

    public InsuranceApproveDto approveInsuranceRequest(Integer id, InsuranceApproveDto insuranceApproveDto){
        log.info("insurance review service triggered");
        var approveInsurance = insuranceApproveMapper.toInsurance(insuranceApproveDto);
        Insurance insurance = insuranceRepository.findById(id)
                .orElse(null);
        Objects.requireNonNull(insurance).setStatus(approveInsurance.getStatus());
        insuranceRepository.save(insurance);
        return insuranceApproveMapper.toInsuranceApproveDto(insurance);
    }

}
