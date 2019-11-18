package session;

import controller.ControllerG;
import exception.JagacyException;

//com.jagacy.util.e.class
public final class TheThread extends Thread {
    private int fora;

    private int doa;

    private ControllerG ifa;

    public TheThread(ControllerG paramf) {
        this.ifa = paramf;
        this.doa = this.ifa.b().getTimeout("jagacy.keepalive.seconds", 300000);
        setDaemon(true);
    }

    public void run() {
        if (this.fora == 0)
        return;
        while (true) {
            if (isInterrupted())
                return;
            this.fora = this.doa;
            try {
                while (this.fora > 0) {
                    sleep(1000L);
                    this.fora -= 1000;
                }
            } catch (InterruptedException interruptedException) {
                return;
            }
            if (isInterrupted())
                return;
            try {
                this.ifa.fora(0);
            } catch (JagacyException jagacyException) {
                if (jagacyException.getError() != 3)
                    break;
            }
        }
    }

    public void a() { this.fora = 300000; }
}
