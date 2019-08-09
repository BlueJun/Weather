package cn.itcast.weatherone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Util {

    public List<Map<String, Object>> getInformation(String jonString)
            throws Exception {

        JSONObject jsonObject = new JSONObject(jonString);
        JSONObject retData = jsonObject.getJSONObject("retData");
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityName", retData.optString("city"));
        map.put("weather", retData.optString("weather"));
        map.put("temp", retData.optString("temp"));
        map.put("l_temp", retData.optString("l_tmp"));
        map.put("h_temp", retData.optString("h_tmp"));
        all.add(map);

        return all;

    }

}


