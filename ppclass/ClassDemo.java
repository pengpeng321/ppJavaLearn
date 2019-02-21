package ppclass;

public class ClassDemo {
    public static void main(String[] args) {
        Foo foo = new Foo();

        //Foo这个类也是个实例对象，Class类的实例对象，但是Class这个类的构造方法是私有的
        Class c1 = Foo.class;                       //根据类获取 类类型         说明每个类都有个隐藏的静态成员变量
        Class c2 = foo.getClass();                  //根据类对象获取 类类型
        Class c3 = null;                            //根据类名称获取 类类型
        try {
            c3 = Class.forName("ppclass.Foo");      //动态加载类，运行时加载
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //可以根据类类型 创建实例对象
        try {
            Foo temp = (Foo) c1.getDeclaredConstructor().newInstance(); //获取类的实例对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Foo{}