package ipn.escom.cm152013090237idp1.persistence;

import ipn.escom.cm152013090237idp1.model.Answer;
import ipn.escom.cm152013090237idp1.model.Question;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jdom2.Attribute;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.input.SAXBuilder;

public class XML {
    public final String XML_RELATIVE_PATH = "xml/"; 
    public final String XML_FILENAME = "data.xml";
    private final String XML_CHILDREN = "PREGUNTA";
    private final File xmlFile;
    private Document doc;
    public XML(String path)
            throws IOException, JDOMException{
        File temp = new File(path
                .concat(XML_RELATIVE_PATH));
        
        this.xmlFile = new File(path
                .concat(XML_RELATIVE_PATH)
                .concat(XML_FILENAME));
        
        /* Si el archivo no existe en el path*/
        if(!temp.exists()) temp.mkdirs();
        /* Si el archivo no existe */
        if(this.xmlFile.createNewFile()) {
            FileWriter writer;
            Document document = new Document();
            writer = new FileWriter(this.xmlFile);
        
            document.setRootElement(new Element("OPTION_MULTIPLE_MOVIECLIP"));
            new XMLOutputter().output(document, writer);
            writer.flush();
            writer.close();
        }
        this.doc = new SAXBuilder().build(this.xmlFile); 
    }
    
    public Question getElementById(int id) 
            throws JDOMException, IOException {
        if(this.doc.getRootElement().getContentSize() < id) return null;
        return Question.parseChildrenElements((Element) this.doc
                .getRootElement()
                .getContent(id));
    }
    
    public List<Question> getAllElements()
            throws JDOMException, IOException {
        List<Question> q = new ArrayList<>();
        for(Element e : this.doc
                .getRootElement()
                .getChildren(this.XML_CHILDREN)){
            q.add(Question.parseChildrenElements(e));
        }
        return q;
    }
    
    public boolean add(Question q){
        Element root = doc.getRootElement();
        root.addContent(this.questionToElement(q));
        
        return this.saveChanges();
    }
    
    public boolean remove(int id){
        if(id > this.doc.getRootElement().getContentSize()) return false;
        this.doc
                .getRootElement()
                .removeContent(2*(id-1) + 1);
        return this.saveChanges();
    }
    
    public boolean edit(int id,Question q){
        if(!this.remove(id)) return false;
        this.doc
                .getRootElement()
                .setContent(id, this.questionToElement(q));
        
        return this.saveChanges();
    }
    
    private boolean saveChanges(){
        boolean response = true;
        try{
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
        
            FileWriter writer;
            writer = new FileWriter(this.xmlFile);
            xmlOutput.output(doc, writer);
            writer.flush();
            writer.close();
        } catch(IOException e){
            response = false;
        }
        return response;
    }
    
    private Element questionToElement(Question q) {
        Element newElement = new Element(this.XML_CHILDREN)
                .setAttribute("TEXTO", q.getQuestion());
        for(Answer answer : q.getAnswerOptions()){
            newElement.addContent(new Element("OPCION")
                    .setText(answer.getAnswer())
                    .setAttribute("ISRIGHTANSWER", Boolean.toString(answer.getIsRightAnswer())));
        }
        return newElement;
    }
}
