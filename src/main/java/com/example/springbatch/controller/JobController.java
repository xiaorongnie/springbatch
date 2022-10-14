package com.example.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Eason
 * @Date 2022-10-14 17:00
 * @Version 1.0
 */
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private Job firstJob;

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("launcher/{message}")
    public String launcher(@PathVariable String message) throws Exception {
        JobParameters parameters = new JobParametersBuilder()
                .addString("message", message)
                .toJobParameters();
        // 将参数传递给任务
        jobLauncher.run(firstJob, parameters);
        return "success";
    }


    /**
     * {@link com.example.springbatch.config.JobConfigure#processor(JobRegistry, ApplicationContext)}  }
     *
     * @see com.example.springbatch.config.JobConfigure#processor(JobRegistry, ApplicationContext)
     */
    @Autowired
    private JobOperator jobOperator;

    @GetMapping("operator/{message}")
    public String operator(@PathVariable String message) throws Exception {
        // 传递任务名称，参数使用 kv方式
        jobOperator.start("firstJob", "message=" + message);
        return "success";
    }
}