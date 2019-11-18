import config.JagacyProperties;
import exception.JagacyException;
import session.Session3270;

public class Teste {
    public static void main(String ...args) {
        try {
            System.setProperty("jagacy.properties.dir",
                    "C:\\Users\\maxwe\\git\\MainframeClient\\src\\main\\resources");
            System.setProperty("test.window", "false");

            Session3270 session = new Session3270("test");
            session.open();
            JagacyProperties p = session.getProperties();
            session.waitForPosition(17, 6, "TEXAS A & M UNIVERSITY", 10000);
            String[] screens = session.readScreen();
            for (String string : screens) {
                System.out.println(string);
            }
            session.close();
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
