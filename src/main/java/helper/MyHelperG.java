package helper;

import controller.MyControllerH;

//com.jagacy.tn3270.g
public final class MyHelperG extends MyHelperD {
    public MyHelperG(MyControllerH paramh) { super(paramh); }

    private MyHelperG() {}

    public Object clone() {
        MyHelperG g1 = new MyHelperG();
        g1.a = this.a;
        g1.doa = (int[])this.doa.clone();
        g1.ifa = (boolean[])this.ifa.clone();
        return g1;
    }

    public int gotoa(int paramInt) {
        int i = 0;
        if (paramInt <= 0)
            return i;
        int j = doa();
        if (paramInt > j)
            paramInt = j;
        while (--paramInt >= 0) {
            i = this.doa[paramInt];
            if (isFeature(i))
                break;
            i = 0;
            paramInt--;
        }
        return i;
    }

    public void doa(int paramInt1, int paramInt2) {
        int i = 1;
        this.ifa[paramInt1] = false;
        i = a(i, 0);
        i = fora(i, 0);
        byte b1 = (byte)(paramInt2 & 0x3F);
        if ((b1 & 0x20 & 0xFF) != 0)
            i |= 0x2;
        if ((b1 & 0x10 & 0xFF) != 0)
            i |= 0x8;
        byte b2 = (byte)(b1 & 0xC);
        if ((b2 & 0xFF) == 12) {
            i |= 0x4;
        } else if ((b2 & 0xFF) == 8) {
            i |= 0x10;
            i |= 0x100;
        } else if ((b2 & 0xFF) == 4) {
            i |= 0x100;
        }
        //if ((b1 & true & 0xFF) != 0)
        //    this.ifa[paramInt1] = true;
        if (this.doa[paramInt1] != 0) {
            int j = this.doa[paramInt1];
            if (isBlink(j))
                i |= 0x40;
            if (isReverse(j))
                i |= 0x80;
            if (isUnderline(j))
                i |= 0x20;
            i = a(i, getForeground(j));
            i = fora(i, getBackground(j));
        }
        this.doa[paramInt1] = i;
    }

    private int a(int paramInt, int[] paramArrayOfint) {
        int i = 0;
        if (paramArrayOfint[0] <= 0) {
            paramArrayOfint[0] = -1;
            return i;
        }
        int j = doa();
        if (paramArrayOfint[0] > j)
            paramArrayOfint[0] = j;
        paramArrayOfint[0] = paramArrayOfint[0] - 1;
        while (paramArrayOfint[0] >= 0) {
            i = this.doa[paramArrayOfint[0]];
            if (isFeature(i) && (i & paramInt & 0xFFFF) != paramInt)
                break;
            i = 0;
            paramArrayOfint[0] = paramArrayOfint[0] - 1;
        }
        return i;
    }

    private int ifa(int paramInt, int[] paramArrayOfint) {
        int i = 0;
        int j = doa();
        if (paramArrayOfint[0] >= j - 1) {
            paramArrayOfint[0] = j;
            return i;
        }
        if (paramArrayOfint[0] < -1)
            paramArrayOfint[0] = -1;
        paramArrayOfint[0] = paramArrayOfint[0] + 1;
        while (paramArrayOfint[0] < j) {
            i = this.doa[paramArrayOfint[0]];
            if (isFeature(i) && (i & paramInt & 0xFFFF) != paramInt)
                break;
            i = 0;
            paramArrayOfint[0] = paramArrayOfint[0] + 1;
        }
        return i;
    }

    public int newa(int[] paramArrayOfint) { return a(65535, paramArrayOfint); }

    public int fora(int[] paramArrayOfint) { return ifa(65535, paramArrayOfint); }

    public int a(int[] paramArrayOfint) { return a(2, paramArrayOfint); }

    public int ifa(int[] paramArrayOfint) { return ifa(2, paramArrayOfint); }

    public int inta(int[] paramArrayOfint) {
        int i = 0;
        int j = doa();
        if (paramArrayOfint[0] >= j - 1) {
            paramArrayOfint[0] = j;
            return i;
        }
        if (paramArrayOfint[0] < -1)
            paramArrayOfint[0] = -1;
        paramArrayOfint[0] = paramArrayOfint[0] + 1;
        while (paramArrayOfint[0] < j) {
            i = this.doa[paramArrayOfint[0]];
            if (isFeature(i) && isProtected(i))
                break;
            i = 0;
            paramArrayOfint[0] = paramArrayOfint[0] + 1;
        }
        return i;
    }

    public int trya(int[] paramArrayOfint) {
        int i = ifa(paramArrayOfint);
        if (i == 0) {
            paramArrayOfint[0] = -1;
            i = ifa(paramArrayOfint);
        }
        if (i == 0)
            paramArrayOfint[0] = -1;
        return i;
    }

    public int doa(int[] paramArrayOfint) {
        int i = a(paramArrayOfint);
        if (i == 0) {
            paramArrayOfint[0] = doa();
            i = a(paramArrayOfint);
        }
        if (i == 0)
            paramArrayOfint[0] = -1;
        return i;
    }

    public int bytea(int[] paramArrayOfint) {
        boolean bool = (paramArrayOfint[0] >= 0 && paramArrayOfint[0] < doa() && trya(paramArrayOfint[0])) ? true : false;
        if (bool)
            return this.doa[paramArrayOfint[0]];
        int i = newa(paramArrayOfint);
        if (paramArrayOfint[0] == -1) {
            paramArrayOfint[0] = doa();
            i = newa(paramArrayOfint);
        }
        return i;
    }

    public int casea(int[] paramArrayOfint) {
        bytea(paramArrayOfint);
        return gotoa(paramArrayOfint[0]);
    }

    public int longa(int paramInt) {
        int i = 0;
        if (paramInt <= 0)
            return i;
        int j = doa();
        if (paramInt > j)
            paramInt = j;
        while (--paramInt >= 0) {
            i = this.doa[paramInt];
            if (isFeature(i))
                break;
            i = 0;
            paramInt--;
        }
        return i;
    }

    public void elsea(int paramInt) {
        this.doa[paramInt] = this.doa[paramInt] & 0xFFFFFFBF;
        this.doa[paramInt] = this.doa[paramInt] & 0xFFFFFF7F;
        this.doa[paramInt] = this.doa[paramInt] & 0xFFFFFFDF;
    }

    public void a(int paramInt, byte paramByte) {
        switch (paramByte) {
            case 0:
            elsea(paramInt);
                break;
            case -15:
                this.doa[paramInt] = this.doa[paramInt] | 0x40;
                break;
            case -14:
                this.doa[paramInt] = this.doa[paramInt] | 0x80;
                break;
            case -12:
                this.doa[paramInt] = this.doa[paramInt] | 0x20;
                break;
        }
    }

    public void voida(int paramInt) {
        this.doa[paramInt] = a(this.doa[paramInt], 0);
        this.doa[paramInt] = fora(this.doa[paramInt], 0);
    }

    public void ifa(int paramInt, byte paramByte) {
        switch (paramByte) {
            case -16:
            case -8:
                this.doa[paramInt] = a(this.doa[paramInt], 1);
                break;
            case -15:
                this.doa[paramInt] = a(this.doa[paramInt], 5);
                break;
            case -14:
                this.doa[paramInt] = a(this.doa[paramInt], 2);
                break;
            case -13:
                this.doa[paramInt] = a(this.doa[paramInt], 10);
                break;
            case -12:
                this.doa[paramInt] = a(this.doa[paramInt], 3);
                break;
            case -11:
                this.doa[paramInt] = a(this.doa[paramInt], 7);
                break;
            case -10:
                this.doa[paramInt] = a(this.doa[paramInt], 4);
                break;
            case -9:
            case -1:
                this.doa[paramInt] = a(this.doa[paramInt], 8);
                break;
            case -7:
                this.doa[paramInt] = a(this.doa[paramInt], 15);
                break;
            case -6:
                this.doa[paramInt] = a(this.doa[paramInt], 11);
                break;
            case -5:
                this.doa[paramInt] = a(this.doa[paramInt], 6);
                break;
            case -4:
                this.doa[paramInt] = a(this.doa[paramInt], 14);
                break;
            case -3:
                this.doa[paramInt] = a(this.doa[paramInt], 9);
                break;
            case -2:
                this.doa[paramInt] = a(this.doa[paramInt], 12);
                break;
            case 0:
                this.doa[paramInt] = a(this.doa[paramInt], 0);
                break;
        }
    }

    public void doa(int paramInt, byte paramByte) {
        switch (paramByte) {
            case -16:
            case -8:
                this.doa[paramInt] = fora(this.doa[paramInt], 1);
                break;
            case -15:
                this.doa[paramInt] = fora(this.doa[paramInt], 5);
                break;
            case -14:
                this.doa[paramInt] = fora(this.doa[paramInt], 2);
                break;
            case -13:
                this.doa[paramInt] = fora(this.doa[paramInt], 10);
                break;
            case -12:
                this.doa[paramInt] = fora(this.doa[paramInt], 3);
                break;
            case -11:
                this.doa[paramInt] = fora(this.doa[paramInt], 7);
                break;
            case -10:
                this.doa[paramInt] = fora(this.doa[paramInt], 4);
                break;
            case -9:
            case -1:
                this.doa[paramInt] = fora(this.doa[paramInt], 8);
                break;
            case -7:
                this.doa[paramInt] = fora(this.doa[paramInt], 15);
                break;
            case -6:
                this.doa[paramInt] = fora(this.doa[paramInt], 11);
                break;
            case -5:
                this.doa[paramInt] = fora(this.doa[paramInt], 6);
                break;
            case -4:
                this.doa[paramInt] = fora(this.doa[paramInt], 14);
                break;
            case -3:
                this.doa[paramInt] = fora(this.doa[paramInt], 9);
                break;
            case -2:
                this.doa[paramInt] = fora(this.doa[paramInt], 12);
                break;
            case 0:
                this.doa[paramInt] = fora(this.doa[paramInt], 0);
                break;
        }
    }
}
