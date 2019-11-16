package controller;

import config.JagacyProperties;
import exception.JagacyException;
import conduit.Conduit3270;
import utils.IController;
import utils.Key;
import utils.Loggable;

import java.util.Arrays;
import java.util.LinkedList;

//com.jagacy.tn3270.h
public class MyController extends Controller implements IController {

    private static final byte[] bk = new byte[256];
    private static final byte[] bt = new byte[Key.MAX_ID];
    private int bf;
    private int bb;
    //private g bd;
    //private b bh;
    //private a bg;
    private boolean be;
    private boolean bp;
    private int bq;
    private Conduit3270 bs;
    private boolean br;
    private int bm;
    private LinkedList bl = new LinkedList();
    private int bi = 0;
    //private f bo = new f();
    private byte[] bc;
    private int bj;
    private int bn;

    public MyController(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException {
        super(paramJagacyProperties, paramLoggable);
        paramJagacyProperties.set("jagacy.product", "3270");
        paramJagacyProperties.set("jagacy.version", "5.3.3");
        paramLoggable.info("Jagacy 3270 version: 5.3.3");
        paramLoggable.info("Java version: " + System.getProperty("java.version"));
        m();
        String str1 = paramJagacyProperties.get("jagacy.sysreq", "abort output");
        str1 = str1.toLowerCase();
        if (str1.equals("interrupt")) {
            this.bf = 0;
        } else if (str1.equals("abort output")) {
            this.bf = 1;
        } else if (str1.equals("test request")) {
            this.bf = 2;
        } else {
            throw new JagacyException("Invalid jagacy.sysreq property: " + str1);
        }
        String str2 = paramJagacyProperties.get("jagacy.attn", "break");
        str2 = str2.toLowerCase();
        if (str2.equals("interrupt")) {
            this.bb = 0;
        } else if (str2.equals("break")) {
            this.bb = 1;
        } else {
            throw new JagacyException("Invalid jagacy.attn property: " + str2);
        }
        this.be = paramJagacyProperties.getBoolean("numericAcceptAny", true);
        String str3 = this.a7.getParam("jagacy.autoSkip", "true2").trim();
        this.bq = 2;
        if (str3.equals("false")) {
            this.bq = 0;
        } else if (str3.equals("true")) {
            this.bq = 1;
        }
    }

    private void m() {
        synchronized (bt) {
            if (bt[Key.ENTER_ID] != 125) {
                bt[Key.ENTER_ID] = 125;
                bt[Key.PF1_ID] = -15;
                bt[Key.PF2_ID] = -14;
                bt[Key.PF3_ID] = -13;
                bt[Key.PF4_ID] = -12;
                bt[Key.PF5_ID] = -11;
                bt[Key.PF6_ID] = -10;
                bt[Key.PF7_ID] = -9;
                bt[Key.PF8_ID] = -8;
                bt[Key.PF9_ID] = -7;
                bt[Key.PF10_ID] = 122;
                bt[Key.PF11_ID] = 123;
                bt[Key.PF12_ID] = 124;
                bt[Key.PF13_ID] = -63;
                bt[Key.PF14_ID] = -62;
                bt[Key.PF15_ID] = -61;
                bt[Key.PF16_ID] = -60;
                bt[Key.PF17_ID] = -59;
                bt[Key.PF18_ID] = -58;
                bt[Key.PF19_ID] = -57;
                bt[Key.PF20_ID] = -56;
                bt[Key.PF21_ID] = -55;
                bt[Key.PF22_ID] = 74;
                bt[Key.PF23_ID] = 75;
                bt[Key.PF24_ID] = 76;
                bt[Key.PA1_ID] = 108;
                bt[Key.PA2_ID] = 110;
                bt[Key.PA3_ID] = 107;
                bt[Key.CLEAR_ID] = 109;
                bt[Key.CURSOR_SELECT_ID] = 126;
                bt[Key.RESET_ID] = -1;
                bt[Key.ATTN_ID] = -1;
                bt[Key.SYSREQ_ID] = -1;
                Arrays.fill(bk, (byte)-1);
                for (byte b1 = 0; b1 < n.length; b1++)
                    bk[n[b1] & 0xFF] = (byte)b1;
            }
        }
    }

    protected boolean chara() {
        return false;
    }

    protected String getConduitClass() {
        String str = "conduit.Conduit3270";
        //String str = "com.jagacy.tn3270.Conduit3270";
        if (this.a7.getParam("jagacy.ssl", false))
            str = "conduit.Conduit3270";
            //str = "com.jagacy.tn3270.SslConduit3270";
        return str;
    }

    protected void e() throws JagacyException {

    }

    protected char[] ifa(int paramInt1, int paramInt2) throws JagacyException {
        return null;
        //return a(paramInt1, paramInt2, false);
    }

    protected int a(int paramInt1, int paramInt2, String paramString) throws JagacyException {
        return 0;
    }

    protected void doa(String paramString) throws JagacyException {

    }
}
