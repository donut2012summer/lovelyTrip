package com.vic.lovelytrip.dto;


import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Serializable;

@Getter
@Setter
public class RestServiceResponse<Body> implements Serializable {

        static final long serialVersionUID = 1L;

        private String responseCode;
        private String responseDescription;

        private Body responseBody;

}
