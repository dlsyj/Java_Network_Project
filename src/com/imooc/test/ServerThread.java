package com.imooc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/* 
 * 服务器线程处理类
 */
public class ServerThread extends Thread{
	//和本线程相关的Socket
	Socket socket = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	//线程执行的操作，响应客户端请求
	public void run(){
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			//获取输入流，并读取客户端信息
			is = socket.getInputStream();	//字节输入流
			isr = new InputStreamReader(is); //将字节流装转换为字符流，提高效率
			br = new BufferedReader(isr);	//为输入流添加缓冲
			String info = null;
			while((info = br.readLine()) != null){	//循环读取客户端的信息
				System.out.println("我是服务器，客户端说： " + info);
			}
			socket.shutdownInput();//关闭输入流
			
			//获取输出流，想用客户端的请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);	//包装为打印流
			pw.write("欢迎您!");
			pw.flush();
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			//关闭资源
			try{
				if (pw != null){
					pw.close();
				}
				if (os != null){
					os.close();
				}
				if (br != null){
					br.close();
				}
				if (isr != null){
					isr.close();
				}
				if (is != null){
					is.close();
				}
				if (socket != null){
					socket.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
