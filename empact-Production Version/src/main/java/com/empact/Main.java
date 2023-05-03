package com.empact;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        List<String> versions = Arrays.asList("2.5.0-dev.1", "2.4.2-5354", "2.4.2-test.675", "2.4.1-integration.1");
        for ( String version :versions)
        {
            String productionVersion = extractProductionVersion(version);
            if (productionVersion != null) {
                System.out.println(productionVersion);
            }
        }

    }

    public static String extractProductionVersion(String version) {
        // Define a regular expression to match the production version format
        String regex = "^(\\d+\\.\\d+\\.\\d+\\-\\d\\d\\d\\d)-?(.*)$";
        Pattern pattern = Pattern.compile(regex);

        // Apply the regular expression to the version string
        Matcher matcher = pattern.matcher(version);
        if (matcher.find()) {
            return matcher.group(1);
        }

        // Return null if no match was found
        return null;

    }

}
