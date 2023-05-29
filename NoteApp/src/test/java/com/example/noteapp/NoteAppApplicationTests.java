package com.example.noteapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootTest
class NoteAppApplicationTests {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 测试日志输出
     * SLF4J 日志级别从小到大 trace>debug>info>warn>error
     */
    @Test
    void logTest() {
        //日志级别 由低到高
        logger.trace("trace 级别日志");
        logger.debug("debug 级别日志");
        logger.info("info 级别日志");
        logger.warn("warn 级别日志");
        logger.error("error 级别日志");
    }


    @Test
    void contextLoads() {
    }

}
