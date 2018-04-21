package concesionarias;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Concesionaria;
import concesionarias.forms.ConcesionariaForm;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConcesionariaInteractor implements Concesionaria {

    private Function<Dao, Boolean> exists(ConcesionariaForm form) {
        return dao -> {
          try {
              return dao.select(null).stream().anyMatch( c -> {
                  ConcesionariaForm conc = (ConcesionariaForm) c;
                    return
                          conc.getNombre().equals(form.getNombre()) &&
                              conc.getConfig().equals(form.getConfig()) &&
                                  conc.getId() == form.getId();
              });
          } catch(SQLException e) {
              e.printStackTrace();
              return false;
          }
        };
    }
    private Function<Dao, Optional<Long>> getIdOf(ConcesionariaForm form) {
        return dao -> {
            try {
                Optional<Long> max =
                        dao.select(null).stream()
                                .filter( l ->
                                    ((ConcesionariaForm)l).getNombre() == form.getNombre() &&
                                        ((ConcesionariaForm)l).getConfig() == form.getConfig()
                                )
                                .map( l -> ((ConcesionariaForm) l).getId())
                                .max(Comparable::compareTo);

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    };

    private String generarCodigo(ConcesionariaForm form) {
        return "SUPER_CODIGO_SECRETO: " + form.getNombre() + " " + form.getConfig();
    }

    @Override
    public Function<Dao, Optional<Long>> registrarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            try {
                if(!exists(form).apply(dao) &&
                    form.getFechaRegistracion() == null  &&
                    form.getFechaAlta() == null) {
                    dao.insert(form);
                }
                Optional<Long> max = getIdOf(form).apply(dao);
                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }

    @Override
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas() {
        return dao -> {
            try {
                return
                    dao.select(null).stream().filter(c -> {
                        ConcesionariaForm conc = (ConcesionariaForm)c ;
                        return conc.getFechaAlta() != null && conc.getCodigo() != null;
                    }).map( c -> (ConcesionariaForm)c).collect(Collectors.toList());
            } catch (SQLException e) {
                return new ArrayList<>();
            }
        };
    }

    @Override
    public Function<Dao, Optional<String>> aprobarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            try {
                if(exists(form).apply(dao) && form.getFechaAlta() == null && form.getCodigo() == null) {
                    String codigo = generarCodigo(form);
                    form.setCodigo(codigo);
                    form.setFechaAlta(new Date(System.currentTimeMillis()));
                    dao.update(form);
                    return Optional.of(codigo);
                }
                else return Optional.of(form.getCodigo());
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }

    @Override
    public Function<Dao, Optional<Long>> configurarConcesionaria(ConcesionariaForm form) {
        return null;
    }
}
