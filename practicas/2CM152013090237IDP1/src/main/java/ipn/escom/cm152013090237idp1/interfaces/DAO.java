package ipn.escom.cm152013090237idp1.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    
    public Optional<T> get(int id);
    public List<T> getAll();
    public boolean add(T t);
    public boolean update(int id,T t);
    public boolean remove(int id);
    
}
