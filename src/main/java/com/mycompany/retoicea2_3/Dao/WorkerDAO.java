package com.mycompany.retoicea2_3.Dao;

import com.mycompany.retoicea2_3.Model.Worker;
import javafx.collections.ObservableList;

/**
 *
 * @author etnag
 */
public interface WorkerDAO {
    public ObservableList<String> showAllWorkers();
    
    public Worker showOneWorker(String userId);
    
    public void deleteWorker(String userId);
}
