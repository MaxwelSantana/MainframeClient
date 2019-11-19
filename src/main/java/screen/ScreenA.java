package screen;

import config.Config;
import controller.MyControllerH;
import exception.JagacyException;
import utils.Loggable;

import java.util.ArrayList;
import java.util.Arrays;

//com.jagacy.tn3270.a
public class ScreenA extends ScreenB {
    private int[] casea;

    private int gotoa;

    public ScreenA(MyControllerH paramh, Config paramd, Loggable paramLoggable) throws JagacyException {
        super(paramh, paramd, paramLoggable);
        this.casea = new int[this.doa.length];
        this.gotoa = 0;
    }

    private ScreenA() {}

    boolean inta(int paramInt) { return !(this.casea[paramInt] != 1 && this.casea[paramInt] != 20 && this.casea[paramInt] != 21); }

    private boolean fora() { return !(this.gotoa != 1 && this.gotoa != 20 && this.gotoa != 21); }

    private boolean newa(int paramInt) { return !(this.casea[paramInt] != 1 && this.casea[paramInt] != 0); }

    public void a(boolean paramBoolean1, boolean paramBoolean2) {
        if (paramBoolean1) {
            this.gotoa = paramBoolean2 ? 20 : 10;
        } else {
            this.gotoa = paramBoolean2 ? 1 : 0;
        }
    }

    public void ifa(boolean paramBoolean) {
        if (paramBoolean) {
            if (this.gotoa == 0 || this.gotoa == 11) {
                this.gotoa = 10;
            } else if (this.gotoa == 1 || this.gotoa == 21) {
                this.gotoa = 20;
            }
        } else if (fora()) {
            this.gotoa = 1;
        } else {
            this.gotoa = 0;
        }
    }

    public void a(boolean paramBoolean) {
        if (this.gotoa == 10 || this.gotoa == 20) {
            this.gotoa = paramBoolean ? 20 : 10;
        } else if (this.gotoa == 11 || this.gotoa == 21) {
            this.gotoa = paramBoolean ? 20 : 10;
        } else {
            this.gotoa = paramBoolean ? 1 : 0;
        }
    }

    public int a(int paramInt, char paramChar) throws JagacyException {
        if (this.doa[paramInt] == 15 || this.doa[paramInt] == 14)
        return 0;
        byte[] arrayOfByte = a(paramChar);
        if (arrayOfByte.length == 0)
            if (newa(paramInt)) {
            arrayOfByte = new byte[1];
        } else {
            arrayOfByte = new byte[2];
        }
        if (this.casea[paramInt] == 11 || this.casea[paramInt] == 21)
        throw new JagacyException(10, "Attempt to write char. at double right value");
        if (arrayOfByte.length == 1) {
            if (newa(paramInt)) {
                super.a(paramInt, arrayOfByte[0]);
            } else {
                if (this.casea[paramInt] == 10)
                    throw new JagacyException(10, "Attempt to write single char. in double subfield");
                    return 0;
            }
        } else {
            byte b1;
            for (b1 = 0; b1 < arrayOfByte.length; b1++) {
                int i = (paramInt + b1) % this.doa.length;
                if (this.casea[i] == 1)
                    return 0;
                if (this.casea[i] == 0)
                    throw new JagacyException(10, "Attempt to write double char. in single subfield");
                    if (this.casea[i] % 2 != b1 % 2)
                    throw new JagacyException(10, "Unbalanced DBCS subfield");
            }
            for (b1 = 0; b1 < arrayOfByte.length; b1++) {
                int i = (paramInt + b1) % this.doa.length;
                super.a(i, arrayOfByte[b1]);
            }
        }
        return arrayOfByte.length;
    }

    public void a(int paramInt, byte paramByte) throws JagacyException {
        super.a(paramInt, paramByte);
        this.casea[paramInt] = this.gotoa;
        switch (this.gotoa) {
            case 10:
                this.gotoa = 11;
                break;
            case 20:
                this.gotoa = 21;
                break;
            case 11:
                this.gotoa = 10;
                break;
            case 21:
                this.gotoa = 20;
                break;
        }
    }

    public void a(int paramInt1, int paramInt2, byte paramByte) {
        super.a(paramInt1, paramInt2, paramByte);
        //boolean bool = fora() ? true : false;
        int bool = fora() ? 1 : 0;
        Arrays.fill(this.casea, paramInt1, paramInt2, bool);
    }

    public char ifa(int paramInt) throws JagacyException {
        if (newa(paramInt))
        return (this.doa[paramInt] == 15 || this.doa[paramInt] == 14) ? (char)this.doa[paramInt] : super.ifa(paramInt);
        if (this.casea[paramInt] == 10 || this.casea[paramInt] == 20) {
            byte[] arrayOfByte = new byte[4];
            arrayOfByte[0] = 14;
            arrayOfByte[1] = this.doa[paramInt];
            arrayOfByte[2] = this.doa[(paramInt + 1) % this.doa.length];
            arrayOfByte[3] = 15;
            return this.fora.a(arrayOfByte)[0];
        }
        return ' ';
    }

    public void doa(int paramInt) {
        super.doa(paramInt);
        //boolean bool = fora() ? true : false;
        int bool = fora() ? 1 : 0;
        this.casea[paramInt] = bool;
    }

    public void fora(int paramInt) {
        super.fora(paramInt);
        //boolean bool = inta(paramInt) ? true : false;
        int bool = inta(paramInt) ? 1 : 0;
        this.casea[paramInt] = bool;
    }

    public void a(int paramInt1, int paramInt2) {
        Arrays.fill(this.doa, paramInt1, paramInt2, (byte)0);
        Arrays.fill(this.casea, paramInt1, paramInt2, 1);
    }

    public void a() {
        super.a();
        //boolean bool = fora() ? true : false;
        int bool = fora() ? 1 : 0;
        Arrays.fill(this.casea, 0, this.casea.length, bool);
    }

    public byte[] a(char paramChar) throws JagacyException {
        byte[] arrayOfByte = this.fora.a(String.valueOf(paramChar));
        if (arrayOfByte.length == 4) {
            byte[] arrayOfByte1 = new byte[2];
            arrayOfByte1[0] = arrayOfByte[1];
            arrayOfByte1[1] = arrayOfByte[2];
            arrayOfByte = arrayOfByte1;
        }
        return arrayOfByte;
    }

    public boolean trya(int paramInt) { return !newa(paramInt); }

    public byte[][] ifa(int paramInt1, int paramInt2) {
        ArrayList<byte[]> arrayList = new ArrayList();
        for (byte b1 = 0; b1 < paramInt2; b1++) {
            if (newa(paramInt1)) {
                arrayList.add(new byte[] { this.doa[paramInt1] });
            } else {
                byte[] arrayOfByte = new byte[2];
                arrayOfByte[0] = this.doa[paramInt1];
                arrayOfByte[1] = this.doa[++paramInt1 % this.doa.length];
                b1++;
                arrayList.add(arrayOfByte);
            }
            paramInt1 = ++paramInt1 % this.doa.length;
        }
        return arrayList.toArray(new byte[0][0]);
    }

    public Object clone() {
        ScreenA a1 = new ScreenA();
        a1.a = this.a;
        a1.doa = (byte[])this.doa.clone();
        a1.fora = this.fora;
        a1.casea = (int[])this.casea.clone();
        a1.gotoa = this.gotoa;
        return a1;
    }
}
