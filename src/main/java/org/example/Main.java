package org.example;

import org.example.bus.CommandBus;
import org.example.bus.QueryBus;
import org.example.commands.TestCommand;
import org.example.queries.TestQuery;

public class Main {
    public static void main(String[] args) {
        QueryBus qbus = new QueryBus();
        qbus.load();

        CommandBus cbus = new CommandBus();
        cbus.load();

        cbus.execute(new TestCommand(12));

        qbus.execute(new TestQuery("Marco"));
    }
}