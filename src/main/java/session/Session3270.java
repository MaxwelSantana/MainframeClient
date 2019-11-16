package session;

import config.JagacyProperties;
import controller.Controller;
import controller.MyController;
import exception.JagacyException;
import utils.Loggable;

import java.util.Properties;

public class Session3270 extends AbstractSession {

    private MyController bytea = (MyController)this.myController;

    public Session3270(String paramString, Properties paramProperties) throws JagacyException { super(paramString, paramProperties); }

    public Session3270(String paramString) throws JagacyException { this(paramString, null); }

    Controller createModel(JagacyProperties paramJagacyProperties, Loggable paramLoggable) throws JagacyException {
        return new MyController(paramJagacyProperties, paramLoggable);
    }
}
