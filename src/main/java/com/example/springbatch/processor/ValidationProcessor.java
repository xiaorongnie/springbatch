package com.example.springbatch.processor;

/**
 * @Author Eason
 * @Date 2022-10-14 17:33
 * @Version 1.0
 */

import org.springframework.batch.item.ItemProcessor;

public class ValidationProcessor implements ItemProcessor<Employee, Employee> {
    public Employee process(Employee employee) throws Exception {
        if (employee.getId() == null) {
            System.out.println("Missing employee id : " + employee.getId());
            return null;
        }

        try {
            if (Integer.valueOf(employee.getId()) <= 0) {
                System.out.println("Invalid employee id : " + employee.getId());
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid employee id : " + employee.getId());
            return null;
        }
        return employee;
    }
}
