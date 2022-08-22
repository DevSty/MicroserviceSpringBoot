package pe.com.stavaray.alumno.service;

import pe.com.stavaray.alumno.model.Alumno;

import java.util.List;

public interface IAlumnoService {

    public List<Alumno> findAll();
    public Alumno findById(Long id);
    public Alumno save(Alumno alumno);
    public Alumno modifyAlumno(Alumno alumno);
    public boolean deleteById(Long id);
    void registrarNotificacion(Alumno alumno);
    String validarAlumno(Alumno alumno);





}
