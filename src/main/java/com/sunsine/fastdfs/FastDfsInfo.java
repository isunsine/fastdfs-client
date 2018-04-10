package com.sunsine.fastdfs;

/**
 * FastDFS 文件描述
 */
public class FastDfsInfo implements java.io.Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private String group;
	private String path;
	private String fileAbsolutePath;
	private String fileRelativePath;
	public FastDfsInfo(String group, String path) {
		this.group = group;
		this.path = path;
	}
	@Override
	public String toString() {
		return "FastDfsInfo{"
			+ "group='"
			+ group
			+ '\''
			+ ", path='"
			+ path
			+ '\''
			+ ", fileAbsolutePath='"
			+ fileAbsolutePath
			+ '\''
			+ ", fileRelativePath='"
			+ fileRelativePath
			+ '\''
			+ '}';
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileAbsolutePath() {
		return fileAbsolutePath;
	}
	public void setFileAbsolutePath(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}
	public String getFileRelativePath() {
		return group + "/" + path;
	}
	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}
}
