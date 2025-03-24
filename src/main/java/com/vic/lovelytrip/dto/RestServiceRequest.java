package com.vic.lovelytrip.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestServiceRequest<Body> implements Serializable {
        private Body body;
}
