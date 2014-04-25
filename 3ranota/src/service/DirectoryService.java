package service;

public class DirectoryService {
	private String rootPath;
	private HistoryService historyService;
	
	public DirectoryService() {
		this.historyService = new HistoryService();
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public void createNewDirectoryStructure(String pathToDirectory) {
		public static void displayDirectoryContents(File dir) {
			try {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						System.out.println("directory:" + file.getCanonicalPath());
						displayDirectoryContents(file);
					} else {
						System.out.println("     file:" + file.getCanonicalPath());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}