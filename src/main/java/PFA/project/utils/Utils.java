package PFA.project.utils;

import PFA.project.config.utils.JwtUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Utils {
    public static String generateOTP() {
        // Define characters allowed in OTP


        // Shuffle the characters
        List<Character> charList = Constants.NUMBERS.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(charList);

        // Take the first 6 characters from the shuffled list
        String otp = IntStream.range(0, 6)
                .mapToObj(i -> charList.get(i).toString())
                .collect(Collectors.joining());

        return otp;
    }



}

