package conduit;

import exception.JagacyException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public final class SslConduit3270 extends Conduit3270 {
    private InputStream T = null;

    private OutputStream S = null;

    private X509TrustManagerB R;

    public void open() throws JagacyException {
        super.open();
        receive(this.myCfg.getParam("jagacy.ssl.receiveTimeout", 10000));
    }

    protected Socket getSocket(InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception, JagacyException {
        this.R = new X509TrustManagerB(null, this.myLogger, this.myCfg);
        this.R.newa();
        return this.R.a(paramInetAddress, paramInt1, paramInt2);
    }

    protected Socket getSocket(Proxy paramProxy, InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception, JagacyException {
        InetSocketAddress inetSocketAddress = (InetSocketAddress)paramProxy.address();
        Socket socket = super.getSocket(paramProxy, paramInetAddress, paramInt1, paramInt2);
        this.R = new X509TrustManagerB(null, this.myLogger, this.myCfg);
        this.R.newa();
        return this.R.a(socket, inetSocketAddress.getAddress(), inetSocketAddress.getPort(), paramInt2);
    }

    protected void reopen() throws JagacyException {
        try {
            this.R.doa();
            Cipher cipher1 = Cipher.getInstance(this.R.inta());
            cipher1.init(2, this.R.fora());
            this.T = new CipherInputStream(this.myInput, cipher1);
            Cipher cipher2 = Cipher.getInstance(this.R.inta());
            cipher2.init(1, this.R.ifa());
            this.S = new CipherOutputStream(this.myOutput, cipher2);
        } catch (Exception exception) {
            throw new JagacyException(1, "Could not enable SSL key", exception);
        }
    }

    protected int read() throws Exception {
        int i;
        if (this.T != null) {
            i = this.T.read();
        } else {
            i = super.read();
        }
        return i;
    }

    protected void write(byte[] paramArrayOfbyte) throws Exception {
        if (this.S != null) {
            this.S.write(paramArrayOfbyte);
        } else {
            super.write(paramArrayOfbyte);
        }
    }
}
