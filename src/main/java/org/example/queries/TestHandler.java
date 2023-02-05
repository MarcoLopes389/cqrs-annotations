package org.example.queries;

import org.example.annotations.QueryHandler;
import org.example.interfaces.IQueryHandler;

@QueryHandler(query = TestQuery.class)
public class TestHandler implements IQueryHandler<TestQuery> {
    @Override
    public void execute(TestQuery arg) {
//        System.out.println(arg.age);
        System.out.println("Funcionou");
    }
}
