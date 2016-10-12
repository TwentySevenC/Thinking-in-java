package initialization;

import static util.util.Print.print;

/**
 * Initialization  static field -> field -> construct method
 * 对象的创建过程，假设有个名为Dog的类：
 * 1. 即使没有显式的使用static关键字，构造器实际上也是静态方法。因此，当首次创建类型为Dog的对象时（构造器可以看成静态方法）
 * 	  或者Dog类的静态方法／静态域首次被访问时，Java解析器必须查找类路径，以定位Dog.class文件。
 * 2. 然后载入Dog.class（这将创建一个Class对象），有关静态初始化的所有动作都会执行。因此，静态初始化只在Class对象首次加载
 * 	  的时候进行一次。
 * 3. 当用new Dog() 创建对象的时候，首次将在堆上为Dog对象分配足够的存储空间。
 * 4. 这块存储空间会被清零，这就自动的将Dog对象中的所有基本类型数据都设置成了默认值，而引用则被设置成了null
 * 5. 执行所有出现于字段定义处的初始化动作。
 * 6. 执行构造器。
 * 
 * @author liujian
 *
 */

class Bowl {
	Bowl(int marker) {
		print("Bowl(" + marker + ")");
	}
	void f1(int marker) {
		print("f1(" + marker + ")");
	}
}

class Table {
	static Bowl bowl1 = new Bowl(1);
	public Table() {
		print("Table()");
		bowl1.f1(1);
	}
	void f2(int marker) {
		print("f2(" + marker + ")");
	}
	static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
	Bowl bowl3 = new Bowl(3);
	static Bowl bowl4 = new Bowl(4);
	public Cupboard() {
		print("Cupboard()");
		bowl4.f1(2);
	}
	void f3(int marker) {
		print("f3(" + marker + ")");
	}
	static Bowl bowl5 = new Bowl(5);
}

public class StaticInitialization {
	public static void main(String[] args) {
		print("Creating new Cupboard() in main");
		new Cupboard();
		print("Creating new Cupboard() in main");
		new Cupboard();
		table.f2(1);
		cupboard.f3(1);
	}
	static Table table = new Table();
	static Cupboard cupboard = new Cupboard();
}
