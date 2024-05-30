package com.example.insurance_application.temporal;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.springframework.stereotype.Service;

@WorkflowInterface
public interface InsuranceWorkflow {
    public static final String TASK_QUEUE_NAME = "Insurance-Request";

    @WorkflowMethod
    void startInsuranceWorkflow();

    @SignalMethod
    void signalReviewRequest();

    @SignalMethod
    void signalApproveRequest();
}
