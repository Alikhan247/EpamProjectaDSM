package com.epam.adsm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by akmatleu on 29.05.17.
 */
public class ValidationAndEncoderService {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationAndEncoderService.class);

    public static String encodePassword(String password) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            for (byte b : bytes) {
                stringBuffer.append(Integer.toHexString(b & 0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.error("cannot encode password", e);
        }
        return stringBuffer.toString();
    }

    public static boolean isValidValue(String value, String validationProperty) {
        Pattern pattern = Pattern.compile(validationProperty);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
