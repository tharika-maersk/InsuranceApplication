package com.example.insurance_application.config;

import com.example.insurance_application.temporal.InsuranceActivityImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class TemporalConfig {

    private String temporalNamespace = "default";


    @Bean
    public WorkflowServiceStubs workflowServiceStubs(){
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs){
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder().setNamespace(temporalNamespace).build());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public InsuranceActivityImpl insuranceActivity(){
        return new InsuranceActivityImpl();
    }
}
