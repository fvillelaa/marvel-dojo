package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by fvillela on 5/15/17.
 */

public class MarvelApiRequestSignature {
    public long timeStamp;
    public String publicKey = "dd9484ba66538778ce6bb40b378c6d96";
    public String privateKey = "ba9c79cbe8374a635d322fc3558e0314241d54d9";
    public String hashSignature;
    private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));


    public MarvelApiRequestSignature(){
        this.timeStamp = calendar.getTimeInMillis() / 1000L;
        this.hashSignature = md5(String.valueOf(this.timeStamp) + privateKey + publicKey);
    }

    private String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
