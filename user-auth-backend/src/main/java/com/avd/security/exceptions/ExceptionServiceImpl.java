package com.avd.security.exceptions;

import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public void takeRisk() {
        throw new CustomException("Something went wrong!");
    }
}
