package ppclass;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法的反射
 * 反射是绕过编译的
 * @author pengpeng
 * @date 2019/2/23 15:50
 */
public class ClassMethodReflect {
    public static void main(String[] args) {
        A a = new A();
        // 要获取方法，就要获取类的信息，就要获取类类型
        Class c = a.getClass();
        try {
            // 获取单个方法，参数：方法名，参数类类型...
            Method m1 = c.getMethod("print");
            // 方法的反射操作，用m方法对象来调用，和a.print()完全相同
            m1.invoke(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method m2 = c.getMethod("print", int.class, int.class);
            m2.invoke(a, 23, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method m3 = c.getDeclaredMethod("print", String.class, String.class);
            m3.invoke(a, "sd", "asf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 了解泛型本质
         */
        ArrayList list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList<>();

        list2.add("asdf");
        Class c1 = list1.getClass();
        Class c2 = list2.getClass();

        /**
         * 反射操作都是编译之后的操作，说明编译之后集合的泛型是去泛型
         */
        System.out.println(c1 == c2);
        try {
            Method m = list2.getClass().getMethod("add", Object.class);
            // list2是String，但是却能存入数字23
            m.invoke(list2, 23);
            System.out.println(list2.size());
            /**
             * for(String str : list)...
             * 他会认为都是string，类型转换出错
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class A{
    public void print(){
        System.out.println("hello word");
    }
    public void print(int a, int b){
        System.out.println(a + b);
    }
    void print(String str1, String str2){
        System.out.println(str1 + str2);
    }
}
