package utils;

import model.Archivo;

public class Ordenamiento {
	
	public static Archivo[] ordenarConBurbuja(Archivo[] array) {
		Archivo temp;
		Archivo archivoA;
		Archivo archivoB;
		for(int i=0; i<array.length; i++) {
			for(int j=(i+1); j<array.length; j++) {
				archivoA = (Archivo)array[i];
				archivoB = (Archivo)array[j];
				if( archivoA.getNombre().toLowerCase().compareTo(archivoB.getNombre().toLowerCase()) < 0 ) {
					temp = (Archivo)array[i];
					array[i] = (Archivo)array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
}