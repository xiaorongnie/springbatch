package com.example.springbatch.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 编写第一个任务
 * <p>
 * 重新启动项目，控制台并不会再次打印出任务执行日志，因为Job名称和 Step名称组成唯一，执行完的不可重复的任务，不会再次执行。
 *
 * @Author Eason
 * @Date 2022-10-13 16:41
 * @Version 1.0
 */
@Component
public class FirstJobDemo {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob() {
        return jobBuilderFactory.get("firstJob")
                .start(step())
                .build();
    }

    private Step step() {
        return stepBuilderFactory.get("step")
                .tasklet((contribution, chunkContext) -> {
                    StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
                    Map<String, JobParameter> parameters = stepExecution.getJobParameters().getParameters();
                    System.out.println(parameters.get("message").getValue());
                    System.out.println(parameters.get("message").getValue());
                    System.out.println("执行步骤.1...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
