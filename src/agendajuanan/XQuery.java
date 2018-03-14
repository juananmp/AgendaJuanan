/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import com.saxonica.xqj.SaxonXQDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import org.w3c.dom.Node;
/**
 *
 * @author janto
 */
public class XQuery {
    public void ejecutarQuery(String archivo){
            try {
            //Try with different Querys Query_1, Query_2, ...
            File queryFile = new File(archivo); //

            XQDataSource xqjd = new SaxonXQDataSource();
            XQConnection xqjc = xqjd.getConnection();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(queryFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(XQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            XQPreparedExpression exp = xqjc.prepareExpression(inputStream);
            XQResultSequence result = exp.executeQuery();
            while (result.next()) {
                System.out.println(result.getItemAsString(null));
            }
            result.close();
            exp.close();
            xqjc.close();

        } catch (XQException ex) {
            Logger.getLogger(XQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

