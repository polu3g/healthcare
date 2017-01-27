/**
 * 
 */
package com.pc;

import com.pc.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.cache.annotation.*;
import java.util.List;
import com.pc.entity.StudentEntity;

/**
 * @author chakrapa
 *
 */
public interface SQLSrvRepository extends JpaRepository <StudentEntity, Integer> {
	@Cacheable("studentsList")
	public List<StudentEntity> findAll();

}
