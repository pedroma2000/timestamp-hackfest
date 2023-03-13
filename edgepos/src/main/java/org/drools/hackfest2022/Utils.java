package org.drools.hackfest2022;

import java.util.stream.Stream;

public class Utils {

    public static Number sum(Number... values) {
        return Stream.of(values).mapToDouble(Number::doubleValue).sum();
    }

    public static double round(Number operand, int decimals) {
        double y = Math.pow(10.0, decimals);
        return Math.round(operand.doubleValue() * y) / y;
    }
    
    private Utils() {
        // only static methods
    }
}
