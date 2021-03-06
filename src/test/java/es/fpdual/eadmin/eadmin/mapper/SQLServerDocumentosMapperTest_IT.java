package es.fpdual.eadmin.eadmin.mapper;

import org.junit.runner.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-configuracion/eadmin-persistencia.xml",
	"classpath:spring-configuracion/eadmin-sqlserver-persistencia.xml"
	})
@Rollback
public class SQLServerDocumentosMapperTest_IT extends BaseDocumentoMapperTest {
	
}