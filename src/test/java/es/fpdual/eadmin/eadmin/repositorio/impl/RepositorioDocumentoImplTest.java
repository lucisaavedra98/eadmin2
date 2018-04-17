package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;

import org.junit.Assert.*;

public class RepositorioDocumentoImplTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;

	private RepositorioDocumentoImpl repositorioDocumento;

	private DocumentoMapper mapper;

	private final Documento documento =	new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION,
					DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);

	@Before
	public void inicializarEnCadaTest() {
		mapper = mock(DocumentoMapper.class);
		
		this.repositorioDocumento = new RepositorioDocumentoImpl(this.mapper);
	}

	@Test
	public void deberiaAlmacenarUnDocumento() {
		  this.repositorioDocumento.altaDocumento(documento);
		  
		  verify(mapper).insertarDocumento(documento);
	}

	@Test
	public void deberiaModificarUnDocumento() {
		when(mapper.modificarDocumento(this.documento)).thenReturn(1);
		
		 this.repositorioDocumento.modificarDocumento(documento);
		  
		  verify(mapper).modificarDocumento(documento);
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deberiaLanzarExcepcionSiIntentamosModificarUnDocumentoQueNoExiste() {
		when(this.mapper.modificarDocumento(this.documento)).thenReturn(0);
		
		this.repositorioDocumento.modificarDocumento(this.documento);
	}

	@Test
	public void deberiaEliminarUnDocumento() {
		this.repositorioDocumento.altaDocumento(documento);

		this.repositorioDocumento.eliminarDocumento(documento.getCodigo());

		verify(this.mapper).eliminarDocumento(this.documento.getCodigo());
	}

	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		when(this.mapper.consultarDocumento(this.documento.getCodigo())).thenReturn(documento);
		
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);

		assertThat(resultado, is (this.documento));
	}
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() {
		//DECLARACION
		final List<Documento>todosLosDocumentos = Arrays.asList(this.documento);
		
		//ENTRENAMIENTO
		when(this.mapper.consultarTodosLosDocumentos()).thenReturn(todosLosDocumentos);
		
		//PRUEBAS
		List<Documento> resultado = repositorioDocumento.obtenerTodosLosDocumentos();
		
		//VERIFIACION
		assertThat(resultado, hasSize(1));
		assertThat(resultado, hasItems(this.documento));
	}
}
