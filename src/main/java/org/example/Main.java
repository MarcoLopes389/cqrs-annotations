package org.example;

import org.example.loader.AnnotationLoader;
import org.example.loader.QueryBus;
import org.example.queries.TestQuery;

public class Main {
    public static void main(String[] args) {
        AnnotationLoader loader = new AnnotationLoader();
        loader.load();

        QueryBus bus = new QueryBus();
        bus.setLoader(loader);

        bus.execute(new TestQuery().getClass());
    }
}