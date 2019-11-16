package session;

import config.Config;
import config.JagacyProperties;
import controller.Controller;
import exception.JagacyException;
import sun.misc.Signal;
import sun.misc.SignalHandler;
import utils.Loggable;
import utils.Logger;

import java.util.Properties;

//AbstractSession.class
public abstract class AbstractSession {

    Controller myController;

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

    abstract Controller createModel(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException;

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
        //checkOpen();
        //checkLogoff();
        if (paramInt1 < 0 || paramInt1 >= getHeight())
            throw new IllegalArgumentException("Invalid row: " + paramInt1);
        if (paramInt2 < 0 || paramInt2 >= getWidth())
            throw new IllegalArgumentException("Invalid column: " + paramInt2);
        if (paramInt3 < 0)
            throw new IllegalArgumentException("Invalid length: " + paramInt3);
        if (paramInt3 == 0)
            return "";
        int i = paramInt1 * getWidth() + paramInt2;
        return new String(this.myController.a(i, paramInt3));
    }

}
