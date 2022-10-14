package com.example.springbatch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Eason
 * @Date 2022-10-14 17:12
 * @Version 1.0
 */
@Configuration
public class JobConfigure {

    /**
     * 注册JobRegistryBeanPostProcessor bean
     * 用于将任务名称和实际的任务关联起来
     */
    @Bean
    public JobRegistryBeanPostProcessor processor(JobRegistry jobRegistry, ApplicationContext applicationContext) {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
        return postProcessor;
    }
}