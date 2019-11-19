package controller;

import config.JagacyProperties;
import exception.JagacyException;
import conduit.Conduit3270;
import helper.FeatureHelper;
import helper.MyHelperG;
import screen.ScreenA;
import screen.ScreenB;
import screen.ScreenBase;
import utils.*;

import java.util.Arrays;
import java.util.LinkedList;

//com.jagacy.tn3270.h
public class MyControllerH extends ControllerG implements IController {

    private static final byte[] bk = new byte[256];
    private static final byte[] bt = new byte[Key.MAX_ID];
    private int bf;
    private int bb;
    private MyHelperG bd;
    private ScreenB bh;
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
        byte b1 = 0;
        ByteBuffer byteBuffer = this.ba.receive(paramInt1);
        if (!this.bs.isEmulating()) {
            int i = byteBuffer.getLength();
            if (i > 0)
                this.aZ.watch("NVT");
            this.aX = false;
            for (byte b2 = 0; b2 < i; b2++) {
                byte b3 = byteBuffer.get();
                b1++;
                if (b3 == 13) {
                    this.aY = this.aY / newa() * newa();
                    this.a3++;
                } else if (b3 == 10) {
                    this.aY = (this.aY + newa()) % this.a1.doa();
                    this.a3++;
                } else {
                    this.a1.a(this.aY++, b3);
                    this.aW++;
                }
            }
            this.bm = this.aY;
        } else if (byteBuffer.getLength() > 0) {
            boolean bool1 = false;
            b1++;
            if (this.bs.isSscpLu()) {
                this.aZ.watch("SSCP-LU");
                this.aX = false;
                this.aY = this.bm = 0;
                this.bj = this.bn = -1;
            }
            boolean bool2 = true;
            byte b2 = byteBuffer.byteAt(0);
            switch (b2) {
                case 39:
                    byteBuffer.get();
                    byteBuffer.addStart(1);
                    break;
                case -15:
                case 1:
                    byteBuffer.get();
                    this.aZ.watch("Write command");
                    bool1 = true;
                    break;
                case 13:
                case 126:
                    byteBuffer.get();
                    this.br = true;
                    this.aZ.watch("Erase Write Alt command");
                    j();
                    bool1 = true;
                    break;
                case -11:
                case 5:
                    byteBuffer.get();
                    this.br = false;
                    this.aZ.watch("Erase Write command");
                    j();
                    bool1 = true;
                    break;
                case 15:
                case 111:
                    byteBuffer.get();
                    this.aZ.watch("Erase Unprotected command");
                    fora(0, this.a1.doa());
                    this.a9.ifa();
                    this.aX = false;
                    g();
                    this.bm = this.aY;
                    this.a3++;
                    break;
                case -14:
                case 2:
                    byteBuffer.get();
                    this.aZ.watch("Read Buffer command");
                    doa((byte)96);
                    break;
                case -10:
                case 6:
                    byteBuffer.get();
                    this.aZ.watch("Read Modified command");
                    fora((byte)96);
                    break;
                case 110:
                    byteBuffer.get();
                    this.aZ.watch("Read Modified All command");
                    fora((byte)96);
                    break;
                case -13:
                case 17:
                    byteBuffer.get();
                    this.aZ.watch("Write structured field command");
                    inta(byteBuffer);
                    return b1;
                case 3:
                    byteBuffer.get();
                    this.aZ.watch("NOP command");
                    return b1;
                default:
                    if (!this.bs.isSscpLu()) {
                        byteBuffer.get();
                        this.aZ.watch("Invalid command " + Util.toHex(b2));
                        break;
                    }
                    bool2 = false;
                    this.br = true;
                    j();
                    bool1 = true;
                    break;
            }
            if (!bool1) {
                if (byteBuffer.getLength() > 0)
                    this.aZ.watch("Extra data at end of command");
            } else if (byteBuffer.getLength() > 0) {
                if (bool2)
                    doa(byteBuffer);
                fora(byteBuffer);
            }
        }
        return b1;
    }

    private void j() {
        this.a1.a();
        this.a9.fora();
        this.aW++;
        this.aY = this.bm = 0;
        this.a3++;
    }

    public synchronized void g() throws JagacyException {
        int i;
        checkControllerIsOpen();
        if (this.aX)
            throw new JagacyException(11, "exception.keyboard.locked", true);
        int[] arrayOfInt = { -1 };
        this.aY = 0;
        int j = this.a1.doa();
        do {
            i = this.bd.ifa(arrayOfInt);
            if (i != 0)
                this.aY = arrayOfInt[0] + 1;
            if (this.aY >= j) {
                this.aY %= j;
                break;
            }
        } while (i != 0 && this.a9.newa(this.aY));
        if (this.a9.newa(this.aY))
        this.aY = 0;
    }


    public synchronized int ifa(int[] paramArrayOfint) throws JagacyException {
        checkControllerIsOpen();
        return this.bd.casea(paramArrayOfint);
    }

    private void fora(ByteBuffer paramByteBuffer) throws JagacyException {
        boolean bool = false;
        int i = this.aY;
        int j = 0;
        int k = this.a1.doa();
        while (paramByteBuffer.getLength() > 0) {
            byte b2;
            byte b1 = paramByteBuffer.get();
            if (b1 == 15) {
                if (this.bg != null) {
                    this.bg.ifa(false);
                    this.bg.a(i++, (byte)15);
                    i %= k;
                }
                continue;
            }
            if (b1 == 14) {
                if (this.bg != null) {
                    this.bg.ifa(false);
                    this.bg.a(i++, (byte)14);
                    this.bg.ifa(true);
                    i %= k;
                    continue;
                }
                throw new JagacyException(0, "exception.controller.dbcs", true);
            }
            if (b1 == 1 || b1 == 2)
                continue;
            if (b1 == 5) {
                this.aZ.watch("Tab order");
                boolean bool1 = true;
                while (i < k && (!this.a9.newa(i) || this.a9.a(i))) {
                    if (this.a9.newa(i)) {
                        bool1 = false;
                    } else if (bool && bool1) {
                        this.a1.doa(i);
                    }
                    i++;
                }
                if (i < k)
                    i++;
                i %= k;
                bool = false;
                this.aW++;
                continue;
            }
            bool = false;
            if (b1 == 29) {
                byte b3 = a(paramByteBuffer.get());
                this.bd.longa(i);
                this.bd.chara(i);
                a(b3, i);
                this.aZ.watch("Start Field order, pos=" + i + ", attr=" + Util.toHex(b3) + ", size=" + k);
                this.aW++;
                i = ++i % k;
                if (this.bg != null)
                    this.bg.a(true);
                continue;
            }
            if (b1 == 17) {
                int m = trya(paramByteBuffer);
                k = this.a1.doa();
                this.aZ.watch("Set Buffer Address order, old pos=" + i + ", new pos=" + m + ", size=" + k);
                i = m;
                continue;
            }
            if (b1 == 19) {
                this.aZ.watch("Insert Cursor order");
                this.aY = this.bm = i;
                this.a3++;
                continue;
            }
            if (b1 == 60) {
                this.aZ.watch("Repeat to address order");
                j = trya(paramByteBuffer);
                k = this.a1.doa();
                b2 = paramByteBuffer.get();
                if (b2 == 8) {
                    this.aZ.watch("GE Suborder");
                    paramByteBuffer.addStart(1);
                    b2 = 63;
                }
                if (j > i) {
                    this.a1.a(i, j, b2);
                    this.a9.ifa(i, j);
                } else {
                    this.a1.a(i, k, b2);
                    this.a9.ifa(i, k);
                    this.a1.a(0, j, b2);
                    this.a9.ifa(0, j);
                }
                this.aW++;
                i = j % k;
                continue;
            }
            if (b1 == 18) {
                this.aZ.watch("Erase Unprotected to Address order");
                j = trya(paramByteBuffer);
                k = this.a1.doa();
                fora(i, j);
                i = j % k;
                this.aW++;
                continue;
            }
            if (b1 == 41) {
                this.aZ.watch("SFE Order");
                this.bd.longa(i);
                this.bd.chara(i);
                byte b3 = 0;
                int m = paramByteBuffer.get() & 0xFF;
                for (byte b4 = 0; b4 < m; b4++) {
                    byte b5 = paramByteBuffer.get();
                    byte b6 = paramByteBuffer.get();
                    if ((b5 & 0xFF) == 192) {
                        b3 = a(b6);
                    } else if ((b5 & 0xFF) == 65) {
                        this.bd.a(i, b6);
                    } else if ((b5 & 0xFF) == 66) {
                        this.bd.ifa(i, b6);
                    } else if ((b5 & 0xFF) == 67) {
                        this.aZ.watch("DBCS Character Set");
                        if (this.bg != null)
                            this.bg.a(((b6 & 0xFF) == 248));
                        this.aW++;
                    } else if ((b5 & 0xFF) == 254) {
                        this.aZ.watch("DBCS Input Control");
                        if (this.bg != null)
                            this.bg.a(((b6 & 0xFF) == 1));
                        this.aW++;
                    } else if ((b5 & 0xFF) == 69) {
                        this.bd.doa(i, b6);
                    }
                }
                a(b3, i);
                this.aW++;
                i = ++i % k;
                continue;
            }
            if (b1 == 44) {
                this.aZ.watch("MF Order");
                int m = paramByteBuffer.get() & 0xFF;
                int n = -1;
                if (this.a9.newa(i)) {
                    for (byte b3 = 0; b3 < m; b3++) {
                        byte b4 = paramByteBuffer.get();
                        byte b5 = paramByteBuffer.get();
                        if ((b4 & 0xFF) == 192) {
                            n = a(b5) & 0xFF;
                        } else if ((b4 & 0xFF) == 65) {
                            this.bd.a(i, b5);
                            this.aW++;
                        } else if ((b4 & 0xFF) == 66) {
                            this.bd.ifa(i, b5);
                            this.aW++;
                        } else if ((b4 & 0xFF) == 67) {
                            this.aZ.watch("DBCS Character Set");
                            if (this.bg != null)
                                this.bg.a(((b5 & 0xFF) == 248));
                            this.aW++;
                        } else if ((b4 & 0xFF) == 254) {
                            this.aZ.watch("DBCS Input Control");
                            if (this.bg != null)
                                this.bg.a(((b5 & 0xFF) == 1));
                            this.aW++;
                        } else if ((b4 & 0xFF) == 69) {
                            this.bd.doa(i, b5);
                            this.aW++;
                        }
                    }
                    if (n != -1) {
                        a((byte)n, i);
                        this.aW++;
                        i = ++i % k;
                        continue;
                    }
                    if (m == 0)
                        i = ++i % k;
                    continue;
                }
                paramByteBuffer.addStart(2 * m);
                continue;
            }
            if (b1 == 40) {
                this.aZ.watch("SA Order");
                paramByteBuffer.addStart(2);
                continue;
            }
            if (b1 == 8) {
                this.aZ.watch("Main GE Order");
                paramByteBuffer.addStart(1);
                b2 = 63;
            } else {
                b2 = b1;
                bool = true;
            }
            if (this.bs.isSscpLu() && b2 == 21) {
                i = (i / newa() + 1) * newa() % this.a1.doa();
                this.a3++;
            } else {
                this.a9.trya(i);
                this.a1.a(i, b2);
            }
            this.aW++;
            i = ++i % k;
        }
        if (this.bs.isSscpLu())
            this.aY = this.bm = i;
    }

    private int trya(ByteBuffer paramByteBuffer) throws JagacyException {
        int i = 0;
        byte b1 = paramByteBuffer.get();
        byte b2 = paramByteBuffer.get();
        if ((b1 & 0xC0) == 0) {
            i = ((b1 & 0xFF) << 8) + (b2 & 0xFF);
        } else {
            byte b3 = bk[b1 & 0xFF];
            byte b4 = bk[b2 & 0xFF];
            if (b3 == -1 || b4 == -1) {
                i = ((b1 & 0x3F) << 6) + (b2 & 0x3F);
            } else {
                i = (b3 & 0xFF) * n.length + (b4 & 0xFF);
            }
        }
        if (i < 0 || i >= this.a1.doa()) {
            if (i >= 0 && !this.br && i < doa() * inta()) {
                this.aZ.watch("Auto switch to alt screen");
                this.br = true;
                return i;
            }
            this.aZ.watch("Invalid address " + i);
            i = 0;
        }
        return i;
    }

    protected void doa(String paramString) throws JagacyException {

    }

    private byte a(byte paramByte) {
        int i = bk[paramByte & 0xFF];
        if (i == -1) {
            i = paramByte & 0x3F | 0x40;
            if (((((i & 0xF) > 0) ? 1 : 0) & (((i & 0xF) < 10) ? 1 : 0)) != 0)
                i |= 0x80;
            if ((i & 0x3F) == 33)
                i &= 0x7F;
            if ((i & 0x3F) == 48)
                i |= 0x80;
        }
        return (byte)i;
    }

    private void doa(byte paramByte) throws JagacyException {
        ByteBuffer byteBuffer = new ByteBuffer(this.a1.doa() * 2);
        byteBuffer.appendEscape(paramByte);
        a(this.aY, byteBuffer);
        byte b1 = 0;
        int i = this.a1.doa();
        while (b1 < i) {
            if (this.a9.newa(b1)) {
                byteBuffer.appendEscape((byte)29);
                //byteBuffer.appendEscape(n[this.bd.elsea(b1) & 0x3F]);
            } else {
                byteBuffer.appendEscape(this.a1.a(b1));
            }
            b1++;
        }
        a(byteBuffer);
    }

    private void a(int paramInt, ByteBuffer paramByteBuffer) {
        int i = paramInt / n.length % n.length;
        paramByteBuffer.appendEscape(n[i]);
        i = paramInt % n.length;
        paramByteBuffer.appendEscape(n[i]);
    }

    private void fora(int paramInt1, int paramInt2) {
        boolean bool = false;
        if (!this.a9.inta()) {
            bool = true;
        } else if (!this.a9.newa(paramInt1)) {
            bool = FeatureHelper.isProtected(this.a9.inta(paramInt1)) ? false : true;
        }
        int i = this.a1.doa();
        while (paramInt1 < i || paramInt2 != 0) {
            paramInt1 %= i;
            if (this.a9.newa(paramInt1)) {
                bool = this.a9.a(paramInt1) ? false : true;
            } else if (bool) {
                this.bh.fora(paramInt1);
            }
            if (++paramInt1 == paramInt2)
                break;
        }
    }

    private void fora(byte paramByte) throws JagacyException {
        byte b1 = -3;
        byte b2 = -2;
        byte b3 = -1;
        boolean bool = false;
        ByteBuffer byteBuffer = new ByteBuffer(this.a1.doa() * 2);
        if ((!this.bs.isEmulating() || this.bs.isSscpLu()) && !this.a9.inta()) {
            for (int j = this.bj; j < this.bn; j++)
                byteBuffer.append(this.a1.a(j));
            a(byteBuffer);
            return;
        }
        byteBuffer.appendEscape(paramByte);
        a(this.aY, byteBuffer);
        byte b4 = -3;
        if (!this.a9.inta())
        b4 = -2;
        byte b5 = 0;
        byte b6 = 0;
        int i = this.a1.doa();
        while (b6 < i) {
            if (this.a9.newa(b6)) {
                b4 = -3;
                if (this.a9.ifa(b6))
                b4 = b6;
            } else if (b4 != -3) {
                b5 = this.a1.a(b6);
                if (b4 >= 0) {
                    byteBuffer.appendEscape((byte)17);
                    a((b4 + 1) % i, byteBuffer);
                    b4 = -1;
                }
                if (b5 != 0)
                    byteBuffer.appendEscape(b5);
            }
            b6++;
        }
        if (b4 >= -1) {
            b6 = 0;
            i = this.a1.doa();
            while (b6 < i && !this.a9.newa(b6)) {
                b5 = this.a1.a(b6);
                if (b4 >= 0) {
                    byteBuffer.appendEscape((byte)17);
                    a((b4 + 1) % i, byteBuffer);
                    b4 = -1;
                }
                if (b5 != 0)
                    byteBuffer.appendEscape(b5);
                b6++;
            }
        }
        a(byteBuffer);
    }

    private void doa(ByteBuffer paramByteBuffer) throws JagacyException {
        byte b1 = paramByteBuffer.get();
        byte b2 = bk[b1 & 0xFF];
        if (b2 == -1)
            b2 = b1;
        if ((b2 & 0x4) != 0) {
            this.aZ.watch("Alarm sounded");
            this.a2++;
        }
        if ((b2 & 0x2) != 0) {
            this.aZ.watch("Keyboard restored");
            this.aX = false;
        }
        if ((b2 & 0x1) != 0) {
            this.aZ.watch("All MDT reset");
            this.a9.a();
        }
    }

    private void newa(ByteBuffer paramByteBuffer) throws JagacyException {
        this.aZ.watch("Sending Query Reply");
        paramByteBuffer.append((byte)-120);
        for (byte b1 = 0; b1 < this.bc.length; b1++)
            a(this.bc[b1], paramByteBuffer);
        this.ba.send(paramByteBuffer);
        this.aX = true;
    }

    private void a(ByteBuffer paramByteBuffer, int paramInt) throws JagacyException {
        ByteBuffer byteBuffer = new ByteBuffer(256);
        byte b1 = 0;
        byte b2 = paramByteBuffer.get();
        byte b3 = paramByteBuffer.get();
        paramInt -= 2;
        switch (b3) {
            case 2:
                this.aZ.watch("Query");
                newa(byteBuffer);
                this.aX = true;
                paramByteBuffer.addStart(paramInt);
                return;
            case 3:
                this.aZ.watch("QueryList");
                if (b2 != -1) {
                    paramByteBuffer.addStart(paramInt);
                } else {
                    byte b5;
                    byte b4 = paramByteBuffer.get();
                    paramInt--;
                    byteBuffer.append((byte)-120);
                    switch (b4) {
                        case 0:
                            this.aZ.watch("List");
                            for (b5 = 0; b5 < paramInt; b5++) {
                                int i = Util.indexOf(paramByteBuffer.get(), this.bc);
                                if (i != -1) {
                                    a(this.bc[i], byteBuffer);
                                    b1++;
                                }
                            }
                            if (b1 <= 0)
                                a((short)-1, byteBuffer);
                            break;
                        case 64:
                            this.aZ.watch("Equivalent+List");
                            paramByteBuffer.addStart(paramInt);
                            for (b5 = 0; b5 < this.bc.length; b5++)
                                a(this.bc[b5], byteBuffer);
                            break;
                        case -128:
                            this.aZ.watch("All\n");
                            paramByteBuffer.addStart(paramInt);
                            for (b5 = 0; b5 < this.bc.length; b5++)
                                a(this.bc[b5], byteBuffer);
                            break;
                        default:
                            this.aZ.watch("Unknown Query List Type " + Util.toHex(b4));
                            paramByteBuffer.addStart(paramInt);
                            break;
                    }
                    this.ba.send(byteBuffer);
                    this.aX = true;
                }
                return;
            case 110:
                this.aZ.watch("ReadModifiedAll");
                if (b2 == 0)
                    fora((byte)97);
                paramByteBuffer.addStart(paramInt);
                return;
            case -14:
                this.aZ.watch("ReadBuffer");
                if (b2 == 0)
                    doa((byte)97);
                paramByteBuffer.addStart(paramInt);
                return;
            case -10:
                this.aZ.watch("ReadModified");
                if (b2 == 0)
                    fora((byte)97);
                paramByteBuffer.addStart(paramInt);
                return;
        }
        this.aZ.watch("Unknown WSF Command " + Util.toHex(b3));
        paramByteBuffer.addStart(paramInt);
    }

    private void ifa(ByteBuffer paramByteBuffer) throws JagacyException {
        byte b1 = paramByteBuffer.get();
        switch (b1) {
            case 0:
                this.br = false;
                this.aZ.watch("Default");
                j();
                return;
            case -128:
                this.br = true;
                this.aZ.watch("Alternate");
                j();
                return;
        }
        this.aZ.watch("Unknown type " + Util.toHex(b1));
    }

    private void inta(ByteBuffer paramByteBuffer) throws JagacyException {
        while (paramByteBuffer.getLength() > 0) {
            int i = ((paramByteBuffer.get() & 0xFF) << 8) + (paramByteBuffer.get() & 0xFF);
            if (i == 0) {
                i = paramByteBuffer.getLength();
            } else {
                i -= 2;
            }
            byte b1 = paramByteBuffer.get();
            i--;
            switch (b1) {
                case 1:
                    this.aZ.watch("ReadPartition");
                    a(paramByteBuffer, i);
                    continue;
                case 3:
                    this.aZ.watch("EraseReset");
                    ifa(paramByteBuffer);
                    continue;
                case 9:
                    this.aZ.watch("SetReplyMode");
                    paramByteBuffer.addStart(i);
                    continue;
                case 12:
                    this.aZ.watch("CreatePartition");
                    paramByteBuffer.addStart(i);
                    a(0);
                    continue;
                case 64:
                    this.aZ.watch("OutboundDS");
                    ifa(paramByteBuffer, i);
                    continue;
            }
            this.aZ.watch("Unsupported structured field command " + Util.toHex(b1));
            ByteBuffer byteBuffer = new ByteBuffer(3);
            byteBuffer.append((byte)-120);
            byteBuffer.append((byte)0);
            byteBuffer.append((byte)0);
            this.ba.send(byteBuffer);
            paramByteBuffer.addStart(i);
        }
    }

    private void ifa(ByteBuffer paramByteBuffer, int paramInt) throws JagacyException {
        byte b1 = paramByteBuffer.get();
        paramInt--;
        if (b1 != 0) {
            this.aZ.trace("Invalid partition " + Util.toHex(b1));
            paramByteBuffer.addStart(paramInt);
            return;
        }
        byte b2 = paramByteBuffer.get();
        paramInt--;
        switch (b2) {
            case -15:
                this.aZ.watch("Write");
                doa(paramByteBuffer);
                if (--paramInt > 0)
                    fora(paramByteBuffer.getBuffer(paramInt));
                return;
            case 126:
                this.br = true;
                this.aZ.watch("EraseWriteAlternate");
                j();
                doa(paramByteBuffer);
                if (--paramInt > 0)
                    fora(paramByteBuffer.getBuffer(paramInt));
                return;
            case -11:
                this.br = false;
                this.aZ.watch("EraseWrite");
                j();
                doa(paramByteBuffer);
                if (--paramInt > 0)
                    fora(paramByteBuffer.getBuffer(paramInt));
                return;
            case 111:
                this.aZ.watch("EraseAllUnprotected");
                fora(0, this.a1.doa());
                this.a9.ifa();
                this.aX = false;
                g();
                this.bm = this.aY;
                this.a3++;
                return;
        }
        this.aZ.watch("Unknown type " + Util.toHex(b2));
        paramByteBuffer.addStart(paramInt);
    }

    public boolean a(Key paramKey) { return !(paramKey.getId() < bt.length && bt[paramKey.getId()] != 0); }

    //TODO
    protected boolean ifa(Key paramKey) throws JagacyException {
        return false;
    }

    protected void fora(Key paramKey) throws JagacyException {

    }

    protected void ifa(String paramString) throws JagacyException {

    }
}
