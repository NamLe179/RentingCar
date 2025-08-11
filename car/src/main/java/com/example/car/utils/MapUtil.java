package com.example.car.utils;

import com.example.car.enums.OtoStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MapUtil {
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) throws ParseException {
        Object obj = params.getOrDefault(key, null);
        if(obj != null) {
            if(tClass.getTypeName().equals("java.lang.Long")) {
                obj = obj != "" ? Long.valueOf(obj.toString()) : null;
            }
            else if(tClass.getTypeName().equals("java.lang.Integer")) {
                obj = obj != "" ? Integer.valueOf(obj.toString()) : null;
            }
            else if(tClass.getTypeName().equals("java.lang.String")) {
                obj = obj.toString();
            }
            else if(tClass.getTypeName().equals("java.lang.Float")) {
                obj = obj != "" ? Float.valueOf(obj.toString()) : null;
            }
            else if(tClass.getTypeName().equals("java.util.Date")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                obj = obj != "" ? formatter.parse(obj.toString()) : null;
            }
            else if(tClass.getTypeName().equals("com.example.car.enums.OtoStatus")) {
                if (obj == "") obj = null;
                int status = Integer.parseInt(obj.toString());
                obj = switch (status) {
                    case 0 -> OtoStatus.CHO_DUYET;
                    case 1 -> OtoStatus.OK;
                    case 2 -> OtoStatus.SUA_CHUA;
                    case 3 -> OtoStatus.HET_HAN_HOP_DONG;
                    default -> obj;
                };
            }
            return tClass.cast(obj);
        }
        return null;
    }
}