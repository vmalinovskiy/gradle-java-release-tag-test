package com.callfire.api.client;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Elementary integration tests
 */
public class SimpleIntegrationTest {

    @Test
    @Ignore
    public void queryCallfireThroughProxyWithBasicAuth() throws Exception {
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_ADDRESS_PROPERTY, "localhost:8080");
        CallfireClient.getClientConfig().put(ClientConstants.PROXY_CREDENTIALS_PROPERTY, "57b84c23c087:fd10b131b0015175");
        CallfireClient client = new CallfireClient("", "");
        System.out.println("Account" + client.meApi().getAccount());
    }
}
