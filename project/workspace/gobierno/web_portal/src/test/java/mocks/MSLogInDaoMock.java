package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MSLogInDaoMock extends MSLogInDao {
    public ArrayList<DynaActionForm> db = new ArrayList<>();

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        Optional<Long> max =
                db.stream()
                        .map(l -> ((LogInForm)l))
                        .filter( l -> l.getUsername().equals(((LogInForm) form).getUsername()) )
                        .map( l -> l.getId())
                        .max(Comparable::compareTo);

        ((LogInForm) form).setId(max.orElse(Long.valueOf(0)) + 1);
        db.add(form);
    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        return db;
    }
}