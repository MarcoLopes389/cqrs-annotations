package org.example.commands;

import org.example.annotations.CommandHandler;
import org.example.interfaces.ICommandHandler;

@CommandHandler(command = TestCommand.class)
public class TestHandler implements ICommandHandler<TestCommand> {
    @Override
    public void execute(TestCommand arg) {
        System.out.println(arg.testProp);
    }
}
