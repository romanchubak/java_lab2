package IO;

import Entity.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by romanchubak on 01.11.2016.
 */
public class XmlIO implements IO {
    @Override
    public void WriteToFile(Entity.Warehouse w_house, String fileName) {
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Warehouse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(w_house, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Warehouse readFromFile(String fileName) {
        Warehouse warehouse = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Warehouse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            warehouse = (Warehouse) unmarshaller.unmarshal(new File(fileName));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return warehouse;
    }
}
