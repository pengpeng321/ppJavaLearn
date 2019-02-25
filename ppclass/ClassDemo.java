package ppclass;

/**
 * 根据类获取 类类型         说明每个类都有个隐藏的静态成员变量
 * 根据类对象获取 类类型
 * 根据类名称获取 类类型
 *
 * 类类型的相关说明
 */
public class ClassDemo {
    public static void main(String[] args) {
        Foo foo = new Foo();

        // Foo这个类也是个实例对象，Class类的实例对象，但是Class这个类的构造方法是私有的
        Class c1 = Foo.class;
        Class c2 = foo.getClass();
        Class c3 = null;
        try {
            // 动态加载类，运行时加载
            c3 = Class.forName("ppclass.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 可以根据类类型 创建实例对象
        try {
            // 获取类的实例对象
            Foo temp = (Foo) c1.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Foo{}