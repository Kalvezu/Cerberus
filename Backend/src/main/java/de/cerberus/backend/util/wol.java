package de.cerberus.backend.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class wol {
    public static void sendMagicPacket(String mac) throws Exception {
        byte[] macBytes = parseMac(mac);
        byte[] bytes = new byte[6 + 16 * macBytes.length];
        for (int i = 0; i < 6; i++) bytes[i] = (byte) 0xFF;
        for (int i = 6; i < bytes.length; i += macBytes.length)
            System.arraycopy(macBytes, 0, bytes, i, macBytes.length);

        InetAddress addr = InetAddress.getByName("255.255.255.255");
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            socket.send(new DatagramPacket(bytes, bytes.length, addr, 9));
        }
    }

    private static byte[] parseMac(String mac) {
        String[] hex = mac.split("[:-]");
        byte[] out = new byte[6];
        for (int i = 0; i < 6; i++)
            out[i] = (byte) Integer.parseInt(hex[i], 16);
        return out;
    }
}