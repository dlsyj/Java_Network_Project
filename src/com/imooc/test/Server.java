package com.imooc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/* 基于TCP协议的Socket通信，实现用户登陆
 * 服务器端
 */
public class Server {
	public static void main(String[] args){
		try {
			//1.创建一个服务器端Socket，即ServerSocket,指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			//2.调用ServerSocket的accept()方法开始监听，等待客户端的连接
			System.out.println("****服务器即将启动，等待客户端的连接");
			int count = 0;
//			Socket socket = serverSocket.accept();
			Socket socket = null;
			//循环监听等待客户端的连接
			while (true){
				//调用accept方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				//thread.setPriority(4);//设置线程的优先级,范围为[1,10],默认值为5，未设置优先级可能会导致运行时速度慢，可降低优先级
				//启动线程
				serverThread.start();
				count ++;	//统计客户端的数量
				System.out.println("客户端的数量" + count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端的IP: " + address.getHostAddress());
			}
			
/*单线程
//			//3.获取输入流，并读取客户端信息
//			InputStream is = socket.getInputStream();	//字节输入流
//			InputStreamReader isr = new InputStreamReader(is); //将字节流装转换为字符流，提高效率
//			BufferedReader br = new BufferedReader(isr);	//为输入流添加缓冲
//			String info = null;
//			while((info = br.readLine()) != null){	//循环读取客户端的信息
//				System.out.println("我是服务器，客户端说： " + info);
//			}
//			socket.shutdownInput();//关闭输入流
//			
//			//4.获取输出流，想用客户端的请求
//			OutputStream os = socket.getOutputStream();
//			PrintWriter pw = new PrintWriter(os);	//包装为打印流
//			pw.write("欢迎您!");
//			pw.flush();
//			
//			//5.关闭资源
//			pw.close();
//			os.close();
//			br.close();
//			isr.close();
//			is.close();
//			socket.close();
//			serverSocket.close();
 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
