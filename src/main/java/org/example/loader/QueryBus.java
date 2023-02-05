package org.example.loader;

import org.example.types.HandlerList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class QueryBus {

    AnnotationLoader loader;

    public void setLoader(AnnotationLoader loader) {
        this.loader = loader;
    }
    public void execute(Class query) {
        Optional<HandlerList> handler =
                loader.handlers.stream().filter((element) -> element.query == query).findFirst();

        if (handler.isPresent()) {
            HandlerList clazz = handler.get();

            try {
                var instance = clazz.handler.getDeclaredConstructor().newInstance();
                Method method = instance.getClass().getDeclaredMethod("execute", clazz.query);
                method.invoke(instance, clazz.query.getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Query not found");
        }
    }
}
