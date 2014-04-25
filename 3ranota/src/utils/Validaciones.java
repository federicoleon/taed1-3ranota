package utils;

public class Validaciones {
	
	private enum ExtensionesImagenValidas {
		JPG("jpg"), 
	    JPEG("jpeg"), 
	    PNG("png");
	    
	    private String extension;
	    private ExtensionesImagenValidas(String extension) {
	    	this.extension = extension;
	    }
	    public String getExtension() {
	    	return this.extension;
	    }
	    public static boolean esExtensionValida(String ext) {
	    	return ( ext.equalsIgnoreCase(JPG.getExtension()) ||
    			ext.equalsIgnoreCase(JPEG.getExtension()) ||
    			ext.equalsIgnoreCase(PNG.getExtension()));
	    }
	}
	
	private enum ExtensionesDocumentosValidas {
		TXT("txt"), 
	    DOC("doc"), 
	    PDF("pdf");
	    private String extension;
	    private ExtensionesDocumentosValidas(String extension) {
	    	this.extension = extension;
	    }
	    public String getExtension() {
	    	return this.extension;
	    }
	    public static boolean esExtensionValida(String ext) {
	    	return ( ext.equalsIgnoreCase(TXT.getExtension()) ||
    			ext.equalsIgnoreCase(DOC.getExtension()) ||
    			ext.equalsIgnoreCase(PDF.getExtension()));
	    }
	}
	
	public static boolean esFormatoImagenValido(String nombreArchivo) {
		String[] aux = nombreArchivo.split("\\.");
		try {
			String extension = aux[(aux.length - 1)];
			return ExtensionesImagenValidas.esExtensionValida(extension);
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean esFormatoDocumentoValido(String nombreArchivo) {
		String[] aux = nombreArchivo.split("\\.");
		try {
			String extension = aux[(aux.length - 1)];
			return ExtensionesDocumentosValidas.esExtensionValida(extension);
		} catch(Exception e) {
			return false;
		}
	}
}