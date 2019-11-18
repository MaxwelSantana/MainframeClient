package screen;

import exception.JagacyException;

import java.io.UnsupportedEncodingException;

//com.jagacy.framework.g
public class CodePage {
    private String a;

    public CodePage(String paramString) throws JagacyException {
        this.a = paramString.toUpperCase();
        a(" ");
    }

    public char a(byte paramByte) throws JagacyException {
        try {
            return (new String(new byte[] { paramByte }, this.a)).charAt(0);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new JagacyException(8, "Codepage " + this.a + " not supported");
        }
    }

    public char[] a(byte[] paramArrayOfbyte) throws JagacyException {
        try {
            return (new String(paramArrayOfbyte, this.a)).toCharArray();
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new JagacyException(8, "Codepage " + this.a + " not supported");
        }
    }

    public byte[] a(String paramString) throws JagacyException {
        try {
            return paramString.getBytes(this.a);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new JagacyException(8, "Codepage " + this.a + " not supported");
        }
    }

    public int a() { return Integer.parseInt(this.a.substring(2)); }

    public String toString() { return this.a; }
}
