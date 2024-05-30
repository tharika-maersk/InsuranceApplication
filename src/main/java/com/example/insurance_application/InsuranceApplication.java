package com.example.insurance_application;

import com.example.insurance_application.temporal.InsuranceActivity;
import com.example.insurance_application.temporal.InsuranceWorkflow;
import com.example.insurance_application.temporal.InsuranceWorkflowImpl;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class InsuranceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(InsuranceApplication.class, args);
		WorkerFactory factory = applicationContext.getBean(WorkerFactory.class);
		InsuranceActivity insuranceActivity = applicationContext.getBean(InsuranceActivity.class);
		Worker worker = factory.newWorker(InsuranceWorkflow.TASK_QUEUE_NAME);
		worker.registerWorkflowImplementationTypes(InsuranceWorkflowImpl.class);
		worker.registerActivitiesImplementations(insuranceActivity);
		factory.start();
	}
}
