package config;

import exception.JagacyException;
import utils.Util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;
import java.util.Enumeration;

public final class JagacyProperties {
    public static final String TRUE = Boolean.TRUE.toString();

    public static final String FALSE = Boolean.FALSE.toString();

    public static boolean USE_ENV = false;

    private Map a1;

    private String a2;

    private String a3;

    private String a;

    private BigInteger a4 = BigInteger.ZERO;

    private String sessionName;

    private boolean ifb;

    public JagacyProperties(String paramString, Properties paramProperties) throws JagacyException {
        if (Util.isEmpty(paramString))
            throw new JagacyException(7, "Empty name");
        if (paramString.equals("jagacy"))
            throw new JagacyException(7, "Name cannot be 'jagacy'");
        if (paramString.equals("java"))
            throw new JagacyException(7, "Name cannot be 'java'");
        this.a2 = paramString.replace(' ', '_');
        this.sessionName = String.valueOf(this.a2) + ".";
        this.a = System.getProperty("jagacy.properties.dir");
        if (!Util.isEmpty(this.a))
            this.a = Util.removeQuotes(this.a);
        if (USE_ENV && Util.isEmpty(this.a))
            try {
                this.a = System.getenv("JAGACY_PROPERTIES_DIR");
            } catch (Error error) {}
        this.ifb = !(!"classpath".equalsIgnoreCase(this.a) && !":classpath:".equalsIgnoreCase(this.a));
        this.a1 = Collections.synchronizedMap(loadProperties(paramProperties));
    }

    public void set(String paramString1, String paramString2) {
        if (Util.isEmpty(paramString1))
            throw new IllegalArgumentException("Empty key");
        if (paramString2 == null)
            throw new IllegalArgumentException("Null value");
        if (!paramString1.startsWith("jagacy.") && !paramString1.startsWith(this.sessionName))
        paramString1 = String.valueOf(this.sessionName) + paramString1;
        this.a1.put(paramString1, paramString2);
    }

    private Map loadProperties(Properties paramProperties) throws JagacyException {
        Map<String, String> hashMap = new HashMap<String, String>();
        boolean bool = false;
        bool = loadProperties("jagacy.", hashMap);
        bool = !(!loadProperties(this.sessionName, hashMap) && !bool);
        if (!bool && !Util.isEmpty(this.a))
            throw new JagacyException(15, "Could not read either property file from directory:\n" + this.a);
        if (paramProperties != null)
            loadProperties(null, hashMap, paramProperties);
        loadProperties(null, hashMap, System.getProperties());
        return hashMap;
    }

    private boolean loadProperties(String paramString, Map paramMap) {
        Properties properties = null;
        boolean bool = false;
        InputStream inputStream = null;
        String str = paramString.concat("properties");
        try {
            if (this.ifb) {
                inputStream = JagacyProperties.class.getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = JagacyProperties.class.getClassLoader().getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
            } else {
                String str1 = this.a;
                if (str1 != null && (str1.startsWith("http://") || str1.startsWith("ftp://"))) {
                    if (!Util.isEmpty(str1)) {
                        if (!str1.endsWith("/"))
                            str1 = String.valueOf(str1) + "/";
                        str = String.valueOf(str1) + str;
                    }
                    inputStream = (new URL(str)).openStream();
                } else {
                    if (!Util.isEmpty(str1)) {
                        if (!str1.endsWith(File.separator))
                            str1 = String.valueOf(str1) + File.separator;
                        str = String.valueOf(str1) + str;
                    }
                    inputStream = new FileInputStream(str);
                }
            }
            properties = new Properties();
            properties.load(new BufferedInputStream(inputStream, 4096));
            loadProperties(paramString, paramMap, properties);
            bool = true;
        } catch (Exception exception) {

        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (Exception exception) {}
        }
        return bool;
    }

    private void loadProperties(String paramString, Map<String, String> paramMap, Properties paramProperties) {
        Enumeration<Object> enumeration = paramProperties.keys();
        while (enumeration.hasMoreElements()) {
            String str3 = (String) enumeration.nextElement();
            String str1 = str3;
            if (Util.isEmpty(str1))
                continue;
            if (!str1.startsWith("jagacy.") && !str1.startsWith(this.sessionName) && (paramString == null || !str1.startsWith(paramString))) {
                if (paramString == null)
                    continue;
                str3 = String.valueOf(paramString) + str1;
            }
            String str2 = paramProperties.getProperty(str1);
            if (Util.isEmpty(str2))
                continue;
            str2 = str2.trim();
            if (Util.isEmpty(str2))
                continue;
            str2 = Util.removeQuotes(str2);
            if ((!"jagacy.".equals(paramString) && String.valueOf(loadProperties(paramMap, String.valueOf(str3) + ".override")).equalsIgnoreCase("false")) || (!"jagacy".equals(paramString) && str3.endsWith(".override")))
                continue;
            paramMap.put(str3, str2);
        }
    }

    public JagacyProperties(String paramString) throws JagacyException { //this(paramString, null);
    }

    public int getTimeout(String paramString, int paramInt) {
        String str = loadProperties(paramString);
        return (str == null) ? paramInt : secs(paramString, str);
    }

    private int secs(String paramString1, String paramString2) {
        int i = loadProperties(paramString1, paramString2);
        if (paramString1.endsWith(".seconds"))
            i *= 1000;
        return i;
    }

    private int loadProperties(String paramString1, String paramString2) {
        int i = 0;
        try {
            byte b = 10;
            String str = paramString2.trim();
            if (str.startsWith("0x") && str.length() > 2) {
                b = 16;
                str = str.substring(2);
            }
            i = Integer.parseInt(str, b);
        } catch (NumberFormatException numberFormatException) {
            //throw new InvalidFormatException("Could not parse int property " + paramString1 + "=" + paramString2);
        }
        //if (i < 0)
            //throw new InvalidFormatException("Invalid negative property " + paramString1 + "=" + i);
        return i;
    }

    public int getCardinal(String paramString, int paramInt) {
        String str = loadProperties(paramString);
        return (str == null) ? paramInt : loadProperties(paramString, str);
    }

    public String get(String paramString1, String paramString2) {
        String str = loadProperties(paramString1);
        return (str == null) ? paramString2 : str;
    }

    public String get(String paramString) throws JagacyException {
        String str = loadProperties(paramString);
        if (str == null)
            throw new JagacyException(5, "Could not find property " + paramString);
        return str;
    }

    public boolean getBoolean(String paramString, boolean paramBoolean) {
        String str = loadProperties(paramString);
        return (str == null) ? paramBoolean : ifa(paramString, str);
    }

    private boolean ifa(String paramString1, String paramString2) {
        boolean bool = false;
        paramString2 = paramString2.toLowerCase().trim();
        if (paramString2.equals("true")) {
            bool = true;
        } else if (paramString2.equals("false")) {
            bool = false;
        } else {
            //throw new InvalidFormatException("Could not parse boolean property " + paramString1 + "=" + paramString2);
        }
        return bool;
    }
/*
    private Map a(Properties paramProperties) throws exception.JagacyException {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        boolean bool = false;
        bool = a("jagacy.", hashMap);
        bool = !(!a(this.a3, hashMap) && !bool);
        if (!bool && !utils.Util.isEmpty(this.a))
            throw new exception.JagacyException(15, "Could not read either property file from directory:\n" + this.a);
        if (paramProperties != null)
            a(null, hashMap, paramProperties);
        a(null, hashMap, System.getProperties());
        return hashMap;
    }



    private boolean a(String paramString, Map paramMap) {
        Properties properties = null;
        boolean bool = false;
        InputStream inputStream = null;
        String str = paramString.concat("properties");
        try {
            if (this.a5) {
                inputStream = config.JagacyProperties.class.getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = config.JagacyProperties.class.getClassLoader().getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
                if (inputStream == null)
                    inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
            } else {
                String str1 = this.a;
                if (str1 != null && (str1.startsWith("http://") || str1.startsWith("ftp://"))) {
                    if (!utils.Util.isEmpty(str1)) {
                        if (!str1.endsWith("/"))
                            str1 = String.valueOf(str1) + "/";
                        str = String.valueOf(str1) + str;
                    }
                    inputStream = (new URL(str)).openStream();
                } else {
                    if (!utils.Util.isEmpty(str1)) {
                        if (!str1.endsWith(File.separator))
                            str1 = String.valueOf(str1) + File.separator;
                        str = String.valueOf(str1) + str;
                    }
                    inputStream = new FileInputStream(str);
                }
            }
            properties = new Properties();
            properties.load(new BufferedInputStream(inputStream, 4096));
            a(paramString, paramMap, properties);
            bool = true;
        } catch (Exception exception) {

        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (Exception exception) {}
        }
        return bool;
    }

    private void a(String paramString, Map<String, String> paramMap, Properties paramProperties) {
        Enumeration<String> enumeration = paramProperties.keys();
        while (enumeration.hasMoreElements()) {
            String str3 = enumeration.nextElement();
            String str1 = str3;
            if (utils.Util.isEmpty(str1))
                continue;
            if (!str1.startsWith("jagacy.") && !str1.startsWith(this.a3) && (paramString == null || !str1.startsWith(paramString))) {
                if (paramString == null)
                    continue;
                str3 = String.valueOf(paramString) + str1;
            }
            String str2 = paramProperties.getProperty(str1);
            if (utils.Util.isEmpty(str2))
                continue;
            str2 = str2.trim();
            if (utils.Util.isEmpty(str2))
                continue;
            str2 = utils.Util.removeQuotes(str2);
            if ((!"jagacy.".equals(paramString) && String.valueOf(a(paramMap, String.valueOf(str3) + ".override")).equalsIgnoreCase("false")) || (!"jagacy".equals(paramString) && str3.endsWith(".override")))
                continue;
            paramMap.put(str3, str2);
        }
    }
    */

    private String loadProperties(Map paramMap, String paramString) {
        if (!paramString.startsWith("jagacy.") && !paramString.startsWith(this.sessionName))
        paramString = this.sessionName.concat(paramString);
        return (String)paramMap.get(paramString);
    }

    private String loadProperties(String paramString) { return loadProperties(this.a1, paramString); }
/*
    private int a(String paramString1, String paramString2) {
        int i = 0;
        try {
            byte b = 10;
            String str = paramString2.trim();
            if (str.startsWith("0x") && str.length() > 2) {
                b = 16;
                str = str.substring(2);
            }
            i = Integer.parseInt(str, b);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidFormatException("Could not parse int property " + paramString1 + "=" + paramString2);
        }
        if (i < 0)
            throw new InvalidFormatException("Invalid negative property " + paramString1 + "=" + i);
        return i;
    }


    private long do(String paramString1, String paramString2) {
        long l = 0L;
        try {
            byte b = 10;
            String str = paramString2.trim();
            if (str.startsWith("0x") && str.length() > 2) {
                b = 16;
                str = str.substring(2);
            }
            l = Long.parseLong(str, b);
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidFormatException("Could not parse int property " + paramString1 + "=" + paramString2);
        }
        if (l < 0L)
            throw new InvalidFormatException("Invalid negative property " + paramString1 + "=" + l);
        return l;
    }

    private int int(String paramString1, String paramString2) {
        int i = a(paramString1, paramString2.trim());
        if (i == 0)
            throw new InvalidFormatException("Invalid field property " + paramString1 + "=" + i);
        return i;
    }

    private int for(String paramString1, String paramString2) {
        int i = a(paramString1, paramString2.trim());
        if (i == 0)
            throw new InvalidFormatException("Invalid offset property " + paramString1 + "=" + i);
        return i;
    }

    private int new(String paramString1, String paramString2) {
        int i = a(paramString1, paramString2);
        if (paramString1.endsWith(".seconds"))
            i *= 1000;
        return i;
    }

    private boolean if(String paramString1, String paramString2) {
        boolean bool = false;
        paramString2 = paramString2.toLowerCase().trim();
        if (paramString2.equals("true")) {
            bool = true;
        } else if (paramString2.equals("false")) {
            bool = false;
        } else {
            throw new InvalidFormatException("Could not parse boolean property " + paramString1 + "=" + paramString2);
        }
        return bool;
    }

    BigInteger a() {
        BigInteger bigInteger = BigInteger.ZERO;
        if (this.a5) {
            bigInteger = this.a4.add(BigInteger.ONE);
        } else {
            File file = new File(String.valueOf(this.a2) + ".properties");
            if (file.exists()) {
                bigInteger = BigInteger.valueOf(file.length());
                bigInteger = bigInteger.shiftLeft(64);
                bigInteger = bigInteger.add(BigInteger.valueOf(file.lastModified()));
            }
        }
        this.a4 = bigInteger;
        return bigInteger;
    }

    String try(String paramString1, String paramString2) throws exception.JagacyException {
        String str = a(a(null), paramString1);
        return (str == null) ? paramString2 : str;
    }

    public String get(String paramString) throws exception.JagacyException {
        String str = a(paramString);
        if (str == null)
            throw new exception.JagacyException(5, "Could not find property " + paramString);
        return str;
    }

    public int getCardinal(String paramString) throws exception.JagacyException { return a(paramString, get(paramString)); }

    public long getLongCardinal(String paramString) throws exception.JagacyException { return do(paramString, get(paramString)); }

    public int getTimeout(String paramString) throws exception.JagacyException { return new(paramString, get(paramString)); }

    public int getField(String paramString) throws exception.JagacyException { return int(paramString, get(paramString)); }

    public int getOffset(String paramString) throws exception.JagacyException { return for(paramString, get(paramString)); }

    public boolean getBoolean(String paramString) throws exception.JagacyException { return if(paramString, get(paramString)); }

    public String get(String paramString1, String paramString2) {
        String str = a(paramString1);
        return (str == null) ? paramString2 : str;
    }

    public int getTimeout(String paramString, int paramInt) {
        String str = a(paramString);
        return (str == null) ? paramInt : new(paramString, str);
    }

    public int getField(String paramString, int paramInt) {
        String str = a(paramString);
        return (str == null) ? paramInt : int(paramString, str);
    }

    public int getOffset(String paramString, int paramInt) {
        String str = a(paramString);
        return (str == null) ? paramInt : for(paramString, str);
    }

    public int getCardinal(String paramString, int paramInt) {
        String str = a(paramString);
        return (str == null) ? paramInt : a(paramString, str);
    }

    public long getLongCardinal(String paramString, long paramLong) {
        String str = a(paramString);
        return (str == null) ? paramLong : do(paramString, str);
    }

    public boolean getBoolean(String paramString, boolean paramBoolean) {
        String str = a(paramString);
        return (str == null) ? paramBoolean : if(paramString, str);
    }

    public Key getKey(String paramString) throws exception.JagacyException {
        String str = get(paramString);
        if (str == null)
            str = "null";
        str = str.toUpperCase().trim();
        Key key = Key.find(str);
        if (key == null)
            throw new exception.JagacyException(9, "Could not find key for property " + paramString + "=" + str);
        return key;
    }

    public void set(String paramString1, String paramString2) {
        if (utils.Util.isEmpty(paramString1))
            throw new IllegalArgumentException("Empty key");
        if (paramString2 == null)
            throw new IllegalArgumentException("Null value");
        if (!paramString1.startsWith("jagacy.") && !paramString1.startsWith(this.a3))
        paramString1 = String.valueOf(this.a3) + paramString1;
        this.a1.put(paramString1, paramString2);
    }
 */
}
