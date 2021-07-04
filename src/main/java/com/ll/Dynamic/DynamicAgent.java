package com.ll.Dynamic;//package com.ll.Dynamic;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * @author Lei
// * @version 0.1
// * @date 2021/6/30
// */
//public class DynamicAgent implements InvocationHandler {
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(
//                "**** proxy: " + proxy.getClass() +
//                        ", method: " + method + ", args: " + args);
//        if (args != null)
//            for (Object arg : args)
//                System.out.println("  " + arg);
//        return method.invoke(new RealObject(), args);
////    }
//
//
//    public static void main(String[] args) {
//        (Interface)Proxy.newProxyInstance(
//                Interface.class.getClassLoader(),
//                new Class[]{Interface.class},
//                new DynamicAgent()
//                );
//    }
//}
