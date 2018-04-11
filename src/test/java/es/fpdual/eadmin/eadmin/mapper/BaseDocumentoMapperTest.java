package es.fpdual.eadmin.eadmin.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public abstract class BaseDocumentoMapperTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;
	private static final Integer TIPO_DOCUMENTO = 1;
	private Documento documento;

	@Autowired
	private DocumentoMapper maper;

	@Before
	public void inicializarCadaTest() {
		documento = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,
				DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	}

	@Test
	public void deberiaInertarUnDocumento() throws Exception {
		assertThat(maper.insertarDocumento(documento),is(1));
	}
}
