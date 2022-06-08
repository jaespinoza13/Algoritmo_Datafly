
package dataFly;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Jhonny Espinoza
 */
public class PrivateTable {
    //make stuff private at some point
    
    int noOfColumns; //defino la variable para contar el numero de columnas
    int noOfRows; // defino la variable para contar el numero de filas 
    TableRow topRow;//contain table heading //create get methods later // Contiene el encabezado de la tabla de tipo TableRow
    TableRow quasiIden;//quasi-Identifiers // Deinimos los cuasiidentificadores
    ArrayList<TableRow> tableRows = new ArrayList<>(); //list of all rows in table // Guardamos en un array el listado de las filas de la tabla
    //ArrayList<TableColumn> tableColumn = new ArrayList<>();//list of all columns in table
     
     PrivateTable(){
         
     }
        
     PrivateTable(int noOfColumns, int noOfRows){
         this.noOfColumns = noOfColumns;
         this.noOfRows = noOfRows;
         
        for(int i = 0; i< noOfRows; i++){
            tableRows.add(new TableRow());
        }
    }
     
     public TableRow getTopRow(){
         return topRow;
     }
     
     public TableRow getQuasiIden(){
         return quasiIden;
     }
     
     public ArrayList<TableRow> getTableRows(){
         return tableRows;
     }
     
     public void setRowHeadings(String data){ //Metodo para establecer encabezado de la tabla recibe como parametro un String que lo denominamos dat
         
         //
         topRow = new TableRow(data, 0);//Voy a codificar esto para mi tabla de prueba,
                                        //esto debe modificarse para obtener la entrada del usuario
                                        //o de alguna base de datos
        topRow.rowNumber = 0;
     }
     
     /*establece los valores de la tabla a partir de las entradas del archivo*/
     public void setTableValues(String fileLocation) throws FileNotFoundException{ //Metodo para leer el archivo
         File inputFile = new File(fileLocation);
         System.out.println("Existe el archivo a analizar: " + inputFile.exists()); //Devuelve parametro true o false
         System.out.println("Es legible el archivo: " + inputFile.canRead()); // Devuelve parametro true o false
         Scanner infile = new Scanner(inputFile); // Definimos un objeto del archivo leido
         int x  = 1;  //medir la fila actual en la tabla
         while(infile.hasNextLine()){ //Metodo para recorrer el archivo
             String line  = infile.nextLine(); 
             tableRows.add(new TableRow(line, x));
             x++;
         }
     }
     
     /*set table values from database  inputs*/
     public void setTableValues(Connection conn) throws FileNotFoundException, SQLException{
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(
                 "SELECT race,ph.dob,p.id,sex,allele1,allele2 "
                 + "FROM production_genotype "
                 + "inner JOIN production_patient p ON production_genotype.patient_id = p.id "
                 + "inner JOIN production_patientphi ph ON p.id = ph.id");
         ResultSetMetaData metadata = rs.getMetaData();
         int columnCount = metadata.getColumnCount();
         
         int x  = 1;//measure current row in table
         while(rs.next()){
             String line = "";
             for(int i = 1; i <= columnCount; i++){
              line = line + rs.getString(i) + ",";
             }
             tableRows.add(new TableRow(line, x));
             x++;
         }
     }
     
     public String getValue(int col, int row){
         if(row == 0){
             return (String)topRow.getData().get(col);
         }
         else if((row > noOfRows) || (col > noOfColumns))
            return "Problem!!!!";
         else
             return (String)tableRows.get(row).getData().get(col);
     }
     
     public void setQuasi(String data){
         quasiIden = new TableRow();
         quasiIden.addData(data);
     }
     
     //make a copy method at some point
     public PrivateTable copy(){
         PrivateTable newTable = new PrivateTable();
         newTable.noOfColumns = noOfColumns;
         newTable.noOfRows = noOfRows;
         newTable.quasiIden = quasiIden.copy();
         newTable.topRow = topRow.copy();
         for(int i = 0; i < tableRows.size(); i++){
            newTable.tableRows.add(tableRows.get(i).copy());
         }
         
         return newTable;
     }
     
     //need method to display formatted table
     //Prints out table in a readable format
     public void printFormat(){
         topRow.rowPrint();
         System.out.println();
         for(int i = 0; i < tableRows.size(); i++){
             tableRows.get(i).rowPrint();
         }
         System.out.println("Tamaño de la tabla " + tableRows.size());
     }
}
