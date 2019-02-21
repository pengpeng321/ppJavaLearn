package ppdynamicload;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Office {
    public static void main(String[] args) {

        String name = "";
        Scanner s = new Scanner(System.in);
        if(s.hasNext()) name = s.nextLine();


        //静态加载类，在编译的时候加载所有可能用到的类
        if(name.equals("Word")){
            Word w = new Word();
            w.start();
        }

        //动态加载类，在运行时刻加载类
        try {
            Class classType = Class.forName("ppdynamicload." + name);                                    //动态加载,获得类类型
            try {
                OfficeAble temp = (OfficeAble) classType.getDeclaredConstructor().newInstance();    //根据类类型创建实例对象
                temp.start();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
