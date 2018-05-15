package com.sfj.sfj.base.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Created by wangyu on 2017/4/19.
 */

public class SharedPreferencesManager {
    private final static String APP_DATA = "_app_data";
    private final static String USER_DATA = "_user_data";
    private static SharedPreferencesManager manager = new SharedPreferencesManager();

    public static SharedPreferencesManager getInstance() {
        return manager;
    }

    private SharedPreferencesManager() {
    }

    private Context mContext;
    private SharedPreferences mSharedPreferences;


    public void init(Context context) {
        mContext = context;
    }

    public void saveValue(String key, int value, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, boolean value, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, String value, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, long value, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, int value) {
        saveValue(key, value, false);
    }

    public void saveValue(String key, boolean value) {
        saveValue(key, value, false);
    }

    public void saveValue(String key, String value) {
        saveValue(key, value, false);
    }

    public void saveValue(String key, long value) {
        saveValue(key, value, false);
    }

    public String getString(String key, String def) {
        return getString(key, def, false);
    }

    public Boolean getBoolean(String key, Boolean def) {
        return getBoolean(key, def, false);
    }

    public int getInt(String key, int def) {
        return getInt(key, def, false);
    }

    public long getLong(String key, long def) {
        return getLong(key, def, false);
    }


    public String getString(String key, String def, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, def);
        }
        return def;
    }

    public Boolean getBoolean(String key, Boolean def, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            return mSharedPreferences.getBoolean(key, def);
        }
        return def;
    }

    public int getInt(String key, int def, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            return mSharedPreferences.getInt(key, def);
        }
        return def;
    }

    public long getLong(String key, long def, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            return mSharedPreferences.getLong(key, def);
        }
        return def;


    }

    private SharedPreferences getSharedPreferences(boolean about) {
        if (mContext != null) {
            if (about) {
                mSharedPreferences = mContext.getSharedPreferences(USER_DATA + "userID", Context.MODE_PRIVATE);
                return mSharedPreferences;
            } else {
                mSharedPreferences = mContext.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
                return mSharedPreferences;
            }
        }
        return mSharedPreferences;
    }

    public void saveUserInfo(String account, String password, int loginType, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("account", account);
            editor.putString("password", password);
            editor.putInt("loginType", loginType);
            editor.commit();
        }
    }

    public String getStringValue(String key, boolean about) {
        getSharedPreferences(about);
        String value = "";
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getString(key, "");
        }
        return value;
    }

    public int getIntValue(String key, boolean about) {
        getSharedPreferences(about);
        int value = 0;
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getInt(key, 0);
        }
        return value;
    }

    public boolean saveObject(String key, Object object,boolean about) {
        getSharedPreferences(about);
        if (object == null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(),
                Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, objectStr);
        return editor.commit();
    }

    public Object getObject(String key, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences==null) return null;
        try {
            String wordBase64 = mSharedPreferences.getString(key, "");
            // 将base64格式字符串还原成byte数组
            if (wordBase64 == null || wordBase64.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成product对象
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeObject(String key, boolean about) {
        getSharedPreferences(about);
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(key).commit();
        }
    }
}
