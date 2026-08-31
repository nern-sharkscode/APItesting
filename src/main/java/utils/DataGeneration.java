package utils;

public class DataGeneration {
    public String generateEmail(){
        String randomEmail = "oleksandrhundertailo+" + System.currentTimeMillis() + "@sharkscode.com";
        return randomEmail;
    };
}
