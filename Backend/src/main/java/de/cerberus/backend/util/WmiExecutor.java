package de.cerberus.backend.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class WmiExecutor {

    private final String user;
    private final String pass;

    public WmiExecutor(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    public void exec(String host, String command) {

        List<String> cmd = List.of(
            "wmic",
            "/node:" + host,
            "/user:" + user,
            "/password:" + pass,
            "process",
            "call",
            "create",
            "\"" + command + "\""
        );

        try {
            Process proc = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            try (BufferedReader r = new BufferedReader(
                     new InputStreamReader(proc.getInputStream()))) {
                r.lines(); 
            }
            proc.waitFor();
        } catch (Exception ex) {
            throw new RuntimeException("WMIC failed on " + host, ex);
        }
    }
}