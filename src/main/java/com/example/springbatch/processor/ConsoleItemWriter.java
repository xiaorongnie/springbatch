package com.example.springbatch.processor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @Author Eason
 * @Date 2022-10-14 17:37
 * @Version 1.0
 */
@Slf4j
public class ConsoleItemWriter<T> implements ItemWriter<T> {


    @Override
    public void write(List<? extends T> items) throws Exception {
        log.trace("Console item writer starts");
        for (T item : items) {
            log.info(items.toString());
        }
        log.trace("Console item writer ends");
    }
}