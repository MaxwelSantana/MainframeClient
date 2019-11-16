package utils;

import exception.JagacyException;

public final class ByteBuffer {
    private byte[] a4;

    private int a1;

    private int a;

    private int a2;

    private int a3;

    public ByteBuffer(int paramInt) {
        a4 = new byte[paramInt];
        this.a = paramInt;
    }

    public void append(byte paramByte) {
        if (a1 == a4.length) {
            byte[] arrayOfByte = new byte[a1 + this.a];
            System.arraycopy(a4, 0, arrayOfByte, 0, a1);
            a4 = arrayOfByte;
        }
        a4[a1] = paramByte;
        a1++;
        a3++;
    }

    public void appendEscape(byte paramByte) {
        append(paramByte);
        if ((paramByte & 0xFF) == 255) {
            append(paramByte);
            a3--;
        }
    }

    public void append(byte[] paramArrayOfbyte) {
        if (a1 + paramArrayOfbyte.length > a4.length) {
            byte[] arrayOfByte = new byte[a1 + paramArrayOfbyte.length];
            System.arraycopy(a4, 0, arrayOfByte, 0, a1);
            a4 = arrayOfByte;
        }
        System.arraycopy(paramArrayOfbyte, 0, a4, a1, paramArrayOfbyte.length);
        a1 += paramArrayOfbyte.length;
        a3 += paramArrayOfbyte.length;
    }

    public int getLength() { return a1 - a2; }

    public int getSize() { return a3 - a2; }

    public void reset() {
        a1 = 0;
        a2 = 0;
        a3 = 0;
        a4 = new byte[this.a];
    }

    public void addStart(int paramInt) throws JagacyException {
        if (paramInt < 0)
            throw new IllegalArgumentException("Invalid offset " + paramInt);
        if (a2 + paramInt > a1)
        throw new JagacyException(16, "Buffer overrun");
        a2 += paramInt;
    }

    public void subtractLength(int paramInt) throws JagacyException {
        if (paramInt < 0)
            throw new IllegalArgumentException("Invalid offset " + paramInt);
        if (a1 - paramInt < a2)
        throw new JagacyException(16, "Buffer underrun");
        a1 -= paramInt;
    }

    public byte byteAt(int paramInt) throws JagacyException {
        if (a2 + paramInt >= a1)
        throw new JagacyException(16, "Buffer overrun");
        return a4[a2 + paramInt];
    }

    public void setByteAt(int paramInt, byte paramByte) throws JagacyException {
        if (a2 + paramInt >= a1)
        throw new JagacyException(16, "Buffer overrun");
        a4[a2 + paramInt] = paramByte;
    }

    public byte get() throws JagacyException {
        if (getLength() == 0)
            throw new JagacyException(16, "Buffer overrun");
        return a4[a2++];
    }

    public byte[] getBytes() {
        byte[] arrayOfByte = new byte[a1 - a2];
        System.arraycopy(a4, a2, arrayOfByte, 0, arrayOfByte.length);
        return arrayOfByte;
    }

    public ByteBuffer getBuffer(int paramInt) throws JagacyException {
        ByteBuffer byteBuffer = new ByteBuffer(this.a);
        while (paramInt-- > 0)
            byteBuffer.append(get());
        return byteBuffer;
    }
}
