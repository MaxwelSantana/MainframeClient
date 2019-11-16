package utils;

public final class Util {
    public static boolean isEmpty(String paramString) { return !(paramString != null && !paramString.equals("")); }

    public static boolean isEmpty(byte[] paramArrayOfbyte) {
        for (byte b = 0; b < paramArrayOfbyte.length; b++) {
            if (paramArrayOfbyte[b] != 0)
                return false;
        }
        return true;
    }

    public static boolean isEmpty(short[] paramArrayOfshort) {
        for (byte b = 0; b < paramArrayOfshort.length; b++) {
            if (paramArrayOfshort[b] != 0)
                return false;
        }
        return true;
    }

    public static boolean isEmpty(int[] paramArrayOfint) {
        for (byte b = 0; b < paramArrayOfint.length; b++) {
            if (paramArrayOfint[b] != 0)
                return false;
        }
        return true;
    }
/*
    public static String toHex(byte paramByte) {
        null = "0x";
        String str = Integer.toHexString(paramByte & 0xFF);
        if (str.length() < 2)
            null = String.valueOf(null) + "0";
        return String.valueOf(null) + str;
    }

    public static String toHex2(byte paramByte) {
        null = "";
        String str = Integer.toHexString(paramByte & 0xFF);
        if (str.length() < 2)
            null = String.valueOf(null) + "0";
        return String.valueOf(null) + str;
    }



    public static String toHex(byte[] paramArrayOfbyte) {
        String str = "";
        for (byte b = 0; b < paramArrayOfbyte.length; b++)
            str = String.valueOf(str) + toHex2(paramArrayOfbyte[b]);
        return str;
    }
 */

    public static byte[] toByteArray(String paramString) {
        byte[] arrayOfByte = new byte[paramString.length()];
        char[] arrayOfChar = paramString.toCharArray();
        for (byte b = 0; b != arrayOfChar.length; b++)
            arrayOfByte[b] = (byte)arrayOfChar[b];
        return arrayOfByte;
    }

    public static byte[] toByteArrayFromHex(String paramString) {
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        byte b = 0;
        int i = paramString.length();
        while (b < i) {
            arrayOfByte[b / 2] = (byte)Integer.parseInt(String.valueOf(paramString.charAt(b) + paramString.charAt(b + 1)), 16);
            b += 2;
        }
        return arrayOfByte;
    }

    public static boolean find(byte paramByte, byte[] paramArrayOfbyte) {
        for (byte b = 0; b < paramArrayOfbyte.length; b++) {
            if (paramArrayOfbyte[b] == paramByte)
                return true;
        }
        return false;
    }

    public static int indexOf(byte paramByte, byte[] paramArrayOfbyte) {
        for (byte b = 0; b < paramArrayOfbyte.length; b++) {
            if (paramArrayOfbyte[b] == paramByte)
                return b;
        }
        return -1;
    }

    public static String concat(String paramString1, String paramString2) {
        if (!paramString1.endsWith("."))
            paramString1 = String.valueOf(paramString1) + ".";
        return paramString1 = String.valueOf(paramString1) + paramString2;
    }

    public static String rightTrim(String paramString) {
        char[] arrayOfChar1 = paramString.toCharArray();
        int i = arrayOfChar1.length;
        for (int j = arrayOfChar1.length - 1; j >= 0 && (Character.isWhitespace(arrayOfChar1[j]) || arrayOfChar1[j] == '\000'); j--)
            i--;
        char[] arrayOfChar2 = new char[i];
        System.arraycopy(arrayOfChar1, 0, arrayOfChar2, 0, i);
        return new String(arrayOfChar2);
    }

    public static String rightTrim_(String paramString) {
        char[] arrayOfChar1 = paramString.toCharArray();
        int i = arrayOfChar1.length;
        for (int j = arrayOfChar1.length - 1; j >= 0 && (Character.isWhitespace(arrayOfChar1[j]) || arrayOfChar1[j] == '\000' || arrayOfChar1[j] == '_'); j--)
            i--;
        char[] arrayOfChar2 = new char[i];
        System.arraycopy(arrayOfChar1, 0, arrayOfChar2, 0, i);
        return new String(arrayOfChar2);
    }

    public static String removeQuotes(String paramString) {
        if (paramString.endsWith("\""))
            paramString = paramString.substring(0, paramString.length() - 1);
        if (paramString.startsWith("\""))
            paramString = paramString.substring(1);
        return paramString;
    }
}
