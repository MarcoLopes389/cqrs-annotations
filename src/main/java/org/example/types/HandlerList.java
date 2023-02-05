package org.example.types;

import org.example.interfaces.IQueryHandler;

public class HandlerList {
    public Class query;
    public Class<?> handler;

    public HandlerList(Class query, Class<?> handler) {
        this.query = query;
        this.handler = handler;
    }
}
