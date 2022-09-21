package com.example.demo2.repository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.demo2.domain.Data;


@Repository
public class JdbcTemplateDataRepository implements DataRepository{


    private final JdbcTemplate jdbcTemplate;


    
    @Autowired
    //데이터소스 인젝션
    public JdbcTemplateDataRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    @Override
    public Data save(Data data) {

        

        // SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // jdbcInsert.withTableName("data").usingColumns("name","kind",
        // "price","date","seller","buyer");
        

        // Map<String, Object> parameters = new HashMap<>();
        // parameters.put("name", data.getName());
        // parameters.put("kind", data.getKind());
        // parameters.put("price", data.getPrice());
        // parameters.put("date", data.getDate());
        // parameters.put("seller", data.getSeller());
        // parameters.put("buyer", data.getBuyer());

        // Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // data.setId(key.longValue());

        jdbcTemplate.update("insert into data values(?,?,?,?,?,?)",
            data.getName(), data.getKind(), data.getPrice(), data.getDate(), data.getSeller(), data.getBuyer());

        
        return data;
    }

    @Override
    public Integer delete(Integer id){
        
        jdbcTemplate.update("delete from data where id=?",
            id);

        return id;
    }

    @Override
    public Data modify(Data data){
        jdbcTemplate.update("update data set name=?, kind=?, price=?, date=?, seller=?, buyer=?, id=? where id = ?",
            data.getName(), data.getKind(), data.getPrice(), data.getDate(), data.getSeller(), data.getBuyer(), data.getId(), data.getId());

        return data;
    }

 
    @Override
    public List<Data> findAll() {

        return jdbcTemplate.query("select * from data", dataRowMapper());
        
    }

    private RowMapper<Data> dataRowMapper(){

        return (rs, rowNum) -> {
            Data data = new Data();
            
            data.setName(rs.getString("name"));
            data.setKind(rs.getString("kind"));
            data.setPrice(rs.getString("price"));
            data.setDate(rs.getString("date"));
            data.setSeller(rs.getString("seller"));
            data.setBuyer(rs.getString("buyer"));
            data.setId(rs.getInt("id"));
            
            return data;
        };

    }
    
}
