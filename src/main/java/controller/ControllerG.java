package controller;

import config.Config;
import config.JagacyProperties;
import exception.JagacyException;
import conduit.AbstractConduit;
import helper.MyHelperD;
import screen.ScreenBase;
import utils.*;

//com.jagacy.framework.g.class
public abstract class ControllerG {

    private int a6 = 25;
    private long a5;
    private long a4;
    private long aV;
    private boolean a0;
    protected Loggable aZ;
    protected Config a7;
    protected AbstractConduit ba;
    protected int aY;
    protected boolean aX;
    protected long a3;
    protected long a2;
    protected long aW;
    protected int aU;
    protected MyHelperD a9;
    protected ScreenBase a1;
    private boolean a8;

    protected ControllerG(JagacyProperties paramJagacyProperties, Loggable paramLoggable) {
        this.aZ = paramLoggable;
        this.a7 = new Config(paramJagacyProperties);
        this.a8 = chara();
    }

    protected void a(ScreenBase parame, MyHelperD paramd) {
        this.a1 = parame;
        this.a9 = paramd;
        this.aY = 0;
        this.a3 = 0L;
        this.a5 = 0L;
        this.a2 = 0L;
        this.a4 = 0L;
        this.aW = 0L;
        this.aV = 0L;
        this.a0 = true;
    }

    protected abstract boolean chara();

    protected abstract String getConduitClass();

    public int newa() { return (this.ba == null) ? TerminalC.maxWidth : this.ba.getWidth(); }

    public int a() { return (this.ba == null) ? TerminalC.maxHeight : this.ba.getHeight(); }

    public int inta() { return (this.ba == null) ? TerminalC.maxWidth : this.ba.getMaxWidth(); }

    public int doa() { return (this.ba == null) ? TerminalC.maxHeight : this.ba.getMaxHeight(); }

    protected abstract void initAuxClasses() throws JagacyException;

    public synchronized void openController() throws JagacyException {
        if (this.a0)
            throw new JagacyException(3, "Controller is already open");
        if (this.ba != null) {
            this.ba.abort();
            this.ba = null;
        }
        this.aZ.watch("Opening controller");
        String str = this.a7.getParam("jagacy.conduit.class", getConduitClass()).trim();
        try {
            this.ba = (AbstractConduit)Class.forName(str).newInstance();
        } catch (Exception exception) {
            throw new JagacyException(exception);
        }
        this.ba.setCfg(this.a7);
        this.ba.setLogger(this.aZ);
        this.ba.open();
        this.aU = this.ba.getTerminalFlags();
        initAuxClasses();
        this.a0 = true;
    }

    protected abstract char[] ifa(int paramInt1, int paramInt2) throws JagacyException;

    public synchronized char[] a(int paramInt1, int paramInt2) throws JagacyException {
        checkControllerIsOpen();
        if (paramInt1 < 0 || paramInt1 >= this.a1.doa())
            throw new IllegalArgumentException("Invalid position: " + paramInt1);
        if (paramInt2 < 0 || paramInt1 + paramInt2 > this.a1.doa())
            throw new IllegalArgumentException("Invalid length: " + paramInt2);
        return ifa(paramInt1, paramInt2);
    }

    protected void checkControllerIsOpen() throws JagacyException {
        if (!this.a0)
            throw new JagacyException(3, "Controller is closed");
    }

    public JagacyProperties b() { return this.a7.getProperties(); }

    public void fora(int paramInt) throws JagacyException { ifa(paramInt, 0, null); }

    public synchronized void ifa(int paramInt1, int paramInt2, String paramString) throws JagacyException {
        checkControllerIsOpen();
        int i = a(paramInt1, paramInt2, paramString);
        if (i > 0 && this.aZ.isWatchEnabled()) {
            this.aZ.watch("Received " + i + " message(s)");
            this.aZ.watch("Received " + (this.aW - this.aV) + " update(s)");
            this.aZ.watch("Received " + (this.a3 - this.a5) + " cursor position(s)");
        }
        if (i > 0)
            doa("After update(s)");
    }

    protected abstract int a(int paramInt1, int paramInt2, String paramString) throws JagacyException;

    protected abstract void doa(String paramString) throws JagacyException;

    protected abstract boolean a(Key paramKey);

    protected abstract boolean ifa(Key paramKey) throws JagacyException;

    protected abstract void fora(Key paramKey) throws JagacyException;

    public synchronized void doa(Key paramKey) throws JagacyException {
        if (this.a8 && paramKey.isWaitKey() && this.a6-- <= 0) {
            this.aZ.fatal("Evaluation 25 page limit exceeded");
            throw new JagacyException(3, "exception.controller.eval_exceeded", true);
        }
        checkControllerIsOpen();
        if (a(paramKey))
            throw new IllegalArgumentException("Unsupported key " + paramKey);
        this.aZ.watch("Sending " + paramKey);
        if (ifa(paramKey))
        return;
        fora(paramKey);
        doa("Before update(s)");
    }

    public synchronized void casea() throws JagacyException {
        try {
            if (this.a0) {
                this.aZ.watch("Closing controller");
                if (this.ba != null)
                    this.ba.close();
            }
        } finally {
            this.a0 = false;
            this.aX = false;
            this.ba = null;
        }
    }

    public synchronized void voida() throws JagacyException {
        checkControllerIsOpen();
        if (!this.aX) {
            this.aV = this.aW;
            this.a5 = this.a3;
        }
    }

    public synchronized boolean ifa() throws JagacyException {
        boolean bool;
        checkControllerIsOpen();
        if (this.aX) {
            bool = false;
        } else if (this.aV == this.aW && this.a5 == this.a3) {
            bool = false;
        } else {
            bool = true;
        }
        return bool;
    }

    protected void a(ByteBuffer paramByteBuffer) throws JagacyException {
        this.aX = true;
        this.a4 = this.a2;
        this.a5 = this.a3;
        this.aV = this.aW;
        this.ba.send(paramByteBuffer);
    }

    public synchronized void a(int paramInt) throws JagacyException {
        checkControllerIsOpen();
        if (this.aX)
            throw new JagacyException(11, "exception.keyboard.locked", true);
        if (paramInt < 0 || paramInt >= this.a1.doa())
        throw new IllegalArgumentException("Invalid cursor position: " + paramInt);
        this.aY = paramInt;
    }

    public synchronized void a(String paramString) throws JagacyException {
        checkControllerIsOpen();
        if (this.aX)
            throw new JagacyException(11, "exception.keyboard.locked", true);
        if (Util.isEmpty(paramString))
            return;
        ifa(paramString);
    }

    protected abstract void ifa(String paramString) throws JagacyException;
}
