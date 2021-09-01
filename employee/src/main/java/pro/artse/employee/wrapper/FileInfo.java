package pro.artse.employee.wrapper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInfo {
	private byte[] data;
	private String fileName;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileNameFromPath(String path) {
		Path p = Paths.get(path);
		fileName = p.getFileName().toString();
	}
}
