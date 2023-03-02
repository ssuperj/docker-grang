package com.grang;

import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class Main {

    @Test
    public static void main(String[] args) throws UnknownHostException {
        /*InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();*/

        String mongoHome = System.getenv("MONGO_HOME");
        System.out.println("mongoHome = " + mongoHome);
    }
}

