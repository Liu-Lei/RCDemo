import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import open189.sign.ParamsSign;
import emp.tool.json.JSONObject;
public class Test
{
	static String app_id = "158250910000040896";
	static String app_secret = "616cb185b7ff168c3ae1a42de14f7550";
	static String access_token = "1b183d907cf469591024dc46a44074421429706745601";
	private static String sendSms(String userPhone) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(date);
		System.err.println(timestamp);
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("app_id", app_id);
		paramsMap.put("access_token", access_token);
		paramsMap.put("timestamp", timestamp);
		
		String getUrl = "http://api.189.cn/v2/dm/randcode/token?app_id=" + app_id
				+ "&access_token=" + access_token + "&timestamp="+timestamp + "&sign="+ParamsSign.value(paramsMap, app_secret);
		String resJson = HttpInvoker.httpGet(getUrl);
		System.err.println(resJson);
		JSONObject json = new JSONObject(resJson);
		System.out.println(json.get("token"));

		TreeMap<String, String> paramsMap1 = new TreeMap<String, String>();
		paramsMap1.put("app_id", app_id);
		paramsMap1.put("access_token", access_token);
		paramsMap1.put("timestamp", timestamp);
		paramsMap1.put("token", json.get("token").toString());
        paramsMap1.put("url", "http://search.rimaotong.com:8888");
        paramsMap1.put("phone", userPhone);
		paramsMap1.put("exp_time", "20");

        String postUrl = "http://api.189.cn/v2/dm/randcode/send";

        String postEntity = "app_id="+app_id
                + "&access_token="+access_token
                + "&token=" + json.get("token").toString()
                + "&phone=" + userPhone
                + "&url=" + "http://search.rimaotong.com:8888"
                + "&exp_time=20"
                + "&timestamp="+timestamp
                + "&sign="+ParamsSign.value(paramsMap1, app_secret);
		
		String resJson1 = HttpInvoker.httpPost(postUrl,null,postEntity);
		return resJson1;
	}

    public static void main(String[] args) {
        String userPhone = "15658153613";
        try {
            System.out.println(sendSms(userPhone));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}