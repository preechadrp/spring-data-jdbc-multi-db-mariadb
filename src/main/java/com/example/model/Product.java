package com.example.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCT") // ระบุชื่อตาราง (ถ้าชื่อเดียวกับ Class ไม่ต้องใส่ก็ได้)
public record Product(
		@Id Long id,

		// บอก Spring Data JDBC ว่าฟิลด์นี้คือ name ใน Database
		@Column("name")
		String name,

		@Column("price")
		BigDecimal price,

		// บอก Spring Data JDBC ว่าฟิลด์นี้คือ InsertDateTime ใน Database 
		// ถ้าไม่ระบุมันจะหาชื่อ insert_date_time เพราะมันจะทำการแปลงชื่อฟิลด์เป็น snake_case โดยอัตโนมัติ
		// เนื่องจากเราตั้งชื่อฟิลด์เป็นแบบ CamelCase แต่ใน Database เราตั้งชื่อเป็น InsertDateTime (PascalCase) ดังนั้นเราต้องระบุชื่อคอลัมน์ให้ตรงกัน
		@Column("insertDateTime") LocalDateTime insertDateTime) {
	// ใช้ Java Record จะช่วยให้โค้ดคลีนขึ้นมาก ไม่ต้องเขียน Getter/Setter/ToString

	// สร้าง Wither Method สำหรับอัปเดตข้อมูล (เนื่องจาก Record เป็น Immutable)
	public Product withId(Long id) {
		return new Product(id, this.name, this.price, this.insertDateTime);
	}
}