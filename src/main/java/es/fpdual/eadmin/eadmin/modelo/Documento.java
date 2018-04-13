package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento extends ElementoBaseAdministracionElectronica {

	private final EstadoDocumento estado;
	
	
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico, EstadoDocumento estado) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion, publico);
		this.estado = estado;
	}
	
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico, Integer codigoEstado) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion, publico);
		switch (codigoEstado) {
		case 1:this.estado=EstadoDocumento.ACTIVO;break;
		case 2:this.estado=EstadoDocumento.APROBADO;break;
		case 3:this.estado=EstadoDocumento.ELIMINADO;break;
		default:this.estado=null;break;
		}
	}
	
	public EstadoDocumento getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return "Documento con código " + codigo;
	}
		
}
