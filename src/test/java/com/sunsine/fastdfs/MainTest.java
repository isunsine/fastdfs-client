package com.sunsine.fastdfs;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sunsine.fastdfs.exception.FastDFSException;
/**
 * 
 * @ClassName: MainTest   
 * @Description: 连接池测试
 * @author: liutao
 * @date: 2018年3月2日 下午2:02:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-fastdfs.xml")
public class MainTest {
	@Resource
	private FastDFSTemplate dfsTemplate;
	private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	private final long awaitTime = 5 * 1000;
	@Test
	public void testUploadAndDel()
		throws FastDFSException {
		FastDfsInfo rv = dfsTemplate.upload("".getBytes(), "txt");
		System.out.println(rv);
		dfsTemplate.deleteFile(rv);
	}
	@Test
	public void testPool()
		throws InterruptedException {
		Runnable runnable = () -> {
			try {
				File file = new File("F:\\01-个人\\图片\\0.jpg");
				FileInputStream fis = new FileInputStream(file);
				byte[] b = new byte[fis.available()];
				fis.read(b);
				Map<String, String> map = new HashMap<>();
				FastDfsInfo rv = dfsTemplate.upload(b, "jpg", map);
				System.out.println(rv.getFileAbsolutePath());
				// dfsTemplate.deleteFile(rv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		Callable<FastDfsInfo> callable = () -> {
			File file = new File("F:\\01-个人\\图片\\0.jpg");
			try(FileInputStream fis = new FileInputStream(file);) {
				
				byte[] b = new byte[fis.available()];
				fis.read(b);
				Map<String, String> map = new HashMap<>();
				FastDfsInfo rv = dfsTemplate.upload(b, "jpg", map);
				return rv;
//				System.out.println(rv.getFileAbsolutePath());
				// dfsTemplate.deleteFile(rv);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		};
		for (int i = 0; i < 1; i++) {
			cachedThreadPool.submit(runnable);
			Future<?> future = cachedThreadPool.submit(runnable);
			FastDfsInfo rv = null;
			try {
				rv = (FastDfsInfo)future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			if (rv != null) {
				System.out.println(rv.getFileAbsolutePath());
			}
		}
		
		try {
			cachedThreadPool.shutdown();
			// (所有的任务都结束的时候，返回TRUE)
			if (!cachedThreadPool.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
				// 超时的时候向线程池中所有的线程发出中断(interrupted)。
				cachedThreadPool.shutdownNow();
			}
		} catch (InterruptedException e) {
			// awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
			System.out.println("awaitTermination interrupted: " + e);
			cachedThreadPool.shutdownNow();
		}
	}
}
