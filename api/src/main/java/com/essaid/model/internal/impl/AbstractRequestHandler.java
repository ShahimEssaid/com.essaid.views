package com.essaid.model.internal.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import lombok.Getter;

import java.beans.Introspector;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Getter
public abstract class AbstractRequestHandler implements RequestHandler {

    private final WeakHashMap<Class<?>, Method> getters = new WeakHashMap<>();
    private final WeakHashMap<Class<?>, Method> setters = new WeakHashMap<>();
    private final WeakHashMap<Class<?>, MethodHandle> toStrings = new WeakHashMap<>();
    private final WeakHashMap<Class<?>, CGetterInfo> cgetterInfos = new WeakHashMap<>();

    private final Method handledMethod;
    private final String prefix;
    private final String suffix;
    private final String propertyName;

    public AbstractRequestHandler(Method handledMethod, String prefix) {
        this.handledMethod = handledMethod;
        this.prefix = prefix;
        this.suffix = handledMethod.getName().substring(prefix.length());
        this.propertyName = Introspector.decapitalize(suffix);
    }

    protected Method getGetter(Request request) {
        return getters.computeIfAbsent(request.getProxy().getClass(), aClass -> findGetter(request));
    }

    private Method findGetter(Request request) {
        try {
            return request.getProxy().getClass().getMethod("get" + suffix);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    protected Method getSetter(Request request, Class<?> setterType) {
        return setters.computeIfAbsent(request.getProxy().getClass(), aClass -> findSetter(request, setterType));
    }

    private Method findSetter(Request request, Class<?> setterType) {
        try {
            return request.getProxy().getClass().getMethod("set" + suffix, setterType);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    protected MethodHandle getToString(Request request) {

        return toStrings.computeIfAbsent(request.getProxy().getClass(), aClass -> findToString(request));
    }

    private MethodHandle findToString(Request request) {
        MethodType mt = MethodType.methodType(String.class);
        MethodHandle handle = null;


        try {
            handle = MethodHandles.lookup().findVirtual(request.getProxy().getClass(), RequestHandler._TO_STRING, mt);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return handle;
    }


    protected CGetterInfo getCGetterInfo(Request request) {
        return cgetterInfos.computeIfAbsent(request.getProxy().getClass(), aClass -> findCGetterInfo(request));
    }

    private CGetterInfo findCGetterInfo(Request request) {
        try {
            Method cGetter = request.getProxy().getClass().getMethod("cget" + suffix);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Getter
    public static class CGetterInfo {
        private final Method method;
        private final Class<?> returnType;
        private final boolean isList;
        private final boolean isMap;
        private final boolean isInstance;

        public CGetterInfo(Method cgetterMethod) {
            this.method = cgetterMethod;
            this.returnType = method.getReturnType();
            if (List.class.isAssignableFrom(returnType)) {
                this.isList = true;
                this.isMap = false;
                this.isInstance = false;
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType pt) {
                    Type listType = pt.getActualTypeArguments()[0];
                }

            } else if (Map.class.isAssignableFrom(returnType)) {
                this.isMap = true;
                this.isList = true;
                this.isInstance = false;
            } else {
                this.isInstance = true;
                this.isList = true;
                this.isMap = false;
            }


        }

    }

}
