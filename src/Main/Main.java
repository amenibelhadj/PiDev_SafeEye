/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.RDV;
import Entities.dispo;
import Service.RdvService;
import Service.dispoService;
import static java.lang.Boolean.TRUE;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import source.DataSource;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here        
       DataSource c = DataSource.getinstance();
        
       /* RDV r1;
           r1 = new RDV (1,"non","mohamed","01/10/2022","12:00");
        RdvService r= new RdvService();
        r.ajouterRdv(r1); */
        
        dispo d1;
           d1 = new dispo (1,"yossr","01/10/2022","15:00");
        dispoService d= new dispoService();
        d.ajouterD(d1);
        
        
        
    }
    
}
