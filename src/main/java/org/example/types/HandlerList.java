package org.example.types;

import org.example.interfaces.IQueryHandler;

public class HandlerList {
    public Class caller;
    public Class<?> handler;

    public HandlerList(Class caller, Class<?> handler) {
        this.caller = caller;
        this.handler = handler;
    }
}
