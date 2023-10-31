package com.essaid.model.impl;

import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
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
        CGetterInfo info = null;
        try {
            Method clientMethod = request.getElementHandler().getElementType().getMethod(RequestHandler.CGET + suffix);
            Method cGetter = request.getProxy().getClass().getMethod(RequestHandler.CGET + suffix);
            info = new CGetterInfo(clientMethod);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return info;
    }

    @Getter
    public static class CGetterInfo {
        private final Method method;
        private final Class<?> returnType;
        private final boolean isList;
        private final Class<?> listInstanceType;
        private final boolean isMap;
        private final boolean isInstance;

        public CGetterInfo(Method cgetterMethod) {
            String string = toString();
            this.method = cgetterMethod;
            this.returnType = method.getReturnType();
            if (List.class.isAssignableFrom(returnType)) {
                this.isList = true;
                this.isMap = false;
                this.isInstance = false;
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType pt) {
                    Type listType = pt.getActualTypeArguments()[0];
                    if (listType instanceof Class<?> cls) {
                        listInstanceType = cls;

                    } else {
                        throw new IllegalStateException("Method's list's generic type is not a simple Class:" + method);
                    }
                } else {
                    throw new IllegalStateException("Method's List return type is not ParameterizedType:" + method);
                }
            } else if (Map.class.isAssignableFrom(returnType)) {
                this.isMap = true;
                this.isList = false;
                this.isInstance = false;
                listInstanceType = null;
            } else {
                this.isInstance = true;
                this.isList = false;
                this.isMap = false;
                listInstanceType = null;
            }


        }

    }

}
