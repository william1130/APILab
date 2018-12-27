package org.wm.apilab.utils;

import org.json.JSONObject;

public class JSONUtils {

    public static String resultString(String status, String message, Object result) {
        JSONObject jsonObject = new JSONObject() {
            {
                put("status", status);
                put("message", message);
                put("result", result);
            }
        };

        return jsonObject.toString();
    }

    public static String fillResultString(String code, Object result) {
        return resultString(code, Const.RESULT_MAP.get(code), result);
    }
    
}
