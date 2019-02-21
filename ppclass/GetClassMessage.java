package ppclass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetClassMessage {
    public static void main(String[] args) {
        Class c1 = Double.class;
        Class c2 = double.class; //上下不一样

        System.out.println(c1.getName());           //完整路径 + 名称
        System.out.println(c1.getSimpleName());

        //获取类的信息
        int a = 2;
        ClassUtil.printClassMethods(a);
        ClassUtil.printClassField(a);
    }
}
class ClassUtil{

    /* Method类
     * 万事万物都是对象,方法也是对象，是Method的对象
     * */
    public static void printClassMethods(Object obj){
        //传递的是哪个子类的对象，c就是该子类的类类型（getClass采用native方法）
        Class c = obj.getClass();
        System.out.println("类的名称 : " + c.getName());

        /*
         * c.getMethods()            获取所有public的方法，包括父类的
         * c.getDeclaredMethods()    获取自己声明的方法，不问访问权限
         * */
        Method[] ms = c.getDeclaredMethods();
        for(Method temp : ms){
            Class returnType = temp.getReturnType();        //获取类的返回类型
            Class[] paramTypes = temp.getParameterTypes();  //获取类的返回值参数
            String modifier = Modifier.toString(temp.getModifiers());   //获取访问权限

            System.out.print(modifier + " ");
            System.out.print(returnType.getSimpleName() + " ");
            System.out.print(temp.getName() + "(");
            for(int i = 0;i < paramTypes.length;i++){
                if(i != paramTypes.length - 1){
                    System.out.print(paramTypes[i].getSimpleName() + ",");
                }
                else{
                    System.out.print(paramTypes[i].getSimpleName());
                }
            }
            System.out.println(")");
        }
    }

    /*
    * Field类
    * 万事万物都是对象,成员变量也是对象，是Field的对象
    * */
    public static void printClassField(Object obj){
        Class c = obj.getClass();
        System.out.println("类的名称 : " + c.getSimpleName());

        /*
        * c.getFields()            获取所有public的成员变量，包括父类
        * c.getDeclaredFields()    获取所有自己声明的成员变量，不问访问权限
        * */
        Field[] field = c.getDeclaredFields();
        for(Field temp : field){
            Class fieldType = temp.getType();                           //获取参数类型
            String modifier = Modifier.toString(temp.getModifiers());   //获取访问权限
            System.out.print(modifier + " ");
            System.out.print(fieldType.getSimpleName() + " ");
            System.out.println(temp.getName());
//            System.out.println(temp.toString());
        }
    }

    /*
    * Constructor类
    * 万事万物都是对象,构造方法也是对象，是Constructor的对象
    * */
    //...https://www.imooc.com/video/3735
}