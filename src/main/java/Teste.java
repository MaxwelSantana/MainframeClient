import exception.JagacyException;
import session.Session3270;

public class Teste {
    public static void main(String ...args) {
        try {
            System.out.println("Teste");

            System.setProperty("jagacy.properties.dir",
                    "C:\\Users\\maxwe\\git\\MainframeClient\\src\\main\\resources");
            System.setProperty("test.window", "true");

            Session3270 session = new Session3270("test");
            session.open();
            System.out.println(session.readRow(1));

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
