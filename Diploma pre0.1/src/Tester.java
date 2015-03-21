import java.io.IOException;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

public class Tester {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Node.class);   
        jc.generateSchema(new SchemaOutputResolver() {

            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName)
                throws IOException {
                return new StreamResult(suggestedFileName);
            }

        });
    }

}