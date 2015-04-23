import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liulei on 2015/4/22.
 */
public class GetAccessToken {

    public static void main(String[] args) throws IOException {
        String baseUrl = "https://oauth.api.189.cn/emp/oauth2/v3/access_token?";
        Map<String, String> params = new HashMap<String, String>();
        baseUrl+="grant_type=client_credentials";
        baseUrl+="&app_id=158250910000040896";
        baseUrl+="&app_secret=616cb185b7ff168c3ae1a42de14f7550";
        baseUrl+="&state=0";
        String result = HttpInvoker.httpPost(baseUrl, null, null);
        System.out.println(result);
    }
}
