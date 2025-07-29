package com.vw.employeeportal.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class PwdUtil {

    public static String generateRandomPwd(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String pwd = RandomStringUtils.random(6,characters);
        return pwd;
    }

}
