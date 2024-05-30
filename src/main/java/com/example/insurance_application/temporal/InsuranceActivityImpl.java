package com.example.insurance_application.temporal;

import com.example.insurance_application.dto.InsuranceRequestDto;
import com.example.insurance_application.mapper.InsuranceRequestMapper;
import com.example.insurance_application.repository.InsuranceRepository;
import com.example.insurance_application.service.InsuranceRequestService;
import com.example.insurance_application.service.ServiceWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsuranceActivityImpl implements InsuranceActivity{

    private static final Logger log = LoggerFactory.getLogger(InsuranceActivityImpl.class);

    @Override
    public void requestInsurance() {
        log.info("request insurance");
   }

    @Override
    public void reviewInsurance() {
        log.info("reviewed insurance");

    }

    @Override
    public void approveInsurance() {
        log.info("approved insurance");
    }
}
