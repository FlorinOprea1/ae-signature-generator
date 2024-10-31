import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {


        Map<String, String> params = Map.of(
                "app_key", "510620",
                "access_token",  "test",
                "start_time", "2024-10-28%2000%3A00%3A00",
                "end_time", "2024-10-29%2000%3A00%3A00",
                "status", "Payment%20Completed",
                "method", "aliexpress.affiliate.order.list",
                "timestamp", "1730368534",
                "sign_method", "sha256"

        );

        String signature = Generator.signApiRequest(params,
                "appSecret",
                "sha256"
        );

        System.out.println(signature);
    }
}