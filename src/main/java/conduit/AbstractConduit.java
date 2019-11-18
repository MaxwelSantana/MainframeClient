package conduit;

import config.Config;
import exception.JagacyException;
import utils.ByteBuffer;
import utils.Loggable;
import utils.Logger;
import utils.TerminalC;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;

public abstract class AbstractConduit {
    protected static final int BUFFER_SIZE = 8192;

    private int width;

    private int height;

    protected Config myCfg;

    protected Loggable myLogger;

    protected BufferedInputStream myInput;

    protected BufferedOutputStream myOutput;

    protected Object myMutex = new Object();

    protected TerminalC myTerminal;

    public abstract void open() throws JagacyException;

    public abstract void send(ByteBuffer paramByteBuffer) throws JagacyException;

    public abstract ByteBuffer receive(int paramInt) throws JagacyException;

    public abstract int getTerminalFlags();

    protected abstract int read() throws Exception;

    protected abstract void write(byte[] paramArrayOfbyte) throws Exception;

    protected abstract int[] createReceiveState();

    protected abstract void resetTimeout() throws JagacyException;

    protected abstract boolean isInitialReceiveState(int[] paramArrayOfint);

    protected abstract boolean isValidReceivedData(int[] paramArrayOfint, byte paramByte);

    protected abstract boolean isReceiveDone(int[] paramArrayOfint, byte paramByte);

    public int getMaxWidth() { return this.myTerminal.getMaxWidth(); }

    public int getMaxHeight() { return this.myTerminal.getMaxHeight(); }

    public int getWidth() { return this.width; }

    public int getHeight() { return this.height; }

    protected void logBytes(String paramString, byte[] paramArrayOfbyte) {
        String str = "";
        if (this.myLogger.isTraceEnabled()) {
            str = String.valueOf(str) + paramString + Logger.NL;
            str = String.valueOf(str) + ">" + Logger.NL;
            byte b;
            label21: for (b = 0;; b += 16) {
                byte b1 = 0;
                while (b1 < 16) {
                    if (b + b1 == paramArrayOfbyte.length) {
                        if (b1 > 0)
                            str = String.valueOf(str) + Logger.NL;
                    } else {
                        String str1 = Integer.toHexString(paramArrayOfbyte[b + b1] & 0xFF);
                        if (str1.length() < 2)
                            str = String.valueOf(str) + "0";
                        str = String.valueOf(str) + str1;
                        str = String.valueOf(str) + " ";
                        b1++;
                        continue;
                    }
                    str = String.valueOf(str) + "<";
                    this.myLogger.trace(str);
                    break label21;
                }
                str = String.valueOf(str) + Logger.NL;
            }
        } else {
            return;
        }
        str = String.valueOf(str) + "<";
        this.myLogger.trace(str);
        // Byte code: goto -> 266
    }

    protected AbstractConduit() { a(); }

    private void a() {
        this.myTerminal = TerminalC.d;
        /*
        this.a = this.myTerminal.new();
        this.if = this.myTerminal.try();

         */
    }

    public final void setLogger(Loggable paramLoggable) { this.myLogger = paramLoggable; }

    public final void setCfg(Config paramd) { this.myCfg = paramd; }

    public TerminalC getTerminal() { return this.myTerminal; }

    protected void init() throws JagacyException {
        if (this.myInput != null)
            throw new JagacyException(1, "Conduit is already open");
        String str = this.myCfg.getParam("jagacy.terminal", this.myTerminal.toString()).trim();
        this.myTerminal = TerminalC.getTerminal(str);
        if (this.myTerminal == null)
            throw new JagacyException(1, "Unknown terminal type " + str);
        //this.myLogger.trace("Opening conduit, terminal=" + this.myTerminal);
        //this.a = this.myTerminal.new();
        this.width = this.myTerminal.getMaxWidth();
        this.height = this.myTerminal.getMaxHeight();
        /*
        if (a1 == -1)
        this.if = this.myCfg.a("jagacy.rows", c.void);
        this.if = this.myCfg.a("jagacy.rows", this.if);
        this.a = this.myCfg.a("jagacy.columns", this.a);
             */
    }

    protected void send(byte[] paramArrayOfbyte) throws JagacyException {
        if (this.myOutput == null)
            throw new JagacyException(1, "Conduit is closed");
        logBytes("Sending", paramArrayOfbyte);
        try {
            write(paramArrayOfbyte);
            this.myOutput.flush();
        } catch (Exception exception) {
            abort();
            throw new JagacyException(1, "Cannot send data", exception);
        }
    }

    protected ByteBuffer receive(ByteBuffer paramByteBuffer) throws JagacyException {
        byte b;
        if (this.myInput == null)
            throw new JagacyException(1, "Conduit is closed");
        int[] arrayOfInt = createReceiveState();
        do {
            int i = -1;
            try {
                synchronized (this.myMutex) {
                    i = read();
                }
            } catch (InterruptedIOException interruptedIOException) {
                i = -2;
            } catch (Exception exception) {
                abort();
                throw new JagacyException(16, "read", exception);
            }
            if (i == -2 && isInitialReceiveState(arrayOfInt))
                return paramByteBuffer;
            if (i < 0) {
                if (isInitialReceiveState(arrayOfInt)) {
                    abort();
                    throw new JagacyException(16, "read returned " + i);
                }
                break;
            }
            resetTimeout();
            b = (byte)i;
            if (!isValidReceivedData(arrayOfInt, b))
                continue;
            paramByteBuffer.append(b);
        } while (!isReceiveDone(arrayOfInt, b));
        if (this.myLogger.isTraceEnabled())
            logBytes("Received", paramByteBuffer.getBytes());
        return paramByteBuffer;
    }

    public abstract void sendBreak() throws JagacyException;

    public void abort() throws JagacyException {
        this.myLogger.trace("Disconnected");
        close();
    }

    public void close() throws JagacyException {
        this.myLogger.trace("Closing conduit");
        try {
            if (this.myInput != null)
                this.myInput.close();
            if (this.myOutput != null)
                this.myOutput.close();
        } catch (IOException iOException) {
            throw new JagacyException(1, "Error closing conduit", iOException);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a();
            this.myInput = null;
            this.myOutput = null;
        }
    }
}
