/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Koussay
 */
public class ReponseRec {
    private int idRec;
    private String 
    message,
    datecreation;

    public ReponseRec(int i, String message, LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ReponseRec{" + "idRec=" + idRec + ", message=" + message + ", datecreation=" + datecreation + '}';
    }
    
    public ReponseRec(){

              }

    public ReponseRec(int idRec, String message, String datecreation) {
        this.idRec = idRec;
        this.message = message;
        this.datecreation = datecreation;
    }

    public int getIdRec() {
        return idRec;
    }

    public String getMessage() {
        return message;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }
    
    
    
    
}
