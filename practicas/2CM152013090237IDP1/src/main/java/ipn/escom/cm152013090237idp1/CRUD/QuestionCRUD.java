package ipn.escom.cm152013090237idp1.CRUD;

import ipn.escom.cm152013090237idp1.interfaces.DAO;
import ipn.escom.cm152013090237idp1.model.Question;
import ipn.escom.cm152013090237idp1.persistence.XML;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.jdom2.JDOMException;

public class QuestionCRUD implements DAO<Question> {
    private final XML xml;
    
    public QuestionCRUD(String path) 
            throws IOException, JDOMException {
        this.xml = new XML(path);
    }
    
    @Override
    public Optional<Question> get(int id){
        Optional op = Optional.empty();
        try{
            Question q = this.xml.getElementById(id);
            op = Optional.ofNullable(q);
        } catch(JDOMException | IOException e){}
        return op;
    }

    @Override
    public List<Question> getAll() {
        try{
            return this.xml.getAllElements();
        } catch(JDOMException | IOException e){
            return new ArrayList<>();
        }
    }

    @Override
    public boolean add(Question t) {
        return this.xml.add(t);
    }

    @Override
    public boolean update(int id,Question t) {
        return this.xml.edit(id,t);
    }

    @Override
    public boolean remove(int id) {
        return this.xml.remove(id);
    }
    
}
