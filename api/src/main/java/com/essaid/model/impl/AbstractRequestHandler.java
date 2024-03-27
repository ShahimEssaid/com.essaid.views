package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestType;
import lombok.Getter;

import java.lang.invoke.MethodHandle;
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
    private final WeakHashMap<Class<?>, GetterInfo> cgetterInfos = new WeakHashMap<>();

    protected final Method method;
    protected final String featureName;
    protected final RequestType requestType;
    protected final Object defaultValue;
    protected final ModelManager modelManager;


    public AbstractRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ModelManager modelManager) {
        this.method = method;
        this.featureName = featureName;
        this.requestType = requestType;
        this.defaultValue = defaultValue;
        this.modelManager = modelManager;
    }

//    protected Method getGetter(Invocation invocation) {
//        return getters.computeIfAbsent(
//            invocation.getProxy().getClass(), aClass -> findGetter(invocation));
//    }

//    private Method findGetter(Invocation invocation) {
//        try {
//            return invocation.getProxy().getClass().getMethod("get" + suffix);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    protected MethodHandle getToString(Invocation invocation) {
//
//        return toStrings.computeIfAbsent(
//            invocation.getProxy().getClass(), aClass -> findToString(invocation));
//    }

//    private MethodHandle findToString(Invocation invocation) {
//        MethodType mt = MethodType.methodType(String.class);
//        MethodHandle handle = null;
//
//
//        try {
//            handle = MethodHandles.lookup().findVirtual(invocation.getProxy().getClass(), RequestHandler._TO_STRING, mt);
//        } catch (NoSuchMethodException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//        return handle;
//    }


//    protected GetterInfo getCGetterInfo(Invocation invocation) {
//        return cgetterInfos.computeIfAbsent(
//            invocation.getProxy().getClass(), aClass -> findCGetterInfo(invocation));
//    }
//
//    private GetterInfo findCGetterInfo(Method method, ModelObjectHandler objectHandler) {
//        GetterInfo info = null;
//        try {
//            Method addMethod = objectHandler.getObjectType().getMethod(RequestHandler.CGET + suffix);
//            Method cGetter = invocation.getProxy().getClass().getMethod(RequestHandler.CGET + suffix);
//            info = new CGetterInfo(addMethod);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//
//        return info;
//    }

    @Getter
    public static class GetterInfo {
        private final Method method;
        private final Class<?> returnType;
        private final boolean isList;
        private final Class<?> listInstanceType;
        private final boolean isMap;
        private final boolean isInstance;

        public GetterInfo(Method cgetterMethod) {
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
