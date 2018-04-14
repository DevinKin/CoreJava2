package inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This program demonstrates the InetAddress class. Supply a host name as command-line argument,
 * or run without command-line arguments to see the address of the local host.
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        if (args.length > 0) {
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses) {
                System.out.println(a);
            }
        } else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
