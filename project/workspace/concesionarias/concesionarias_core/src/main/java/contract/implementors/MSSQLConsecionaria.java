package contract.implementors;

import contract.ConsecionariaService;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

public abstract class MSSQLConsecionaria extends ConsecionariaService {
    public MSSQLConsecionaria(DatasourceEnum datasourceEnum) {
        super(DAOFactory.MSSQL, datasourceEnum);
    }    
}
