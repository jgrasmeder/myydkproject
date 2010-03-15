/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if (pc.getIdentifer().equals("joe")) {
           if (!pc.getPassword().equals("password")) {
                throw new IOException("wrong password");
           }
           return;
        }
        throw new IOException("wrong password");
    }

}
