
package dataFly;
import java.util.ArrayList;
/**
 *
 * @author Jhonny Espinoza
 */
public class TableRow {
    int rowNumber; //number of row in the table // definimos la variable para guardar el numero de filas de la tabla
    //int noOfColumns;
    ArrayList<String> data = new ArrayList<>();
    public TableRow(){
        
    }
    public TableRow(String data, int rowNumber){ // En este metdo receptamos la data de las variables ya definidas arriba y el numero de filas
        String[] stringArray = data.split(","); // Separamos los campos de cada fila basandonos en la coma para separar
        for (String stringArray1 : stringArray) { // recorremos la data que nos llego del txt y guardamos en el array previamente con la condicion de la coma ,
            this.data.add(stringArray1);
        }
        this.rowNumber = rowNumber;// establecimos el numero valor de las filas de la tabla 
    }
    //assuming row is representated as ... , ... , ... , ... ,
    public void addData(String data){ //Metodo agregar Data nos llega el parametro data definido previamente 
        String[] stringArray = data.split(","); // Guardamos en el Array los datos ya recorriendo con el foreach y distiguiendo por la coma ,
        for (String stringArray1 : stringArray) { // Recorrimos en al array y vamos guaradndo cada registro en stringArray1
            this.data.add(stringArray1);
        }
    }
    
    public ArrayList<String> getData(){ //En este metodo devolvemos la data ya reccorida previamente 
        return data; 
    }
    /**use this to check equality of table
     * @param row - row, the calling row wants to be compared to
     * @return boolean */
    public boolean checkEquality(TableRow row){ //Vamos a comprobar la cantidad de filas de la tabal igualdad con la data 
        if(data.size() != row.data.size()) //Si el tamaño de la data es diferente a las filas del tamaño de la data regresa Falso
            return false;
        else //SINO 
        {
            for(int i = 0; i < data.size(); i++){ // Recorro el tamaño de la data
                if((data.get(i).equalsIgnoreCase(row.data.get(i))) == false) // Compara los datos de data en la posicion i con los de la data de la fila en la posicion i 
                    return false; //Si es falso retorna false
            }
            return true; // Caso contrario retorna true
        }
        
    }
    
    public TableRow copy(){  //Definimos la clase copiar de tipo TableRow que dinimos en las variables
        TableRow newTableRow = new TableRow(); //Creamos un objeto newTableRow de tipo TableRow
        newTableRow.rowNumber = rowNumber; //llamamos al objeto creado con la extension de rowNumber con las filas de rowNumber
        for(int i = 0; i < data.size(); i++){ // Recorremos el tamaño de la data que ya esta cargada de las filas que previamente recorrimos 
            newTableRow.data.add(data.get(i)); // Agregamos en el objeto newTableRow los datos de la data en la posicion i
        }
        return newTableRow; //Retornamos el objeto ya recorrido y agregago los datos de la tabla 
    }
    
    public void rowPrint(){ //Impimir los registros de la data recorrida 
        for(int i = 0; i < data.size(); i++ ){ //Recorremos el tamaño de la data 
            System.out.printf("%15s", data.get(i)); //Visualizamos los datos de la data en cada posicion i
        }
        System.out.println();
    }
    
    /*public boolean seeIfSequenceIsInRow(ArrayList<String> array){
        
    }*/
  
}
