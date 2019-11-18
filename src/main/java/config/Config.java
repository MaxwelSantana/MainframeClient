package config;

import com.sun.media.sound.InvalidFormatException;
import exception.JagacyException;

import java.util.HashMap;
import java.util.Map;

//com.jagacy.util.d
public class Config {
    private Map a1 = new HashMap<Object, Object>();

    private JagacyProperties jagacyProperties;

    public Config(JagacyProperties paramJagacyProperties) { this.jagacyProperties = paramJagacyProperties; }

    public JagacyProperties getParam() { return this.jagacyProperties; }

    public String getParam(String paramString) throws JagacyException {
        String str = (String)this.a1.get(paramString);
        if (str == null) {
            str = this.jagacyProperties.get(paramString);
            this.a1.put(paramString, str);
        }
        return str;
    }

    public String getParam(String paramString1, String paramString2) {
        String str = (String)this.a1.get(paramString1);
        if (str == null) {
            str = this.jagacyProperties.get(paramString1, paramString2);
            this.a1.put(paramString1, str);
        }
        return str;
    }

    public int getParam(String paramString, int paramInt) {
        int i = 0;
        Integer integer = (Integer)this.a1.get(paramString);
        if (integer == null) {
            i = this.jagacyProperties.getCardinal(paramString, paramInt);
            this.a1.put(paramString, new Integer(i));
        } else {
            i = integer.intValue();
        }
        return i;
    }

    public int ifa(String paramString, int paramInt) throws InvalidFormatException {
        int i = 0;
        Integer integer = (Integer)this.a1.get(paramString);
        if (integer == null) {
            i = this.jagacyProperties.getTimeout(paramString, paramInt);
            this.a1.put(paramString, new Integer(i));
        } else {
            i = integer.intValue();
        }
        return i;
    }

    public boolean getParam(String paramString, boolean paramBoolean) {
        boolean bool = false;
        Boolean bool1 = (Boolean)this.a1.get(paramString);
        if (bool1 == null) {
            bool = this.jagacyProperties.getBoolean(paramString, paramBoolean);
            this.a1.put(paramString, new Boolean(bool));
        } else {
            bool = bool1.booleanValue();
        }
        return bool;
    }

    public int getTimeout(String paramString, int paramInt) {
        int i = 0;
        Integer integer = (Integer)this.a1.get(paramString);
        if (integer == null) {
            i = this.jagacyProperties.getTimeout(paramString, paramInt);
            this.a1.put(paramString, new Integer(i));
        } else {
            i = integer.intValue();
        }
        return i;
    }

    public JagacyProperties getProperties() { return this.jagacyProperties; }
}
