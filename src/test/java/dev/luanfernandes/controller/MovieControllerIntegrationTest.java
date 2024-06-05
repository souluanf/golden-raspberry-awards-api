package dev.luanfernandes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static dev.luanfernandes.constants.PathConstants.PRODUCERS_AWARD_INTERVALS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MovieControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetProducerIntervals() {
        String url = "http://localhost:" + port + PRODUCERS_AWARD_INTERVALS;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("min").contains("max");
    }

    @Test
    void testGetProducerIntervalsInvalidUrl() {
        String invalidUrl = "http://localhost:" + port + PRODUCERS_AWARD_INTERVALS + "invalid";
        ResponseEntity<String> response = restTemplate.getForEntity(invalidUrl, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}
