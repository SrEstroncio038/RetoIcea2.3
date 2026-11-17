/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retoicea2_3.Controller;

import com.mycompany.retoicea2_3.Dao.ImplementationRefoundBD;
import com.mycompany.retoicea2_3.Dao.RefoundDAO;
import com.mycompany.retoicea2_3.Model.Refounds;

/**
 *
 * @author asola
 */
public class RefoundController {

    private RefoundDAO dao = new ImplementationRefoundBD();

    public void createRefound(Refounds r) {
        dao.createRefound(r);
    }
}
