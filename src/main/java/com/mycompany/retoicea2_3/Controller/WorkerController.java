package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationWorkerBD;
import com.mycompany.retoicea2_3.Dao.WorkerDAO;
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
}
