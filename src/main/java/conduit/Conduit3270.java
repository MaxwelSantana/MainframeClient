package conduit;

import exception.JagacyException;
import utils.ByteBuffer;
import utils.Terminal;
import utils.Util;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Conduit3270 extends AbstractConduit {
    private boolean[] o;

    private boolean[] s;

    private boolean[] x;

    private boolean I;

    private int l;

    protected Socket mySocket;

    private String M;

    private boolean L;

    private boolean w;

    private static final byte[] m = new byte[] { -1, -6, 24, 1, -1, -16 };

    private static final byte[] u = new byte[] { -1, -6, 40, 8, 2, -1, -16 };

    private static final byte[] z = new byte[] { -1, -6, 46, 1, -1, -16 };

    private static final byte[] B = new byte[] { -1, -6, 40, 2, 4 };

    private static final byte[] n = new byte[] { -1, -6, 40, 3, 7 };

    private static final byte[] P = new byte[] { -1, -6, 40, 2, 6 };

    private static final byte[] k = new byte[] { -1, -6, 40, 3, 4 };

    protected Socket getSocket(InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception {
        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(paramInetAddress, paramInt1);
        socket.connect(inetSocketAddress, paramInt2);
        return socket;
    }

    protected Socket getSocket(Proxy paramProxy, InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception {
        Socket socket = new Socket(paramProxy);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(paramInetAddress, paramInt1);
        socket.connect(inetSocketAddress, paramInt2);
        return socket;
    }

    protected void reopen() throws JagacyException {}

    public void open() throws JagacyException {
        this.myTerminal = Terminal.s;
        init();
        boolean bool = this.myCfg.getParam("jagacy.ssl", false);
        int i = bool ? 992 : 23;
        String str = this.myCfg.getParam("jagacy.host").trim();
        i = this.myCfg.getParam("jagacy.port", i);
        int j = this.myCfg.getTimeout("jagacy.connect.timeout", 10000);
        Proxy proxy = null;
        if (this.myCfg.getParam("jagacy.useProxy", false)) {
            String str1 = this.myCfg.getParam("jagacy.proxy.host", "localhost");
            int i1 = this.myCfg.getParam("jagacy.proxy.port", 1080);
            this.myLogger.trace("Using proxy " + str1 + ":" + i1);
            InetSocketAddress inetSocketAddress = null;
            inetSocketAddress = new InetSocketAddress(str1, i1);
            proxy = new Proxy(Proxy.Type.SOCKS, inetSocketAddress);
        }
        //this.myLogger.trace("Opening " + str + ":" + i);
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(str);
        } catch (UnknownHostException unknownHostException) {
            throw new JagacyException(1, "exception.conduit.unknown_host", str, unknownHostException);
        }
        try {
            if (proxy != null) {
                this.mySocket = getSocket(proxy, inetAddress, i, j);
            } else {
                this.mySocket = getSocket(inetAddress, i, j);
            }
            this.mySocket.setKeepAlive(true);
            this.myInput = new BufferedInputStream(this.mySocket.getInputStream(), 8192);
            this.myOutput = new BufferedOutputStream(this.mySocket.getOutputStream(), 8192);
        } catch (Exception exception) {
            abort();
            throw new JagacyException(1, "exception.conduit.no_socket", String.valueOf(str) + ":" + i, exception);
        }
        try {
            if (!bool)
                this.mySocket.setOOBInline(true);
            if (this.myCfg.getParam("jagacy.tcp.nodelay", false))
                this.mySocket.setTcpNoDelay(true);
        } catch (SocketException socketException) {
            throw new JagacyException(socketException);
        }
    }

    void open(InputStream paramInputStream, OutputStream paramOutputStream) throws JagacyException {
        this.myTerminal = Terminal.s;
        init();
        this.myInput = new BufferedInputStream(paramInputStream, 8192);
        this.myOutput = new BufferedOutputStream(paramOutputStream, 8192);
    }

    protected void init() throws JagacyException {
        super.init();
        this.w = false;
        this.L = true;
        this.M = this.myCfg.getParam("deviceName", "").toUpperCase().trim();
        this.l = this.myCfg.getParam("jagacy.tn3270e.initialSeqNumber", 0);
        if (this.myCfg.getParam("jagacy.strictLU", false)) {
            if (this.M.length() > 8)
                throw new JagacyException(6, "Device name must be less than nine characters");
            if (!Util.isEmpty(this.M)) {
                if (!Character.isLetter(this.M.charAt(0)))
                    throw new JagacyException(6, "Device name must begin with a letter");
                byte b = 1;
                int i = this.M.length();
                while (b < i) {
                    char c = this.M.charAt(b);
                    if (!Character.isLetter(c) && !Character.isDigit(c))
                        throw new JagacyException(6, "Device name must be alphanumeric");
                    b++;
                }
            }
        }
        this.o = new boolean[256];
        this.s = new boolean[256];
        this.x = new boolean[256];
        this.s[0] = true;
        this.s[3] = true;
        this.s[24] = true;
        this.s[25] = true;
        this.s[46] = this.myCfg.getParam("jagacy.ssl", false);
        this.x[0] = true;
        this.x[0] = true;
        this.x[3] = true;
        this.x[25] = true;
        this.x[6] = true;
        this.I = false;
        if (this.myTerminal.toString().endsWith("-E")) {
            this.s[40] = true;
            this.x[40] = true;
        }
    }

    public int getTerminalFlags() { return this.myTerminal.getTerminalFlags(); }

    public boolean isEmulating() { return !(!this.o[0] && !this.o[25] && !this.o[40]); }

    public boolean isSscpLu() { return this.w; }

    public void send(ByteBuffer paramByteBuffer) throws JagacyException { send(paramByteBuffer, -1); }

    public void send(ByteBuffer paramByteBuffer, int paramInt) throws JagacyException {
        paramByteBuffer.append((byte)-1);
        paramByteBuffer.append((byte)-17);
        if (this.I) {
            ByteBuffer byteBuffer = new ByteBuffer(5);
            if (paramInt != -1) {
                byteBuffer.append((byte)2);
            } else if (this.w) {
                byteBuffer.append((byte)7);
            } else if (!isEmulating()) {
                byteBuffer.append((byte)5);
            } else {
                byteBuffer.append((byte)0);
            }
            byteBuffer.append((byte)0);
            if (paramInt == -1 && this.myCfg.getParam("jagacy.tn3270e.responses", false)) {
                if (this.l == this.myCfg.getParam("jagacy.tn3270e.initialSeqNumber", 0) && this.myCfg.getParam("jagacy.tn3270e.alwaysResponse", false)) {
                    byteBuffer.append((byte)2);
                } else {
                    byteBuffer.append((byte)1);
                }
            } else {
                byteBuffer.append((byte)0);
            }
            if (!this.L) {
                if (paramInt != -1) {
                    byteBuffer.appendEscape((byte)(paramInt >>> 8));
                    byteBuffer.appendEscape((byte)paramInt);
                } else {
                    byteBuffer.appendEscape((byte)(this.l >>> 8));
                    byteBuffer.appendEscape((byte)this.l);
                    this.l++;
                    if (this.l >= 32768)
                        this.l = this.myCfg.getParam("jagacy.tn3270e.initialSeqNumber", 0);
                }
            } else {
                byteBuffer.append((byte)0);
                byteBuffer.append((byte)0);
            }
            send(byteBuffer.getBytes());
        }
        send(paramByteBuffer.getBytes());
    }

    private final void a(int paramInt) throws JagacyException {
        if (this.mySocket != null)
            try {
                this.mySocket.setSoTimeout(paramInt);
            } catch (SocketException socketException) {
                abort();
                throw new JagacyException(1, "Cannot set timeout", socketException);
            }
    }

    protected int[] createReceiveState() { return new int[1]; }

    protected void resetTimeout() throws JagacyException { a(0); }

    protected boolean isInitialReceiveState(int[] paramArrayOfint) { return (paramArrayOfint[0] == 0); }

    protected boolean isValidReceivedData(int[] paramArrayOfint, byte paramByte) { return !(paramArrayOfint[0] == 2 && paramByte == -1); }

    protected boolean isReceiveDone(int[] paramArrayOfint, byte paramByte) {
        switch (paramArrayOfint[0]) {
            case 0:
            case 1:
            case 4:
                if (paramByte == -1) {
                    paramArrayOfint[0] = 2;
                } else if (paramArrayOfint[0] == 0) {
                    paramArrayOfint[0] = 1;
                }
                if (paramArrayOfint[0] == 1 && !isEmulating()) {
                    int i = 0;
                    try {
                        i = this.myInput.available();
                    } catch (IOException iOException) {}
                    if (i <= 0)
                        paramArrayOfint[0] = 5;
                }
                break;
            case 2:
                paramArrayOfint[0] = 5;
                if (paramByte == -1) {
                    paramArrayOfint[0] = 1;
                    break;
                }
                if (paramByte == -15) {
                    paramArrayOfint[0] = 5;
                    break;
                }
                if (paramByte == -6) {
                    paramArrayOfint[0] = 4;
                    break;
                }
                if ((paramByte & 0xFF) > 250) {
                    paramArrayOfint[0] = 3;
                    break;
                }
                if ((paramByte & 0xFF) < 239)
                    paramArrayOfint[0] = 1;
                break;
            case 3:
                paramArrayOfint[0] = 5;
                break;
        }
        return (paramArrayOfint[0] == 5);
    }

    public ByteBuffer receive(int paramInt) throws JagacyException {
        ByteBuffer byteBuffer = new ByteBuffer(1024);
        if (paramInt < 0)
            throw new IllegalArgumentException("Invalid timeout " + paramInt);
        if (paramInt == 0)
            paramInt = 1;
        a(paramInt);
        byteBuffer = receive(byteBuffer);
        if (byteBuffer.getLength() != 0)
            if (byteBuffer.byteAt(0) != -1) {
                if (isEmulating())
                    byteBuffer.subtractLength(2);
                this.I = (this.I && this.o[40]);
                if (this.I) {
                    byte b1 = byteBuffer.get();
                    if (b1 != 0 && b1 != 7) {
                        byteBuffer.reset();
                        return byteBuffer;
                    }
                    this.w = false;
                    if (b1 == 7)
                        this.w = true;
                    byteBuffer.addStart(1);
                    byte b2 = byteBuffer.get();
                    int i = byteBuffer.get() << 8;
                    i |= byteBuffer.get();
                    if (b2 == 2) {
                        ByteBuffer byteBuffer1 = new ByteBuffer(1);
                        byteBuffer1.append((byte)0);
                        send(byteBuffer1, i);
                    }
                }
            } else {
                byte[] arrayOfByte = byteBuffer.getBytes();
                if (arrayOfByte.length < 3) {
                    this.myLogger.trace("Short message");
                    byteBuffer.reset();
                } else {
                    ByteBuffer byteBuffer1 = new ByteBuffer(16);
                    byteBuffer1.append((byte)-1);
                    byte b1 = arrayOfByte[1];
                    byte b2 = arrayOfByte[2];
                    int i = arrayOfByte[2] & 0xFF;
                    if (b1 == -3) {
                        if (this.s[i]) {
                            byteBuffer1.append((byte)-5);
                        } else {
                            byteBuffer1.append((byte)-4);
                        }
                        byteBuffer1.append(b2);
                        this.o[i] = true;
                    } else if (b1 == -5) {
                        if (this.x[i]) {
                            byteBuffer1.append((byte)-3);
                        } else {
                            byteBuffer1.append((byte)-2);
                        }
                        byteBuffer1.append(b2);
                    } else if (b1 == -2) {
                        if (i == 40) {
                            byteBuffer1.append((byte)-4);
                            this.I = false;
                        } else if (this.s[i]) {
                            byteBuffer1.append((byte)-5);
                        } else {
                            byteBuffer1.append((byte)-4);
                        }
                        byteBuffer1.append(b2);
                        this.o[i] = true;
                    } else if (b1 == -4) {
                        if (this.x[i]) {
                            byteBuffer1.append((byte)-3);
                        } else {
                            if (b2 == 1) {
                                if (this.o[i])
                                    throw new JagacyException(1, "VTxxx not supported, please use Jagacy VT");
                                this.o[i] = true;
                            }
                            byteBuffer1.append((byte)-2);
                        }
                        byteBuffer1.append(b2);
                    } else if (Arrays.equals(arrayOfByte, m) && this.s[24]) {
                        byteBuffer1.append((byte)-6);
                        byteBuffer1.append((byte)24);
                        byteBuffer1.append((byte)0);
                        try {
                            byteBuffer1.append(ifa().getBytes("ASCII"));
                        } catch (UnsupportedEncodingException unsupportedEncodingException) {
                            abort();
                            throw new JagacyException(8, "ASCII not supported", unsupportedEncodingException);
                        }
                        byteBuffer1.append((byte)-1);
                        byteBuffer1.append((byte)-16);
                    } else if (Arrays.equals(arrayOfByte, u)) {
                        this.I = true;
                        byteBuffer1.append((byte)-6);
                        byteBuffer1.append((byte)40);
                        byteBuffer1.append((byte)2);
                        byteBuffer1.append((byte)7);
                        try {
                            byteBuffer1.append(ifa().getBytes("ASCII"));
                        } catch (UnsupportedEncodingException unsupportedEncodingException) {
                            abort();
                            throw new JagacyException(8, "ASCII not supported", unsupportedEncodingException);
                        }
                        if (!Util.isEmpty(this.M)) {
                            byteBuffer1.append((byte)1);
                            try {
                                byteBuffer1.append(this.M.getBytes("ASCII"));
                            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                                abort();
                                throw new JagacyException(8, "ASCII not supported", unsupportedEncodingException);
                            }
                        }
                        byteBuffer1.append((byte)-1);
                        byteBuffer1.append((byte)-16);
                    } else if (Arrays.equals(arrayOfByte, z)) {
                        reopen();
                    } else {
                        byte[] arrayOfByte1 = new byte[5];
                        System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, arrayOfByte1.length);
                        if (Arrays.equals(arrayOfByte1, B)) {
                            this.I = true;
                            byteBuffer1.append((byte)-6);
                            byteBuffer1.append((byte)40);
                            byteBuffer1.append((byte)3);
                            byteBuffer1.append((byte)7);
                            if (this.myCfg.getParam("jagacy.tn3270e.bindImage", false)) {
                                byteBuffer1.append((byte)0);
                                this.L = false;
                            }
                            if (this.myCfg.getParam("jagacy.tn3270e.responses", false)) {
                                byteBuffer1.append((byte)2);
                                this.L = false;
                            }
                            if (this.myCfg.getParam("jagacy.tn3270e.sysreq", false)) {
                                byteBuffer1.append((byte)4);
                                this.L = false;
                            }
                            byteBuffer1.append((byte)-1);
                            byteBuffer1.append((byte)-16);
                        } else if (Arrays.equals(arrayOfByte1, n)) {
                            this.I = true;
                            if (this.myCfg.getParam("jagacy.tn3270e.isFunctions", false)) {
                                byteBuffer1.append((byte)-6);
                                byteBuffer1.append((byte)40);
                                byteBuffer1.append((byte)3);
                                byteBuffer1.append((byte)4);
                                if (this.myCfg.getParam("jagacy.tn3270e.bindImage", false)) {
                                    byteBuffer1.append((byte)0);
                                    this.L = false;
                                }
                                if (this.myCfg.getParam("jagacy.tn3270e.responses", false)) {
                                    byteBuffer1.append((byte)2);
                                    this.L = false;
                                }
                                if (this.myCfg.getParam("jagacy.tn3270e.sysreq", false)) {
                                    byteBuffer1.append((byte)4);
                                    this.L = false;
                                }
                                byteBuffer1.append((byte)-1);
                                byteBuffer1.append((byte)-16);
                            } else if (arrayOfByte.length > arrayOfByte1.length + 2 && !this.myCfg.getParam("jagacy.tn3270e.bindImage", false) && !this.myCfg.getParam("jagacy.tn3270e.responses", false) && !this.myCfg.getParam("jagacy.tn3270e.sysreq", false)) {
                                byteBuffer1.append((byte)-6);
                                byteBuffer1.append((byte)40);
                                byteBuffer1.append((byte)3);
                                byteBuffer1.append((byte)7);
                                byteBuffer1.append((byte)-1);
                                byteBuffer1.append((byte)-16);
                            }
                        } else if (Arrays.equals(arrayOfByte1, P)) {
                            this.I = true;
                            if (this.myTerminal.toString().contains("3279")) {
                                String str = this.myTerminal.toString().replace('9', '8');
                                this.myTerminal = Terminal.getTerminal(str);
                                byteBuffer1.append((byte)-6);
                                byteBuffer1.append((byte)40);
                                byteBuffer1.append((byte)2);
                                byteBuffer1.append((byte)7);
                                try {
                                    byteBuffer1.append(ifa().getBytes("ASCII"));
                                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                                    abort();
                                    throw new JagacyException(8, "ASCII not supported", unsupportedEncodingException);
                                }
                                if (!Util.isEmpty(this.M)) {
                                    byteBuffer1.append((byte)1);
                                    try {
                                        byteBuffer1.append(this.M.getBytes("ASCII"));
                                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                                        abort();
                                        throw new JagacyException(8, "ASCII not supported", unsupportedEncodingException);
                                    }
                                }
                                byteBuffer1.append((byte)-1);
                                byteBuffer1.append((byte)-16);
                            } else {
                                abort();
                                throw new JagacyException(1, "exception.conduit.device_reject", true);
                            }
                        } else if (Arrays.equals(arrayOfByte1, k)) {
                            this.I = true;
                            if (arrayOfByte.length > arrayOfByte1.length + 2 && !this.myCfg.getParam("jagacy.tn3270e.bindImage", false) && !this.myCfg.getParam("jagacy.tn3270e.responses", false) && !this.myCfg.getParam("jagacy.tn3270e.sysreq", false)) {
                                this.myLogger.trace("Rejecting TN3270E");
                                byteBuffer1.append((byte)-2);
                                byteBuffer1.append((byte)40);
                                this.I = false;
                                this.o[40] = false;
                                this.s[40] = false;
                                this.x[40] = false;
                            } else {
                                this.I = true;
                                this.myLogger.trace("Is RFC2355");
                            }
                        } else {
                            this.myLogger.trace("Other options");
                        }
                    }
                    if (byteBuffer1.getLength() > 1)
                        send(byteBuffer1.getBytes());
                    byteBuffer.reset();
                }
            }
        return byteBuffer;
    }

    private String ifa() {
        String str = this.myTerminal.toString();
        if (str.endsWith("-E") && this.myCfg.getParam("jagacy.tn3270e.standardTerminal", false))
            str = str.substring(0, str.length() - 2);
        return str;
    }

    public void sendBreak() throws JagacyException { send(new byte[] { -1, -13 }); }

    public void sendInterrupt() throws JagacyException { send(new byte[] { -1, -12 }); }

    public void sendAbortOutput() throws JagacyException { send(new byte[] { -1, -11 }); }

    public void abort() throws JagacyException {
        if (!this.myCfg.getParam("jagacy.ssl", false) && this.mySocket != null) {
            try {
                if (this.myCfg.getParam("jagacy.tcp.abortNoLinger", false))
                    this.mySocket.setSoLinger(false, 0);
            } catch (SocketException socketException) {
                this.myLogger.trace("Cannot reset linger");
            }
            super.abort();
        }
    }

    public void close() throws JagacyException {
        try {
            super.close();
            if (this.mySocket != null)
                this.mySocket.close();
        } catch (IOException iOException) {
            throw new JagacyException(1, "Error closing conduit", iOException);
        } finally {
            this.mySocket = null;
        }
    }

    protected int read() throws Exception { return this.myInput.read(); }

    protected void write(byte[] paramArrayOfbyte) throws Exception { this.myOutput.write(paramArrayOfbyte); }
}
