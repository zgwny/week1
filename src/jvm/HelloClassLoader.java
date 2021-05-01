package jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

public class HelloClassLoader extends ClassLoader {

	public static void main(String[] args)
			throws IOException, IllegalAccessException, InstantiationException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		Class<?> clazz = new HelloClassLoader().findClass("Hello");
		//调用hello方法
		clazz.getMethod("hello").invoke(clazz.newInstance());
	}

	/**
	 * 自定义类加载器
	 * @param String name 文件路径
	 * @return Class<?>
	 * @throws ClassNotFoundException
	 */
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] contents = null;
		try {
			contents = readFileContent("src\\jvm\\Hello.xlass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defineClass(name, contents, 0, contents.length);
	}

	/**
	 * 读取文件内容
	 * @param fileName
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] readFileContent(String fileName) throws IOException {
		byte[] data = null;
		File file = new File(fileName);
		InputStream is = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int temp;
		while ((temp = is.read()) != -1) {
			baos.write(255 - temp);
		}
		data = baos.toByteArray();
		is.close();
		baos.close();
		return data;
	}

}
