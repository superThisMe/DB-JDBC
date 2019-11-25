package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

	public List<EmployeeVO> selectEmployeesByName(String name) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 조회된 데이터를 저장하기 위한 리스트
		ArrayList<EmployeeVO> employees = new ArrayList<>();
		
		try {
			// 1. JDBC 드라이버 준비
			Class.forName("oracle.jdbc.OracleDriver");
			// DriverManager.registerDriver(new OracleDriver());

			// 2. 연결 객체 만들기 (연결)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.6.31:1521:xe", "hr", "9922");

			// 3. SQL 작성 + 명령 객체 만들기
			String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL " + "FROM EMPLOYEES "
					+ "WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ?"; // ? : 데이터가 채워질 곳
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name + "%"); // 첫번째 ?를 채울 데이터
			pstmt.setString(2, name + "%"); // 두번째 ?를 채울 데이터

			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : SELECT를 위한 명령

			// 5. 명령 실행 결과가 있으면 처리 (SELECT 인 경우)
			while (rs.next()) { // 결과 집합의 다음 행으로 이동 (없으면 false)
				EmployeeVO employee = new EmployeeVO();
				employee.setEmployeeNo(rs.getInt(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setEmail(rs.getString(4));
				
				employees.add(employee);
			}

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지 출력
		} finally {
			// 6. 연결 종료
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				pstmt.close();
			} catch (Exception ex) {
			}
			try {
				conn.close();
			} catch (Exception ex) {
			}
		}
		return employees;

	}

}
