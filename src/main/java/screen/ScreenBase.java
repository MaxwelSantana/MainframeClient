package screen;

import controller.ControllerF;
import exception.JagacyException;

import java.util.Arrays;

public abstract class ScreenBase {
    protected byte[] doa;

    protected ControllerF a;

    protected CodePage fora;

    protected ScreenBase(ControllerF paramf) {
        this.a = paramf;
        this.doa = new byte[paramf.inta() * paramf.doa()];
    }

    protected ScreenBase() {}

    public void a(int paramInt, byte paramByte) throws JagacyException { this.doa[paramInt] = paramByte; }

    public void a(int paramInt1, int paramInt2, byte paramByte) { Arrays.fill(this.doa, paramInt1, paramInt2, paramByte); }

    public byte a(int paramInt) { return this.doa[paramInt]; }

    public char ifa(int paramInt) throws JagacyException { return this.fora.a(this.doa[paramInt]); }

    public void doa(int paramInt) {
        if (paramInt < 0 || paramInt >= this.doa.length)
        return;
        this.doa[paramInt] = 0;
    }

    public void a() { Arrays.fill(this.doa, 0, this.doa.length, (byte)0); }

    public int a(int paramInt, char paramChar) throws JagacyException {
        this.doa[paramInt] = this.fora.a(String.valueOf(paramChar))[0];
        return 1;
    }

    public int doa() { return this.a.newa() * this.a.a(); }

    public int ifa() { return this.fora.a(); }
}
