package com.texo.gra;

import com.texo.gra.controller.MoviesController;
import com.texo.gra.service.MoviesService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {

    @Autowired
    private MoviesController moviesController;

    @Autowired
    MoviesService moviesService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(moviesController);
    }
}
