package com.example.insurance_application.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface InsuranceActivity {

    @ActivityMethod
    void requestInsurance();

    @ActivityMethod
    void reviewInsurance();

    @ActivityMethod
    void approveInsurance();
}
