package org.csource.fastdfs.test;
import org.csource.fastdfs.UploadCallback;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * 
 * @ClassName: UploadLocalFileSender   
 * @Description: upload file callback class, local file sender
 * @author: liutao
 * @date: 2018年3月2日 下午1:06:43
 */
public class UploadLocalFileSender implements UploadCallback {
	private String local_filename;
	public UploadLocalFileSender(String szLocalFilename) {
		this.local_filename = szLocalFilename;
	}
	/**
	 * send file content callback function, be called only once when the file uploaded
	 * @param out output stream for writing file content
	 * @return 0 success, return none zero(errno) if fail
	 */
	public int send(OutputStream out)
		throws IOException {
		FileInputStream fis;
		int readBytes;
		byte[] buff = new byte[256 * 1024];
		fis = new FileInputStream(this.local_filename);
		try {
			while ((readBytes = fis.read(buff)) >= 0) {
				if (readBytes == 0) {
					continue;
				}
				out.write(buff, 0, readBytes);
			}
		} finally {
			fis.close();
		}
		return 0;
	}
}
