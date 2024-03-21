package com.texo.gra;

import com.texo.gra.controller.MoviesController;
import com.texo.gra.dto.AwardIntervalsDTO;
import com.texo.gra.dto.ProducerIntervalDTO;
import com.texo.gra.service.MoviesService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {

    @Autowired
    private MoviesController moviesController;

    @MockBean
    MoviesService moviesService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(moviesController);
        Mockito.when(moviesService.calculateRangeAwards())
                .thenReturn(new AwardIntervalsDTO(
                        List.of(
                                ProducerIntervalDTO.builder()
                                        .producer("Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt")
                                        .interval(1)
                                        .previousWin(2011)
                                        .followingWin(2012)
                                        .build(),
                                ProducerIntervalDTO.builder()
                                        .producer("Yoram Globus and Menahem Golan")
                                        .interval(1)
                                        .previousWin(1986)
                                        .followingWin(1987)
                                        .build()
                        ),
                        List.of(
                                ProducerIntervalDTO.builder()
                                        .producer("Jerry Weintraub")
                                        .interval(9)
                                        .previousWin(1980)
                                        .followingWin(1989)
                                        .build(),
                                ProducerIntervalDTO.builder()
                                        .producer("Dino De Laurentiis")
                                        .interval(8)
                                        .previousWin(1985)
                                        .followingWin(1993)
                                        .build()
                        )));
    }
}
