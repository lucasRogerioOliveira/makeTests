import static org.junit.Assert.assertTrue;
import informationschema.tables.InformationSchemaModel;
import informationschema.tables.InformationSchemaTableDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class H2DatabaseTests {
	
	public static List<InformationSchemaTableDto> informationsSchemaTable;

	
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException{
        informationsSchemaTable = InformationSchemaModel.list();
	}
	
	
	@Test
	public void showRecordsTest() throws SQLException{
        assertTrue(informationsSchemaTable.size() > 0);
	}
	
	
	@Test
	public void makeResultSetInformationSchemaTablesTest() throws SQLException, IllegalAccessException, IOException{
        System.out.println(MakeTests.makeSettersWith(informationsSchemaTable, "InformationsSchemaTableTest", false));
	}
	
	
	@Test
	public void makeResultSetInformationSchemaTablesMapTest() throws SQLException, IllegalAccessException, IOException{
		Map<Integer, InformationSchemaTableDto> map = InformationSchemaModel.listWithId();
        System.out.println(MakeTests.makeSettersWith(map, "InformationsSchemaTableMapTest", false));
	}	
	
}
