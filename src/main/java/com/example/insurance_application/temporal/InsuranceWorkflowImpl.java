package com.example.insurance_application.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class InsuranceWorkflowImpl implements InsuranceWorkflow{

    private static final Logger log = LoggerFactory.getLogger(InsuranceWorkflowImpl.class);
    private final RetryOptions retryOptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(5000).build();

    private final ActivityOptions options = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(60))
            .setRetryOptions(retryOptions).build();

    private final InsuranceActivity activity = Workflow.newActivityStub(InsuranceActivity.class, options);

    private static boolean isReviewApproved = false;
    private static boolean isRequestApproved = false;

    @Override
    public void startInsuranceWorkflow() {
        activity.requestInsurance();
        log.info("Waiting for manager to review");
        Workflow.await(() -> isRequestApproved);
    }

    @Override
    public void signalReviewRequest() {
        activity.reviewInsurance();
        isReviewApproved = true;
    }

    @Override
    public void signalApproveRequest() {
        activity.approveInsurance();
        isRequestApproved = true;
    }
}
