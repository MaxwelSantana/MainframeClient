package controller;

import config.JagacyProperties;
import exception.JagacyException;
import conduit.Conduit3270;
import helper.MyHelperG;
import screen.ScreenA;
import screen.ScreenB;
import screen.ScreenBase;
import utils.IController;
import utils.Key;
import utils.Loggable;
import utils.TerminalC;

import java.util.Arrays;
import java.util.LinkedList;

//com.jagacy.tn3270.h
public class MyControllerH extends ControllerF implements IController {

    private static final byte[] bk = new byte[256];
    private static final byte[] bt = new byte[Key.MAX_ID];
    private int bf;
    private int bb;
    private MyHelperG bd;
    private ScreenBase bh;
    private ScreenA bg;
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

    public MyControllerH(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException {
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
            str = "conduit.SslConduit3270";
            //str = "com.jagacy.tn3270.SslConduit3270";
        return str;
    }

    protected void initAuxClasses() throws JagacyException {
        this.br = false;
        this.bs = (Conduit3270)this.ba;
        this.bp = this.a7.getParam().getBoolean("showHiddenFields", false);
        this.bd = new MyHelperG(this);
        if (this.a7.getParam("jagacy.dbcs", false)) {
            this.bh = this.bg = new ScreenA(this, this.a7, this.aZ);
            if ((this.bs.getTerminalFlags() & TerminalC.elsea) == 0) {
                this.bc = B;
            } else {
                this.bc = z;
            }
        } else {
            this.bh = new ScreenB(this, this.a7, this.aZ);
            if ((this.bs.getTerminalFlags() & TerminalC.elsea) == 0) {
                this.bc = f;
            } else {
                this.bc = d;
            }
        }
        this.aX = true;
        a(this.bh, this.bd);
        this.bm = this.aY;
        this.bj = this.bn = -1;
    }

    protected char[] ifa(int paramInt1, int paramInt2) throws JagacyException {
        return a(paramInt1, paramInt2, false);
    }

    public synchronized char[] a(int paramInt1, int paramInt2, boolean paramBoolean) throws JagacyException {
        char[] arrayOfChar = new char[paramInt2];
        if (paramInt2 == 0)
            return arrayOfChar;
        boolean bool = false;
        boolean bool1 = false;
        if (this.a9.inta() && !this.a9.trya(paramInt1)) {
            int[] arrayOfInt = { paramInt1 };
            this.bd.bytea(arrayOfInt);
            bool = this.a9.chara(arrayOfInt[0]) ? false : true;
            bool1 = this.a9.ifa(arrayOfInt[0]);
        }
        int i = this.a1.doa();
        for (int b1 = 0; b1 < paramInt2; b1++) {
            byte b2 = this.a1.a(paramInt1);
            if (this.a9.trya(paramInt1)) {
                bool = this.a9.chara(paramInt1) ? false : true;
                bool1 = this.a9.ifa(paramInt1);
                arrayOfChar[b1] = ' ';
            } else if (b2 == 15 && !bool1) {
                arrayOfChar[b1] = '>';
            } else if (b2 == 14 && !bool1) {
                arrayOfChar[b1] = '<';
            } else if (b2 == 0 || b2 == 64 || b2 == 12 || b2 == 13 || b2 == 21 || b2 == 25 || (b2 & 0xFF) == -1 || b2 == 15 || b2 == 14) {
                arrayOfChar[b1] = ' ';
            } else if (bool && !this.bp && !paramBoolean) {
                arrayOfChar[b1] = ' ';
            } else if (b2 == 63) {
                arrayOfChar[b1] = '�';
            } else if (b2 == 28) {
                arrayOfChar[b1] = '?';
            } else if (b2 == 30) {
                arrayOfChar[b1] = '?';
            } else {
                arrayOfChar[b1] = this.a1.ifa(paramInt1);
            }
            paramInt1 = ++paramInt1 % i;
        }
        return arrayOfChar;
    }

    protected int a(int paramInt1, int paramInt2, String paramString) throws JagacyException {
        return 0;
    }

    protected void doa(String paramString) throws JagacyException {

    }
}
