package de.cerberus.backend.entity;

import java.util.Arrays;

public enum Command {
    MON_ON("mon-on"),
    MON_OFF("mon-off"),
    LOCK("lock"),
    UNLOCK("unlock"),
    RESTART("restart"),
    SHUTDOWN("shutdown"),
    WOL("wol");

    public final String path;
    Command(String p) { this.path = p; }

    public static Command from(String raw) {
        return Arrays.stream(values())
                     .filter(c -> c.path.equalsIgnoreCase(raw))
                     .findFirst()
                     .orElseThrow(() ->
                         new IllegalArgumentException("Unknown command " + raw));
    }
}