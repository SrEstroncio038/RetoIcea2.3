package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Conexion.DBConection;
import com.mycompany.retoicea2_3.Model.Worker;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.retoicea2_3.Model.Department;
import org.bson.Document;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.conversions.Bson;

public class ImplementationWorkerBD implements WorkerDAO{
    
    @Override
    public ObservableList<String> showAllWorkers(){
        MongoCollection<Document> resultados=DBConection.getInstancia().getColeccion("users");
        ObservableList<String> items=FXCollections.observableArrayList();
        for (Document doc: resultados.find(Filters.eq("userType", "Worker"))){
            items.add(doc.getString("name")+" "+doc.getString("surname"));
        }
        return items;
    }
    
    private Worker documentToWorker(Document doc){
        Worker worker=new Worker();
        try{
            worker.setId(doc.getInteger("id", 0));
            worker.setSeniority(doc.getInteger("seniority", 0));
            String departmentString=doc.getString("department");
            if(departmentString==null){
                worker.setDepartment(Department.valueOf(departmentString));
            }
            worker.setUser(doc.getString("user"));
            worker.setName(doc.getString("name"));
            worker.setSurname(doc.getString("surname"));
            worker.setBirthPlace(doc.getString("birthPlace"));
            
        }catch(Exception e) {
            System.out.println("Error obtaining workers: " + e.getMessage());
            worker=null;
        }
        return worker;
    }
    
    
    
            
}
