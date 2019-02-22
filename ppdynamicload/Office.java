package ppdynamicload;

import java.util.Scanner;

/**
 * 测试动态加载类
 */
public class Office {
    public static void main(String[] args) {
        String name = "";
        Scanner s = new Scanner(System.in);
        if(s.hasNext()) name = s.nextLine();

        // 静态加载类，在编译的时候加载所有可能用到的类
        if(name.equals("Word")){
            Word w = new Word();
            w.start();
        }

        // 动态加载类，在运行时刻加载类
        try {
            // 动态加载,获得类类型
            Class classType = Class.forName("ppdynamicload." + name);

            // 根据类类型创建实例对象
            OfficeAble temp = (OfficeAble) classType.getDeclaredConstructor().newInstance();
            temp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
