package com.vic.lovelytrip.dto.restservice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestServiceRequest<T> implements Serializable {
        private T body;
}
