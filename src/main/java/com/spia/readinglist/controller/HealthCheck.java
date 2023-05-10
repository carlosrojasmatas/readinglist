package com.spia.readinglist.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {


    @Getter
    @Setter
    private static class HealthResponse{
        private String status;

        private HealthResponse(String status){
            this.status = status;
        }

        public static HealthResponse create(String status){
            return new HealthResponse(status);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public HealthResponse health(){
        return HealthResponse.create("running");

    }
}
