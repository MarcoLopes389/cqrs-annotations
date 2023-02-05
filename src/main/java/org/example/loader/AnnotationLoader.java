package org.example.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.example.annotations.QueryHandler;
import org.example.interfaces.IQueryHandler;
import org.example.types.HandlerList;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationLoader {

    List<HandlerList> handlers = new ArrayList<>();

    public void load() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(true);

        provider.addIncludeFilter(new AnnotationTypeFilter(QueryHandler.class));

        Set<BeanDefinition> classes = provider.findCandidateComponents("org.example");

        for (BeanDefinition bd : classes) {
            try {
                Class<?> instance = Class.forName(bd.getBeanClassName());

                System.out.println(instance.getAnnotation(QueryHandler.class).query());
                handlers.add(new HandlerList(
                        instance.getAnnotation(QueryHandler.class).query(),
                        instance
                ));
            } catch (ClassNotFoundException e) {
                System.out.println("Error to load query handler");
                System.out.println(e);
            }
        }

        System.out.println(this.handlers);
    }

    public List<HandlerList> get() {
        return this.handlers;
    }
}
