package service;

import java.io.File;
import utils.Validaciones;
import model.Archivo;
import model.Ejecucion;

public class DirectoryService {
	
	public DirectoryService() {
	}
	
	public void createNewDirectoryStructure(String directoryPath) {
		Ejecucion ejecucion = new Ejecucion(directoryPath);
		this.createNewDirectoryStructure(ejecucion, ejecucion.getFilePath());
		ejecucion.procesarResultados();
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
					if(Validaciones.esFormatoDocumentoValido(archivo.getNombre())) {
						archivo.setEsDocumentoValido(true);
					}else if(Validaciones.esFormatoImagenValido(archivo.getNombre())) {
						archivo.setEsFotoValida(true);
					}
					ejecucion.agregar(archivo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}