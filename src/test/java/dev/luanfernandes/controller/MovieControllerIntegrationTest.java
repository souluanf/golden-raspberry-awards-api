package dev.luanfernandes.controller;

import static dev.luanfernandes.constants.PathConstants.PRODUCERS_AWARD_INTERVALS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
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

    static Stream<ProducerTestParams> producerTestParamsProvider() {
        return Stream.of(
                new ProducerTestParams(0, 0, 200),
                new ProducerTestParams(1, 0, 200),
                new ProducerTestParams(0, 1, 200));
    }

    @ParameterizedTest
    @MethodSource("producerTestParamsProvider")
    void testGetProducerIntervalsParameterized(ProducerTestParams params) {
        String url = String.format("http://localhost:%d", port) + PRODUCERS_AWARD_INTERVALS;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(params.expectedStatusCode);

        if (params.expectedStatusCode == 200) {
            JSONArray minArray;
            JSONArray maxArray;
            try {
                JSONObject jsonResponse = new JSONObject(response.getBody());
                minArray = jsonResponse.getJSONArray("min");
                maxArray = jsonResponse.getJSONArray("max");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            assertThat(minArray.length()).isGreaterThan(params.minExpectedLength);
            assertThat(maxArray.length()).isGreaterThan(params.maxExpectedLength);
        }
    }

    static class ProducerTestParams {
        int minExpectedLength;
        int maxExpectedLength;
        int expectedStatusCode;

        public ProducerTestParams(int minExpectedLength, int maxExpectedLength, int expectedStatusCode) {
            this.minExpectedLength = minExpectedLength;
            this.maxExpectedLength = maxExpectedLength;
            this.expectedStatusCode = expectedStatusCode;
        }
    }
}
