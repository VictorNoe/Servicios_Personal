package mx.edu.utez.personal4b.model.personal;

import mx.edu.utez.personal4b.model.Repository;
import mx.edu.utez.personal4b.model.position.BeanPosition;
import mx.edu.utez.personal4b.utils.MySQLConnection;
import mx.edu.utez.personal4b.utils.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPersonal implements Repository<BeanPersonal> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();

    @Override
    public List<BeanPersonal> findAll() {
        List<BeanPersonal> personal= new ArrayList<>();
        BeanPersonal person = null;
        BeanPosition position = null;
        try{
            conn = client.getConnection();
            String query = "SELECT pe.*,po.description FROM personal pe " +
                    "JOIN position po ON po.id = pe.position_id;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                person = new BeanPersonal();
                position = new BeanPosition();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setLastname(rs.getString("lastname"));
                person.setBirthday(rs.getString("birthday"));
                person.setSalary(rs.getDouble("salary"));
                position.setDescription(rs.getString("description"));
                person.setPosition(position);
                personal.add(person);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return personal;
    }

    @Override
    public BeanPersonal findById(Long id) { //findAllByName
        BeanPersonal person = null;
        BeanPosition position = null;
        try{
            conn = client.getConnection();
            String query = "SELECT pe.*,po.description FROM personal pe " +
                    "JOIN position po ON po.id = pe.position_id where pe.id=?;";
            ps = conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if (rs.next()){ //si existe el siguiente
                person = new BeanPersonal();
                position = new BeanPosition();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setLastname(rs.getString("lastname"));
                person.setBirthday(rs.getString("birthday"));
                person.setSalary(rs.getDouble("salary"));
                position.setDescription(rs.getString("description"));
                person.setPosition(position);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE, "Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        System.out.println(person);
        return person;
    }

    //Content-Type
    //application/json

    @Override
    public Response<BeanPersonal> save(BeanPersonal person) {
        try {
            conn = client.getConnection();
            String query = "INSERT INTO personal(name,surname,lastname,birthday,salary,position_id) " +
                    "VALUES (?,?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1,person.getName());
            ps.setString(2,person.getSurname());
            ps.setString(3,person.getLastname());
            ps.setString(4,person.getBirthday());
            ps.setDouble(5,person.getSalary());
            ps.setLong(6,person.getPosition().getId());
            if (ps.executeUpdate()==1){
                return new Response<BeanPersonal>(200,"Registrado correctamente",person,false);
            }else{
                return new Response<>(200,"Error al registrar",person,true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName())
                    .log(Level.SEVERE,"Error- -> save" + e.getMessage());
            return new Response<>(400, "Error con el servidor",null,true);
        }finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanPersonal> update(BeanPersonal person) {
        try {
            conn = client.getConnection();
            String query = "UPDATE personal SET name = ?, suname = ?, lastname = ?, birthday = ?, salary = ?, position_id = ? WHERE id = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getLastname());
            ps.setString(4, person.getBirthday());
            ps.setDouble(5, person.getSalary());
            ps.setLong(6, person.getPosition().getId());
            ps.setLong(7, person.getId());
            if (ps.executeUpdate() == 1){
                return new Response<>(200, "Actualizacion correcta", person, false);
            } else {
                return new Response<>(400, "Actualizacion incorrecta", person, true);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoPersonal.class.getName()).log(Level.SEVERE, "Error - update: "+ e.getMessage());
            return new Response<>(400, "Error al actualizar", null, true);
        } finally {
            client.close(conn, ps, rs);
        }
    }

    @Override
    public Response<BeanPersonal> remove(Long id) {
        try{
            conn = client.getConnection();
            return null;
        }
    }
}
