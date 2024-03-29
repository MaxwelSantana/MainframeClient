package session;

import config.JagacyProperties;
import controller.ControllerG;
import controller.MyControllerH;
import exception.JagacyException;
import utils.Key;
import utils.Loggable;

import java.util.Properties;

public class Session3270 extends AbstractSession {

    private MyControllerH bytea = (MyControllerH)this.myController;

    public Session3270(String paramString, Properties paramProperties) throws JagacyException { super(paramString, paramProperties); }

    public Session3270(String paramString) throws JagacyException { this(paramString, null); }

    ControllerG createModel(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException {
        return new MyControllerH(paramJagacyProperties, paramLoggable);
    }

    void processKey(Key paramKey) throws JagacyException {

    }

    boolean isControllerKey(Key paramKey) { return !this.bytea.a(paramKey); }

    void insertString(String paramString) throws JagacyException {

    }
}
