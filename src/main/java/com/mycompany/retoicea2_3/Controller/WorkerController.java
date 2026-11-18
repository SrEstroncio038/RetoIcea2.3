package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationWorkerBD;
import com.mycompany.retoicea2_3.Dao.WorkerDAO;
import com.mycompany.retoicea2_3.Model.Worker;
import javafx.collections.ObservableList;

/**
Controlador encargado de gestionar los trabajadores.
Actúa como intermediario entre la interfaz de usuario
y la capa de acceso a datos para consultar y eliminar trabajadores.

@author G.2
*/
public class WorkerController {
    private final WorkerDAO dao=new ImplementationWorkerBD();
    
    public ObservableList<String> showAllWorkers(){
        return dao.showAllWorkers();
    }
    
    public Worker showOneWorker(String userId){
        return dao.showOneWorker(userId);
    }
    
    public void deleteWorker(String userId){
        dao.deleteWorker(userId);
    }
    
    public void updateWorker(Worker worker, String userId){
        dao.updateWorker(worker, userId);
    }
    
    public void insertWorker(Worker worker){
    dao.insertWorker(worker);
}
}