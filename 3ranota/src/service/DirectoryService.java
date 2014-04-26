package service;

import java.io.File;
import model.Archivo;
import model.Ejecucion;
import model.Resultados;

public class DirectoryService {
	
	public DirectoryService() {}
	
	public Resultados createNewDirectoryStructure(String directoryPath) {
		Ejecucion ejecucion = new Ejecucion(directoryPath);
		this.createNewDirectoryStructure(ejecucion, ejecucion.getFilePath());
		ejecucion.procesarResultados();
		return ejecucion.getResultadosEjecucion();
	}
	
	private void createNewDirectoryStructure(Ejecucion ejecucion, File pathDirectory) {
		try {
			File[] files = pathDirectory.listFiles();
			for (File file : files) {
				Archivo archivo = new Archivo(file.getName());
				if (file.isDirectory()) {
					archivo.setEsDirectorio(true);
					ejecucion.agregar(archivo);
					createNewDirectoryStructure(ejecucion, file);
				} else {
					ejecucion.agregar(archivo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}