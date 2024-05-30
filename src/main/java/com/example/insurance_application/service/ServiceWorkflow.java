package com.example.insurance_application.service;


import com.example.insurance_application.temporal.InsuranceWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkflow {
    private static final String WORKFLOW_NAME = "Insurance_";
    WorkflowClient workflowClient;
    WorkflowServiceStubs workflowServiceStubs;

    @Autowired
    public ServiceWorkflow(WorkflowClient workflowClient, WorkflowServiceStubs workflowServiceStubs) {
        this.workflowClient = workflowClient;
        this.workflowServiceStubs = workflowServiceStubs;
    }

    public void insuranceRequestWorkflow(String workflowId){
        InsuranceWorkflow workflow = createConnectionWorkflow(workflowId);
        WorkflowClient.start(workflow::startInsuranceWorkflow);
    }

    public void insuranceReviewWorkflow(String workflowId){
        InsuranceWorkflow workflow = workflowClient.newWorkflowStub(InsuranceWorkflow.class, WORKFLOW_NAME + workflowId);
        workflow.signalReviewRequest();
    }

    public void insuranceApproveWorkflow(String workflowId){
        InsuranceWorkflow workflow = workflowClient.newWorkflowStub(InsuranceWorkflow.class, WORKFLOW_NAME + workflowId);
        workflow.signalApproveRequest();
    }

    public InsuranceWorkflow createConnectionWorkflow(String id){
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(InsuranceWorkflow.TASK_QUEUE_NAME)
                .setWorkflowId(WORKFLOW_NAME + id)
                .build();
        return workflowClient.newWorkflowStub(InsuranceWorkflow.class, options);
    }
}
