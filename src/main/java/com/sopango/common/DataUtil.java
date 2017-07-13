package com.sopango.common;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sopango.model.data.YunData;

/**
 * 
 *
 * @author thb
 * @date 2017-7-13 上午10:21:47
 * @since  V1.0
 */
public class DataUtil {

    private static OkHttpClient client = new OkHttpClient();
    private static Logger log = Logger.getLogger(DataUtil.class);

    private static String get(String url) {
        Request req = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(req).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            ResponseBody body = response.body();
            if(body == null ){
                return null;
            }
            return body.string();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
    private static String get(String url,Map<String, String> headers) {
        Request req = new Request.Builder().url(url).headers(Headers.of(headers)).build();
        try {
            Response response = client.newCall(req).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            ResponseBody body = response.body();
            if(body == null ){
                return null;
            }
            return body.string();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static YunData getYunData(String url) {
        String html = get(url);
        if (StringUtils.isBlank(html)) {
            return null;
        }
        Pattern pattern = Pattern.compile("window.yunData = (.*})");
        Matcher matcher = pattern.matcher(html);
        String json = null;
        while (matcher.find()) {
            json = matcher.group(1);
        }
        if (json == null) {
            return null;
        }
        YunData yunData = JSON.parseObject(json, YunData.class);
        return yunData;
    }
    
    public static YunData getFollowData(String url,Map<String, String> headers){
        String json = get(url,headers);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        YunData yunData = JSON.parseObject(json, YunData.class);
        return yunData;
    }
}
