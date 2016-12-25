package com.imooc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/* 基于TCP协议的Socket通信，实现用户登陆
 * 客户端
 */
public class Client {
	public static void main(String[] args) {
		try {
			//1.创建客户端Socket,指定服务器地址和端口
			Socket socket = new Socket("localhost",8888);
			
			//2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();	//字节输出流
//			//使用ObjectOutputStream对象序列化流传输对象
//			ObjectOutputStream oos = new ObjectOutputStream(os);
//			User user = new User("admin","123");	//封装为对象
//			oos.writeObject(user);	//序列化
			PrintWriter pw = new PrintWriter(os);	//将输出流包装为打印流
			pw.write("用户名：admin；密码：123");
			pw.flush();
			socket.shutdownOutput();	//关闭输出流
			//3.获取输入流，用来读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info = br.readLine()) != null){	//循环读取客户端的信息
				System.out.println("我是客户端,服务器端说： " + info);
			}
			
			//4.关闭资源
			br.close();
			is.close();
			pw.close();	//不能关闭输出流!否则会导致socket也被关闭
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
