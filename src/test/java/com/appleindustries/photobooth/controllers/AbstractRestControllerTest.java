package com.appleindustries.photobooth.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

/**
 * @author zane
 * <p>
 * Converts JSON result of @RestController to the expected Entity T
 */
public class AbstractRestControllerTest {


    /**
     * @param mvcResult
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    protected <T> T mvcResultAsObject(MvcResult mvcResult, Class<T> clazz) throws IOException {
        String content = mvcResult.getResponse().getContentAsString();
        return mapFromJson(content, clazz);
    }

    /**
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
