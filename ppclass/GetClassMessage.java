package ppclass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 测试获取类信息：成员变量、方法等
 */
public class GetClassMessage {
    public static void main(String[] args) {
        // c1 c2不一样
        Class c1 = Double.class;
        Class c2 = double.class;

        // getName 获得类的完整路径 + 名称
        System.out.println(c1.getName());
        // getSimpleName 获得类名称
        System.out.println(c1.getSimpleName());

        // 测试
        int a = 2;
        ClassUtil.printClassMethods(a);
        ClassUtil.printClassField(a);
    }
}

/**
 * 获取类的信息
 */
class ClassUtil{
    /**
     * 使用Method类,获取类方法
     * 万事万物都是对象,方法也是对象，是Method的对象
     * @param obj
     */
    public static void printClassMethods(Object obj){
        int i;
        // 传递的是哪个子类的对象，c 就是该子类的类类型（getClass采用native方法）
        Class c = obj.getClass();
        System.out.println("类的名称 : " + c.getName());

        /**
         * c.getMethods()            获取所有public的方法，包括父类的
         * c.getDeclaredMethods()    获取自己声明的方法，不问访问权限
         */
        Method[] ms = c.getDeclaredMethods();
        for (Method temp : ms) {
            // 获取类的返回类型
            Class returnType = temp.getReturnType();
            // 获取类的返回值参数
            Class[] paramTypes = temp.getParameterTypes();
            // 获取访问权限
            String modifier = Modifier.toString(temp.getModifiers());

            System.out.print(modifier + " ");
            System.out.print(returnType.getSimpleName() + " ");
            System.out.print(temp.getName() + "(");
            for (i = 0;i < paramTypes.length - 1;i++) {
                System.out.print(paramTypes[i].getSimpleName() + ",");
            }
            System.out.print(paramTypes[i].getSimpleName() + ")");
        }
    }

    /**
     * 使用Field类
     * 万事万物都是对象,成员变量也是对象，是Field的对象
     * @param obj
     */
    public static void printClassField(Object obj){
        Class c = obj.getClass();
        System.out.println("类的名称 : " + c.getSimpleName());

        /**
         * c.getFields()            获取所有public的成员变量，包括父类
         * c.getDeclaredFields()    获取所有自己声明的成员变量，不问访问权限
         */
        Field[] field = c.getDeclaredFields();
        for (Field temp : field) {
            //获取参数类型
            Class fieldType = temp.getType();
            //获取访问权限
            String modifier = Modifier.toString(temp.getModifiers());
            System.out.print(modifier + " ");
            System.out.print(fieldType.getSimpleName() + " ");
            System.out.println(temp.getName());
        }
    }

    /**
     * 使用Constructor类
     * 万事万物都是对象,构造方法也是对象，是Constructor的对象
     */
    //...https://www.imooc.com/video/3735
}