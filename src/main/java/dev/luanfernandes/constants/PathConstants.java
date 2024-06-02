package dev.luanfernandes.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    private static final String API = "/api";
    public static final String MOVIES_V1 = API + "/v1/movies";
    public static final String PRODUCERS_AWARD_INTERVALS = MOVIES_V1 + "/producers-award-intervals";
}
