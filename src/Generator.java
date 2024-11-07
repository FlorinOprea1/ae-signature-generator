import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Generator {

    public static final String SIGN_METHOD_SHA256 = "sha256";
    public static final String SIGN_METHOD_HMAC_SHA256 = "HmacSHA256";

    public static String signApiRequest(Map<String, String> params, String appSecret, String signMethod) throws IOException {

        // sort all text parameters
        SortedSet<String> keys = new TreeSet<>(params.keySet());

        // join key values
        StringBuilder query = new StringBuilder();

        for (String key : keys) {
            String value = params.get(key);
            if (value == null || value.isEmpty()) {
                System.out.println("Value for " + key + " is empty");
            } else {
                query.append(key).append(value);
            }
        }

        System.out.println("Spliced String: " + query);

        // UTF-8 encode and sing with sha256 and app secret
        byte[] bytes = null;

        if (signMethod.equals(SIGN_METHOD_SHA256)) {
            bytes = encryptHMACSHA256(query.toString(), appSecret);
        }

        // convert to hex
        return byte2hex(bytes);
    }


    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SIGN_METHOD_HMAC_SHA256);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }


    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}

