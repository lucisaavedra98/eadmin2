package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.util.Utilidades;


@Transactional("eadminTransactionManager")
public abstract class BaseDocumentoMapperTest {

	private static final Date FECHA_CREACION = Utilidades.asDate(LocalDate.of(2015, 1, 1));
	private static final Date FECHA_ULTIMA_MODIFICACION = Utilidades.asDate(LocalDate.of(2015, 1, 1));
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;
	private Documento documento;

	@Autowired
	private DocumentoMapper maper;

	@Before
	public void inicializarCadaTest() {
		documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,
				DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	}

	@Test
	public void deberiaInsertarUnDocumento() throws Exception {
		
		final int resultado=this.maper.insertarDocumento(this.documento);
		
		assertThat(resultado,is(1));
	}
	
	@Test
	public void deberiaEliminarUnDocumento() throws Exception {
		this.maper.insertarDocumento(this.documento);
		
		final int resultado=this.maper.eliminarDocumento(this.documento.getCodigo());
		
		assertThat(resultado,is(1));
	}
	
	
	
	@Test
	public void deberiaActualizarTodosLosCamposDeUnDocumento() throws Exception {
		//DECLARACION
		final Documento documentoActualizado = new Documento(CODIGO_DOCUMENTO, "documento2", Utilidades.asDate(LocalDate.of(2015, 2, 1)), Utilidades.asDate(LocalDate.of(2015, 2, 2)),
				DOCUMENTO_PUBLICO, EstadoDocumento.APROBADO);
		
		//ENTRENAMIENTO
		this.maper.insertarDocumento(this.documento);
		
		//PRUEBA
		final int resultado= this.maper.modificarDocumento(documentoActualizado);
		
		//VERIFICACION
		assertThat(resultado,is(1));
		
		final Documento documentoModificado = this.maper.consultarDocumento(CODIGO_DOCUMENTO);
		assertThat(documentoModificado,is(documentoActualizado));
	}
	
	@Test
	public void deberiaConsultarUnDocumento() throws Exception {
		
		this.maper.insertarDocumento(this.documento);
		
		this.maper.consultarDocumento(documento.getCodigo());
	}
	
}