package informationschema.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.ConnectionFactory;

public class InformationSchemaModel {
	
	public static Connection conn;
	public static Statement stat;
	
	public static List<InformationSchemaTableDto> list() throws SQLException{
		List<InformationSchemaTableDto> informationsTables = new ArrayList<InformationSchemaTableDto>();
		if (conn == null){
			conn = ConnectionFactory.getConnectionH2();
		}
		stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
		while (rs.next()){
			InformationSchemaTableDto istd = new InformationSchemaTableDto();
			istd.setTableCatalog(rs.getString("TABLE_CATALOG"));
			istd.setTableSchema(rs.getString("TABLE_SCHEMA"));
			istd.setTableName(rs.getString("TABLE_NAME"));
			istd.setTableType(rs.getString("TABLE_TYPE"));
			istd.setStorageType("STORAGE_TYPE");
			istd.setSql(rs.getString("SQL"));
			istd.setRemarks(rs.getString("REMARKS"));
			istd.setLastModification(rs.getString("LAST_MODIFICATION"));
			istd.setId(rs.getInt("ID"));
			istd.setTypeName(rs.getString("TYPE_NAME"));
			istd.setTableClass(rs.getString("TABLE_CLASS"));
			informationsTables.add(istd);
		}
		return informationsTables;
	}
	
	public static Map<Integer, InformationSchemaTableDto> listWithId() throws SQLException{
		Map<Integer, InformationSchemaTableDto> informationsTables = new HashMap<Integer, InformationSchemaTableDto>();
		if (conn == null){
			conn = ConnectionFactory.getConnectionH2();
		}
		stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
		while (rs.next()){
			InformationSchemaTableDto istd = new InformationSchemaTableDto();
			istd.setTableCatalog(rs.getString("TABLE_CATALOG"));
			istd.setTableSchema(rs.getString("TABLE_SCHEMA"));
			istd.setTableName(rs.getString("TABLE_NAME"));
			istd.setTableType(rs.getString("TABLE_TYPE"));
			istd.setStorageType("STORAGE_TYPE");
			istd.setSql(rs.getString("SQL"));
			istd.setRemarks(rs.getString("REMARKS"));
			istd.setLastModification(rs.getString("LAST_MODIFICATION"));
			istd.setId(rs.getInt("ID"));
			istd.setTypeName(rs.getString("TYPE_NAME"));
			istd.setTableClass(rs.getString("TABLE_CLASS"));
			informationsTables.put(rs.getInt("ID"), istd);
		}
		return informationsTables;
	}
	

}

