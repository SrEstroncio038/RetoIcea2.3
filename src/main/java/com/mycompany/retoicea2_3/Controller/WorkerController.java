package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationWorkerBD;
import com.mycompany.retoicea2_3.Dao.WorkerDAO;
import com.mycompany.retoicea2_3.Model.Worker;
import javafx.collections.ObservableList;

/**
 *
 * @author etnag
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
}
