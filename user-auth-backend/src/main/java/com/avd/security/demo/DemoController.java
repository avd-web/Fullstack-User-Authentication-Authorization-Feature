package com.avd.security.demo;

import com.avd.security.exceptions.ExceptionService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class DemoController {

    private final ExceptionService exceptionService;

    @Autowired
    public DemoController(ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

//    //Normal demo-endpoint
//  @GetMapping
//  public ResponseEntity<String> sayHello() {
//    return ResponseEntity.ok("Hello from secured endpoint");
//  }

    //Throw Custom Exception
    @GetMapping
    public ResponseEntity<String> sayRisk() {
        exceptionService.takeRisk();
        return ResponseEntity.ok("exceptionService.takeRisk() didn't work.");
    }

//    //Throw Custom Exception directly from Controller.
//  @ExceptionHandler(CustomException.class)
//  public ResponseEntity<String> handleCustomException(final CustomException ex) {
//    return ResponseEntity.badRequest().body("I'm a CustomException thrown from the DemoController directly.");
//  }

}
