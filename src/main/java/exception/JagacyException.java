package exception;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class JagacyException extends Throwable {
    public JagacyException(int i, String s, IOException iOException) {
        super(s);
    }

    public JagacyException(int i, String cannot_send_data, Exception exception) {
    }

    public JagacyException(int i, String conduit_is_already_open) {
    }

    public JagacyException(int i, String s, String str, UnknownHostException unknownHostException) {
    }

    public JagacyException(int i, String s, String str, Exception exception) {
    }

    public JagacyException(SocketException socketException) {
    }

    public JagacyException(int i, String s, boolean b) {
    }

    public JagacyException(Exception exception) {
    }

    public JagacyException(String s) {
    }

    public int getError() {
        return 0;
    }
}
