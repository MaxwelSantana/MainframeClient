import config.JagacyProperties;
import exception.JagacyException;
import session.Session3270;
import utils.Key;

import java.io.InputStream;

public class Teste {
    public static void main(String ...args) {
        try {
            //InputStream inputStream = Teste.class.getResourceAsStream("/resources/cacerts");

            System.setProperty("jagacy.properties.dir",
                    "C:\\Users\\MaxwelSantana\\Documents\\Cliente\\Betternow\\MainframeClient\\src\\main\\resources");
            System.setProperty("test.window", "false");

            Session3270 session = new Session3270("test");
            session.open();
            session.waitForPosition(17, 6, "TEXAS A & M UNIVERSITY", 10000);
            System.out.println(session.readPosition(17, 6, 22));
            /*
            session.writePosition(23, 1, "PHONBOOK");
            session.writeKey(Key.ENTER);
            session.waitForChange(10000);
            */
            for (String string : session.readScreen()) {
                System.out.println(string);
            }
            session.close();
            System.out.println("SUCESS");
            /*
            JagacyProperties jagacyProperties = new JagacyProperties("sessionName", null);

            Config config = new Config(jagacyProperties);

            //System.out.println(config.getParam("jagacy.host").trim());

            Conduit3270 conduit3270 = new Conduit3270();

            conduit3270.setCfg(config);

            conduit3270.open();
             */
        }catch (JagacyException ex)
        {
            ex.printStackTrace();
        }
    }
}
