package com.sunsine.fastdfs.pool;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.sunsine.fastdfs.FastDFSTemplateFactory;

import java.io.IOException;
/**
 * @ClassName: ConnectionFactory
 * @Description: 链接创建
 * @author: liutao
 * @date: 2018年3月2日 下午1:52:53
 */
class ConnectionFactory extends BasePooledObjectFactory<StorageClient> {
	private FastDFSTemplateFactory factory;
	public ConnectionFactory(FastDFSTemplateFactory templateFactory) {
		this.factory = templateFactory;
	}
	@Override
	public StorageClient create()
		throws Exception {
		TrackerClient trackerClient = new TrackerClient(factory.getG_tracker_group());
		TrackerServer trackerServer = trackerClient.getConnection();
		return new StorageClient(trackerServer, null);
	}
	@Override
	public PooledObject<StorageClient> wrap(StorageClient storageClient) {
		return new DefaultPooledObject<>(storageClient);
	}
	public PooledObject<StorageClient> makeObject()
		throws Exception {
		return wrap(create());
	}
	public void destroyObject(StorageClient obj)
		throws Exception {
		close(obj.getTrackerServer());
	}
	private void close(TrackerServer trackerServer) {
		if (trackerServer != null) {
			try {
				trackerServer.close();
			} catch (IOException ignored) {
			}
		}
	}
}