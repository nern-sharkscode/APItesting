package utils;

public class DataGeneration {
    public static String generateEmail(){
        String randomEmail = "oleksandrhundertailo+" + System.currentTimeMillis() + "@sharkscode.com";
        return randomEmail;
    };
}
