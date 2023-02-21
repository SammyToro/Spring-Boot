package com.apress.todo.repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apress.todo.domain.ToDo;

@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    private static final String SQL_INSERT = "insert into todo (id,"+
        "description,created,modified,completed) values (:id,:description,:created,"+
        ":modified,:completed)";

    private static final String SQL_QUERY_FIND_ALL = "select id,description, created,"+
        "modified, completed from todo";    

    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + 
        " where id  = :id";
        
    private static final String SQL_UPDATE = "update todo set description = :description"+
        " modified = :modified, completed = :completed where id = :id";
        
    private static final String SQL_DELETE = "delete from todo where id = :id";
    
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ToDo> toDoRowMapper = (ResultSet rs, int rowNum) -> {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getString("id"));
        toDo.setDescription(rs.getString("description"));
        toDo.setModified(rs.getTimestamp("modified").toLocalDateTime());
        toDo.setCreated(rs.getTimestamp("created").toLocalDateTime());
        toDo.setCompleted(rs.getBoolean("completed"));
        return  toDo;
    };

    @Override
    public ToDo save(final ToDo domain) {
        // TODO Auto-generated method stub
        ToDo result = findById(domain.getId());
        if(result != null){
            result.setDescription(domain.getDescription());
            result.setModified(LocalDateTime.now());
            result.setCompleted(domain.isCompleted());
            return upsert(result, SQL_UPDATE);
        }
        return upsert(domain, SQL_INSERT);
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        // TODO Auto-generated method stub
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo domain) {
        // TODO Auto-generated method stub
        Map<String,String> namedParameters = Collections.singletonMap("id", domain.getId());
        this.jdbcTemplate.update(SQL_DELETE, namedParameters);
        
    }

    @Override
    public ToDo findById(String id) {
        // TODO Auto-generated method stub
        try {
           Map<String,String> namedParameters = Collections.singletonMap("id", id);
           return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, namedParameters, toDoRowMapper); 
            
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            return null;
        }
    }

    @Override
    public Iterable<ToDo> findAll() {
        // TODO Auto-generated method stub
        return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, toDoRowMapper);
    }

    private ToDo upsert(final ToDo toDo, final String sql){
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("id", toDo.getId());
        namedParameters.put("description", toDo.getDescription());
        namedParameters.put("created", Timestamp.valueOf(toDo.getCreated()));
        namedParameters.put("modified", Timestamp.valueOf(toDo.getModified()));
        namedParameters.put("completed",toDo.isCompleted());

        this.jdbcTemplate.update(sql, namedParameters);
        return findById(toDo.getId());
    }
    
}
