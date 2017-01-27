package com.pc.entity;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
@Cacheable
public class StudentEntity {

		public StudentEntity() {}
		
        @Id
        @Column(name = "ID")
        private Integer id;
        
        @Column(name = "NAME")
        private String name;
        
        @Column(name = "DEPARTMENT")
        private String department;
        
        @Column(name = "COLLEGE")
        private String college;
        
        @Column(name = "ADDRESS")
        private String address;               

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Integer  getId() {
			return id;
		}

		public void setId(Integer  id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getCollege() {
			return college;
		}

		public void setCollege(String college) {
			this.college = college;
		}

// Create Getters and Setters
}