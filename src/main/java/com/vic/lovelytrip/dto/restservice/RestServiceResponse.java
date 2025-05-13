package com.vic.lovelytrip.dto.restservice;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestServiceResponse<T> implements Serializable {

        static final long serialVersionUID = 1L;

        private String responseCode;
        private String responseDescription;

        private T responseBody;
        private T errors;

}
