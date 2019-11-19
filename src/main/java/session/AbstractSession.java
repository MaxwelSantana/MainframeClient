package session;

import config.Config;
import config.JagacyProperties;
import controller.ControllerG;
import exception.JagacyException;
import sun.misc.Signal;
import sun.misc.SignalHandler;
import ui.UserInterface;
import utils.Key;
import utils.Loggable;
import utils.Logger;
import utils.Util;

import java.util.Date;
import java.util.Properties;

//AbstractSession.class
public abstract class AbstractSession {

    ControllerG myController;

    JagacyProperties myProperties;

    private String inta;

    Config myCfg;

    Loggable myLogger;

    private TheThread a;

    private boolean fora;

    private Thread newa;

    private SignalHandler ifa;

    private SignalHandler doa;

    boolean myIsInsert;

    UserInterface myUi;

    AbstractSession(String paramString, Properties paramProperties) throws JagacyException {
        this.inta = paramString;
        this.myProperties = new JagacyProperties(this.inta, paramProperties);
        this.myCfg = new Config(this.myProperties);
        if (this.myProperties.getBoolean("jagacy.log4j", false) || this.myProperties.getBoolean("log4j", false)) {
            try {
                this.myLogger = (Loggable)Class.forName("com.jagacy.util.Log4jLogger").newInstance();
            } catch (Throwable throwable) {
                throw new JagacyException(4, "Error starting log4j", new Exception(String.valueOf(throwable)));
            }
        } else {
            this.myLogger = (Loggable)new Logger(this.myProperties);
        }
        this.myController = createModel(this.myProperties, this.myLogger);
        this.a = new TheThread(this.myController);
        this.a.start();
    }

    abstract ControllerG createModel(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException;

    public int getWidth() { return this.myController.newa(); }

    public int getHeight() { return this.myController.a(); }

    public int getMaxWidth() { return this.myController.inta(); }

    public int getMaxHeight() { return this.myController.doa(); }

    void openModel() throws JagacyException { this.myController.openController(); }

    public synchronized void open() throws JagacyException {
        if (this.fora)
            throw new JagacyException(4, "Session is already open");
        this.myLogger.watch("Opening session");
        openModel();
        this.fora = true;
        if (this.myProperties.getBoolean("jagacy.signals", true))
            try {
                Class.forName("sun.misc.SignalHandler");
                SignalHandler signalHandler = new SignalHandler() {
                    public void handle(Signal param1Signal) {
                        if (AbstractSession.this.fora)
                            AbstractSession.this.newa = Thread.currentThread();
                        try {
                            AbstractSession.this.close();
                        } catch (JagacyException jagacyException) {
                            jagacyException.printStackTrace();
                        }
                        System.exit(0);
                    }
                };
                this.ifa = Signal.handle(new Signal("INT"), signalHandler);
                this.doa = Signal.handle(new Signal("TERM"), signalHandler);
            } catch (ClassNotFoundException classNotFoundException) {}
        //this.myUi = createUi();
        //if (this.myUi != null) {
            //this.myUi.setLocked(a());
            //this.myUi.update();
        //}
        if (!logon())
            close();
    }

    protected boolean logon() throws JagacyException {
        checkOpen();
        return true;
    }

    protected void logoff() throws JagacyException { checkOpen(); }

    void checkOpen() throws JagacyException {
        if (!this.fora)
        throw new JagacyException(4, "exception.session.closed", true);
    }

    void checkLogoff() throws JagacyException {
        if (this.newa != null && this.newa != Thread.currentThread())
            throw new JagacyException(14, "Application is logging off");
    }

    public synchronized void close() throws JagacyException {
        try {
            if (this.a != null)
                this.a.interrupt();
            if (this.fora) {
                this.myLogger.watch("Closing session");
                try {
                    logoff();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            //if (this.myUi != null)
                //this.myUi.close();
            if (this.myController != null)
                this.myController.casea();
        } finally {
            this.fora = false;
            this.myIsInsert = false;
            if (this.ifa != null) {
                Signal.handle(new Signal("INT"), this.ifa);
                this.ifa = null;
            }
            if (this.doa != null) {
                Signal.handle(new Signal("TERM"), this.doa);
                this.doa = null;
            }
        }
    }

    public String readRow(int paramInt) throws JagacyException {
        if (paramInt < 0 || paramInt >= getHeight())
            throw new IllegalArgumentException("Invalid row: " + paramInt);
        return readPosition(paramInt, 0, getWidth());
    }

    public String readPosition(int paramInt1, int paramInt2, int paramInt3) throws JagacyException {
        checkOpen();
        checkLogoff();
        if (paramInt1 < 0 || paramInt1 >= getHeight())
            throw new IllegalArgumentException("Invalid row: " + paramInt1);
        if (paramInt2 < 0 || paramInt2 >= getWidth())
            throw new IllegalArgumentException("Invalid column: " + paramInt2);
        if (paramInt3 < 0)
            throw new IllegalArgumentException("Invalid length: " + paramInt3);
        if (paramInt3 == 0)
            return "";
        int i = paramInt1 * getWidth() + paramInt2;

        char[] teste = this.myController.a(i, paramInt3);
        return new String(teste);
    }

    public boolean waitForPosition(int i, int j, String s, int k) throws JagacyException {
        if (i < 0 || i >= getHeight())
            throw new IllegalArgumentException((new StringBuilder("Invalid row: ")).append(i).toString());
        if (j < 0 || j >= getWidth())
            throw new IllegalArgumentException((new StringBuilder("Invalid column: ")).append(j).toString());
        if (k < 0)
            throw new IllegalArgumentException((new StringBuilder("Invalid timeout: ")).append(k).toString());
        if (Util.isEmpty(s))
            throw new IllegalArgumentException("Empty value");
        int l = s.length();
        String s1 = readPosition(i, j, l);
        if (s.equals(s1))
            return true;
        for (int i1 = 0; i1 < k; i1 += 100) {
            waitForChange(100);
            String s2 = readPosition(i, j, l);
            if (s.equals(s2))
                return true;
        }

        if (k != 100)
            myLogger.warn("Possible intermediate screen");
        return false;
    }

    public boolean waitForChange(int i) throws JagacyException {
        if (i < 0)
            throw new IllegalArgumentException((new StringBuilder("Invalid timeout: ")).append(i).toString());
        else
            return wait(i, 0, null);
    }

    boolean wait(int paramInt1, int paramInt2, String paramString) throws JagacyException {
        checkOpen();
        checkLogoff();
        int i = paramInt1;
        this.a.a();
        boolean bool = this.myController.ifa();
        if (!bool)
            while (paramInt1 > 0) {
                long l1 = (new Date()).getTime();
                try {
                    this.myController.ifa(paramInt1, paramInt2, paramString);
                } catch (JagacyException jagacyException) {
                    if (jagacyException.getError() == 16 && this.myCfg.getParam("jagacy.autoReconnect", false)) {
                        abort();
                        if (this.myUi != null) {
                            this.myUi.resetTime();
                            try {
                                this.myUi.update();
                            } catch (JagacyException jagacyException1) {}
                        }
                        open();
                    } else {
                        throw jagacyException;
                    }
                }
                if (this.myUi != null)
                    try {
                        this.myUi.update();
                    } catch (JagacyException jagacyException) {}
                bool = this.myController.ifa();
                if (bool)
                    break;
                long l2 = (new Date()).getTime();
                paramInt1 = (int)(paramInt1 - l2 - l1);
            }
        this.myController.voida();
        if (this.myUi != null) {
            if (bool)
                this.myUi.endTime();
            try {
                this.myUi.update();
            } catch (JagacyException jagacyException) {}
        }
        if (!bool && i != 100 && paramInt2 <= 0 && paramString == null)
            this.myLogger.warn("Possible intermediate screen");
        return bool;
    }

    public synchronized void abort() {
        if (this.fora) {
            this.myLogger.watch("Abort session");
            try {
                close();
            } catch (JagacyException jagacyException) {}
        }
    }

    public char[] readScreenText() throws JagacyException {
        checkOpen();
        checkLogoff();
        return myController.a(0, getWidth() * getHeight());
    }

    public String[] readScreen() throws JagacyException {
        int i = getHeight();
        String as[] = new String[i];
        for (int j = 0; j < i; j++)
            as[j] = readRow(j);

        return as;
    }

    public void writePosition(int paramInt1, int paramInt2, String paramString) throws JagacyException {
        if (paramInt1 < 0 || paramInt1 >= getHeight())
            throw new IllegalArgumentException("Invalid row: " + paramInt1);
        if (paramInt2 < 0 || paramInt2 >= getWidth())
            throw new IllegalArgumentException("Invalid column: " + paramInt2);
        writeCursor(paramInt1, paramInt2);
        writeString(paramString);
    }

    public Key writeKey(Key paramKey) throws JagacyException {
        checkOpen();
        checkLogoff();
        if (paramKey == null)
            throw new IllegalArgumentException("Null key");
        if (isControllerKey(paramKey)) {
            this.myIsInsert = false;
            this.a.a();
            this.myController.doa(paramKey);
            /*
            if (this.myUi != null) {
                this.myUi.setLocked(a());
                this.myUi.resetTime();
                this.myUi.beginTime();
            }*/
            return paramKey;
        }
        processKey(paramKey);
        if (this.myUi != null)
            this.myUi.update();
        return paramKey;
    }

    abstract void processKey(Key paramKey) throws JagacyException;

    abstract boolean isControllerKey(Key paramKey);
    /*
    public void writePosition(Location paramLocation, String paramString) throws JagacyException {
        if (paramLocation == null)
            throw new IllegalArgumentException("Null location");
        writePosition(paramLocation.getRow(), paramLocation.getColumn(), paramString);
    }*/

    public void writePosition(String paramString) throws JagacyException {
        if (Util.isEmpty(paramString))
            throw new IllegalArgumentException("Empty propertyPrefix");
        int i = this.myProperties.getCardinal(Util.concat(paramString, "row"));
        int j = this.myProperties.getCardinal(Util.concat(paramString, "column"));
        String str = this.myProperties.get(Util.concat(paramString, "value"));
        writePosition(i, j, str);
    }

    public void writePosition(String paramString1, String paramString2) throws JagacyException {
        if (Util.isEmpty(paramString1))
            throw new IllegalArgumentException("Empty propertyPrefix");
        int i = this.myProperties.getCardinal(Util.concat(paramString1, "row"));
        int j = this.myProperties.getCardinal(Util.concat(paramString1, "column"));
        writePosition(i, j, paramString2);
    }

    public void writeCursor(int paramInt1, int paramInt2) throws JagacyException {
        checkOpen();
        checkLogoff();
        if (paramInt1 < 0 || paramInt1 >= getHeight())
            throw new IllegalArgumentException("Invalid row: " + paramInt1);
        int i = getWidth();
        if (paramInt2 < 0 || paramInt2 >= i)
            throw new IllegalArgumentException("Invalid column: " + paramInt1);
        this.myController.a(paramInt1 * i + paramInt2);
    }

    public void writeString(String paramString) throws JagacyException {
        checkOpen();
        checkLogoff();
        if (!Util.isEmpty(paramString)) {
            if (this.myIsInsert) {
                insertString(paramString);
            } else {
                this.myController.a(paramString);
            }
            if (this.myUi != null)
                this.myUi.update();
        }
    }

    abstract void insertString(String paramString) throws JagacyException;

    public JagacyProperties getProperties() {
        return myProperties;
    }
}
