package com.daekyo.async_component;

import com.daekyo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class AsyncCompo {

    private final Logger logger = LoggerFactory.getLogger(AsyncCompo.class);

    @Async("threadPoolTaskExecutor")
    public Future<String> callPython() throws IOException {
        String[] commandArr = {"python3", "/Users/ilpark/PycharmProjects/whltest/test.py"};
        Process process = new ProcessBuilder(commandArr).start();

        BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

        List<String> lines = new ArrayList<>();
        String line = null;
        while((line = stdOut.readLine()) != null) {
            lines.add(line);
        }

        String result = String.join("\n", lines);
        logger.info("result : {}",result);
        return new AsyncResult<>(result);
    }

    @Async("threadPoolTaskExecutor")
    public Future<String> asyncTest() {
        logger.info("chk : {}",Thread.currentThread().getName());
        return new AsyncResult<>(Thread.currentThread().getName());
    }
}