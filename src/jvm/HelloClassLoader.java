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
		//����hello����
		clazz.getMethod("hello").invoke(clazz.newInstance());
	}

	/**
	 * �Զ����������
	 * @param String name �ļ�·��
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
	 * ��ȡ�ļ�����
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
