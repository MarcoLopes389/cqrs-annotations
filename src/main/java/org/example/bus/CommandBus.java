package org.example.bus;

import org.example.annotations.CommandHandler;
import org.example.types.HandlerList;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CommandBus {

    public List<HandlerList> handlers = new ArrayList<>();

    public void load() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(true);

        provider.addIncludeFilter(new AnnotationTypeFilter(CommandHandler.class));

        Set<BeanDefinition> classes = provider.findCandidateComponents("org.example");

        for (BeanDefinition bd : classes) {
            try {
                Class<?> instance = Class.forName(bd.getBeanClassName());

                handlers.add(new HandlerList(
                        instance.getAnnotation(CommandHandler.class).command(),
                        instance
                ));
            } catch (ClassNotFoundException e) {
                System.out.println("Error to load query handler");
                System.out.println(e);
            }
        }
    }

    public void execute(Object query) {
        Optional<HandlerList> optional =
                this.handlers.stream().filter(
                        (element) -> {
                            return element.caller.getName() == query.getClass().getName();
                        }
                ).findFirst();

        if (optional.isPresent()) {
            HandlerList clazz = optional.get();

            try {
                var instance = clazz.handler.getDeclaredConstructor().newInstance();
                Method method = instance.getClass().getDeclaredMethod("execute", clazz.caller);
                method.invoke(instance, query);
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
