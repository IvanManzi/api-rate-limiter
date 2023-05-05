package util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtil {

    public static String resultSuccess() throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 1);
        out.put(BSysConstants.RETURN_MSG, "Operation successful");
        return out.toString();
    }
    public static String resultSuccess(String info) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 1);
        out.put(BSysConstants.RETURN_MSG, info);
        return out.toString();
    }

    public static String resultSuccess(Object obj) throws JSONException {
        String aa = com.alibaba.fastjson.JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return aa;
    }

    public static String resultSuccess(Map<String, Object> data) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 1);
        out.put(BSysConstants.RETURN_DATA, data);
        out.put(BSysConstants.RETURN_MSG, "Operation successful");

        Map map = new HashMap();
        map.put(BSysConstants.RETURN_CODE, 1);
        map.put(BSysConstants.RETURN_DATA, data);
        map.put(BSysConstants.RETURN_MSG, "Operation successful");
        String aa = com.alibaba.fastjson.JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return aa;
    }

    public static String resultSuccessToGson(Map<String, Object> data) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 1);
        out.put(BSysConstants.RETURN_DATA, data);
        out.put(BSysConstants.RETURN_MSG, "Operation successful");
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put(BSysConstants.RETURN_CODE, 1);
        map.put(BSysConstants.RETURN_DATA, data);
        map.put(BSysConstants.RETURN_MSG, "Operation successful");

        String aa = gson.toJson(data);
        aa = com.alibaba.fastjson.JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue);

        return aa;
    }

    public static String resultFail() throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 2);
        StringBuilder msg = new StringBuilder("Operation failure");
        out.put(BSysConstants.RETURN_MSG, msg.toString());
        return out.toString();
    }

    public static String resultFail(String info) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 2);
        StringBuilder msg = new StringBuilder("");
        if (StringUtils.hasText(info)) {
            msg.append(" " + info);
        }
        out.put(BSysConstants.RETURN_MSG, msg.toString());
        return out.toString();
    }

    public static String resultFail(Map<String, Object> data) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 2);
        out.put(BSysConstants.RETURN_DATA, data);

        Map map = new HashMap();
        map.put(BSysConstants.RETURN_CODE, 2);
        map.put(BSysConstants.RETURN_DATA, data);
        String aa = com.alibaba.fastjson.JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return aa;

    }

    public static String resultFail(Map<String, Object> data,String msg) throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 2);
        out.put(BSysConstants.RETURN_DATA, data);
        out.put(BSysConstants.RETURN_MSG, msg);

        Map map = new HashMap();
        map.put(BSysConstants.RETURN_CODE, 2);
        map.put(BSysConstants.RETURN_DATA, data);
        map.put(BSysConstants.RETURN_MSG, msg);
        String aa = com.alibaba.fastjson.JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return aa;

    }

    public static String resultFailByToken() throws JSONException {
        JSONObject out = new JSONObject();
        out.put(BSysConstants.RETURN_CODE, 3);
        StringBuilder msg = new StringBuilder("Token does not exist, please login!");
        out.put(BSysConstants.RETURN_MSG, msg.toString());
        return out.toString();
    }
}
