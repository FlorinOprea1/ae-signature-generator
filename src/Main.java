import java.io.IOException;
import java.util.Map;

//doc: https://openservice.aliexpress.com/doc/doc.htm?spm=a2o9m.11193531.0.0.35f53b53m2DcVg&nodeId=27493&docId=118729#/?docId=1386

public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, String> params = Map.of(
                "app_key", "510620",
                "start_time", "2024-10-28 00:00:00",
                "end_time", "2024-10-29 00:00:00",
                "status", "Payment Completed",
                "method", "aliexpress.affiliate.order.list",
                "timestamp", "1730978175",
                "sign_method", "sha256"

        );

        String signature = Generator.signApiRequest(
                params,
                "appSecret",
                "sha256"
        );

        System.out.println("Result: " + signature);
    }
}