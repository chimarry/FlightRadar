package pro.artse.dal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pro.artse.dal.errorhandling.ErrorHandler;

public final class FileUtil {
	public static Boolean uploadFile(byte[] data, String uri) {
		boolean isSaved = false;
		File userDirectory = new File(DirectoryStructureBuilder.ROOT_DIRECTORY_PATH);
		if (!userDirectory.exists())
			userDirectory.mkdir();

		File fileToSave = new File(uri);

		try {
			boolean isCreated = fileToSave.createNewFile();
			if (!isCreated)
				return false;
			byte[] dataToSave = data;
			isSaved = saveFile(fileToSave, dataToSave);
		} catch (IOException e) {
			ErrorHandler.handle(e);
		}
		return isSaved;
	}

	public static byte[] downloadFile(String filePath) {
		try {
			byte[] data = Files.readAllBytes(Paths.get(filePath));
			return data;
		} catch (IOException e) {
			ErrorHandler.handle(e);
			return null;
		}
	}

	private static boolean saveFile(File file, byte[] data) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file, true)) {
			fos.write(data);
			return true;
		}
	}

	public static class DirectoryStructureBuilder {
		public static final String ROOT_DIRECTORY_PATH = ConfigurationUtil.get("rootDirectoryPath");
		public static final String DIRECTORY_PATH_FORMAT = "%s" + File.separator + "%s";

		public static String buildPathForFile(String fileName) {
			return String.format(DIRECTORY_PATH_FORMAT, ROOT_DIRECTORY_PATH, fileName);
		}

		public static String getFileName(Path path) {
			return path.getFileName().toString();
		}
	}
}
