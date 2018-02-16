package contract.implementors;

import contract.ConsecionariaService;
import dbaccess.DAOFactory;

public abstract class MSSQLConsecionaria extends ConsecionariaService {
    public MSSQLConsecionaria() {
        super(DAOFactory.MSSQL);
    }
    
}
