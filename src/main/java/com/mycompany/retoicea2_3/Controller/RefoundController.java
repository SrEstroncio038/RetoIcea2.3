package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationRefoundBD;
import com.mycompany.retoicea2_3.Dao.RefoundDAO;
import com.mycompany.retoicea2_3.Model.Refounds;

public class RefoundController {

    private RefoundDAO dao = new ImplementationRefoundBD();

    public void createRefound(Refounds r) {
        dao.createRefound(r);
    }
}
