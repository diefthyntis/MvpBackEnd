package com.diefthyntis.MinimumViableProduct.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class FileUtils {

    /**
     * Extracts the file extension from a given filename string.
     * @param filename the filename from which to extract the extension
     * @return an Optional containing the file extension, or an empty Optional if no extension is found
     */
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    /**
     * Generates a string representation of the current date and time in the format "yyyyMMddhhmmss" and the extension.
     *
     * @param ext the file extension to append to the generated string
     * @return the generated file name composed of the generated string and the extension.
     */
    public static String generateStringFromDate(String ext) {
        return new SimpleDateFormat("yyyyMMddhhmmss'."+ext+"'").format(new Date());
    }
}