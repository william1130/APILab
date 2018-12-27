package org.wm.apilab.utils;

import java.util.HashMap;
import java.util.Map;

public class Const {

    /** 作業成功 */
    public static final String RC_000 = "000";

    /** Bad Request */
    public static final String RC_400 = "400";

    /** Unauthorized */
    public static final String RC_401 = "401";

    /** Forbidden */
    public static final String RC_403 = "403";

    /** Not Found */
    public static final String RC_404 = "404";

    /** Method Not Allowed */
    public static final String RC_405 = "405";

    /** Unsupported Media Type */
    public static final String RC_415 = "415";

    /** Internal Server Error */
    public static final String RC_500 = "500";

    /** 查無資料 */
    public static final String RC_800 = "800";

    /** 客自 parameter validate error */
    public static final String RC_900 = "900";

    /** 資料庫連線失敗或逾時 */
    public static final String RC_901 = "901";

    /** IP不合法 */
    public static final String RC_902 = "902";

    /** 您無權限使用 */
    public static final String RC_903 = "903";

    /** 無法提供服務 */
    public static final String RC_904 = "904";

    /** 無法取得資訊 */
    public static final String RC_905 = "905";

    /** 無此使用者 */
    public static final String RC_906 = "906";

    /** 錯誤的呼叫來源 */
    public static final String RC_907 = "907";

    /** JSON格式錯誤 */
    public static final String RC_908 = "908";

    /** 無此使用者或密碼錯誤 */
    public static final String RC_909 = "909";

    /** 傳入參數錯誤 */
    public static final String RC_910 = "910";

    /** 密碼過期 */
    public static final String RC_911 = "911";

    /** 登入驗證有誤 */
    public static final String RC_912 = "912";

    /** 發生未知的錯誤 */
    public static final String RC_999 = "999";

    // 紀錄RESULT_CODE 對應的 RESULT_MSG
    public static Map<String, String> RESULT_MAP = new HashMap<String, String>();

    static {
        RESULT_MAP.put(RC_000, "作業成功");
        RESULT_MAP.put(RC_400, "Bad Request");
        RESULT_MAP.put(RC_401, "Unauthorized");
        RESULT_MAP.put(RC_403, "Forbidden");
        RESULT_MAP.put(RC_404, "Not Found");
        RESULT_MAP.put(RC_405, "Method Not Allowed");
        RESULT_MAP.put(RC_415, "Unsupported Media Type");
        RESULT_MAP.put(RC_500, "Internal Server Error");
        RESULT_MAP.put(RC_800, "查無資料");
        RESULT_MAP.put(RC_900, "request 參數錯誤");
        RESULT_MAP.put(RC_901, "資料庫連線失敗或逾時");
        RESULT_MAP.put(RC_902, "IP不合法");
        RESULT_MAP.put(RC_903, "您無權限使用");
        RESULT_MAP.put(RC_904, "無法提供服務");
        RESULT_MAP.put(RC_905, "無法取得資訊");
        RESULT_MAP.put(RC_906, "無此使用者");
        RESULT_MAP.put(RC_907, "錯誤的呼叫來源");
        RESULT_MAP.put(RC_908, "JSON格式錯誤");
        RESULT_MAP.put(RC_909, "無此使用者或密碼錯誤");
        RESULT_MAP.put(RC_910, "傳入參數錯誤");
        RESULT_MAP.put(RC_911, "密碼過期");
        RESULT_MAP.put(RC_912, "登入驗證有誤");
        RESULT_MAP.put(RC_999, "發生未知的錯誤");
    }
}
