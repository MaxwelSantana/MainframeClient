package helper;

import controller.ControllerF;
import utils.Util;

import java.util.Arrays;

//com.jagacy.framework.d
public abstract class MyHelperD extends FeatureHelper {
    protected int[] doa;

    protected boolean[] ifa;

    protected ControllerF a;

    public abstract int newa(int paramInt);

    protected static int a(int paramInt1, int paramInt2) {
        paramInt1 &= 0xFFFFFF;
        paramInt1 |= paramInt2 << 24;
        return paramInt1;
    }

    protected static int fora(int paramInt1, int paramInt2) {
        paramInt1 &= 0xFF00FFFF;
        paramInt1 |= paramInt2 << 16;
        return paramInt1;
    }

    protected MyHelperD(ControllerF paramf) {
        this.a = paramf;
        this.doa = new int[paramf.inta() * paramf.doa()];
        this.ifa = new boolean[this.doa.length];
    }

    protected MyHelperD() {}

    protected int doa() { return this.a.newa() * this.a.a(); }

    public boolean inta() { return !Util.isEmpty(this.doa); }

    public boolean ifa(int paramInt) { return isProtected(this.doa[paramInt]); }

    public boolean doa(int paramInt) { return !trya(paramInt) ? false : this.ifa[paramInt]; }

    public boolean fora(int paramInt) { return isNumeric(this.doa[paramInt]); }

    public boolean chara(int paramInt) { return !isHidden(this.doa[paramInt]); }

    public boolean inta(int paramInt) { return isBold(this.doa[paramInt]); }

    public boolean casea(int paramInt) { return isSelectable(this.doa[paramInt]); }

    public boolean trya(int paramInt) { return isFeature(this.doa[paramInt]); }

    public void bytea(int paramInt) { this.doa[paramInt] = 0; }

    public void ifa(int paramInt1, int paramInt2) { Arrays.fill(this.doa, paramInt1, paramInt2, 0); }

    public void fora() { Arrays.fill(this.doa, 0, this.doa.length, 0); }

    public void a(int paramInt, boolean paramBoolean) {
        if (!trya(paramInt))
        return;
        this.ifa[paramInt] = paramBoolean;
    }

    public void a() {
        byte b = 0;
        int i = this.doa.length;
        while (b < i) {
            a(b, false);
            b++;
        }
    }

    public void ifa() {
        byte b = 0;
        int i = this.doa.length;
        while (b < i) {
            if (!trya(b) || ifa(b))
            return;
            this.ifa[b] = false;
            b++;
        }
    }

    public int a(int paramInt) { return this.doa[paramInt]; }

    public abstract void doa(int paramInt1, int paramInt2);
}