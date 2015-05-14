package informationschema.tables;


public class InformationSchemaTableDto {
	
	public String tableCatalog;
	public String tableSchema;
	public String tableName;
	public String tableType;
	public String storageType;
	public String sql;
	public String remarks;
	public String lastModification;
	public Integer id;
	public String typeName;
	public String tableClass;		
	
	public String getTableCatalog() {
		return tableCatalog;
	}
	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLastModification() {
		return lastModification;
	}
	public void setLastModification(String lastModification) {
		this.lastModification = lastModification;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTableClass() {
		return tableClass;
	}
	public void setTableClass(String tableClass) {
		this.tableClass = tableClass;
	}	

}
