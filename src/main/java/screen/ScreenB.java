package screen;

import config.Config;
import controller.MyControllerH;
import exception.JagacyException;
import utils.Loggable;

//com.jagacy.tn3270.b
public class ScreenB extends ScreenBase {
    public ScreenB(MyControllerH paramh, Config paramd, Loggable paramLoggable) throws JagacyException {
        super(paramh);
        String str = paramd.getParam("jagacy.codepage", "INTERNAL");
        paramLoggable.watch("Codepage=" + str);
        if (str.equalsIgnoreCase("INTERNAL")) {
            this.fora = new CodePageC();
        } else if (str.indexOf('.') >= 0) {
            try {
                this.fora = (CodePage)Class.forName(str).newInstance();
            } catch (Exception exception) {
                throw new JagacyException(exception);
            }
        } else {
            this.fora = new CodePage(str);
        }
    }

    void fora(int paramInt) { doa(paramInt); }

    protected ScreenB() {}

    public Object clone() {
        ScreenB b1 = new ScreenB();
        b1.a = this.a;
        b1.doa = (byte[])this.doa.clone();
        b1.fora = this.fora;
        return b1;
    }
}
