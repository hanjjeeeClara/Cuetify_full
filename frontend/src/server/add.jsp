<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import='java.util.*'%>
<%@ page import='java.sql.*'%>
<%@ page import='org.json.simple.*'%>


<%
	//데이터베이스 접속 정보
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pw = "1234";
	
    
	
	//접속
	Class.forName(driver);
	Connection db = DriverManager.getConnection(url, id, pw);

	// 파라매터 한글 처리
	request.setCharacterEncoding("utf-8");
	
	//브라우저가 전달하는 파라미터를 추출
	String input_name = request.getParameter("input_name");
	String input_kind = request.getParameter("input_kind");
	String input_price = request.getParameter("input_price");
    String input_date = request.getParameter("input_date");
    String input_seller = request.getParameter("input_seller");
    String input_buyer = request.getParameter("input_buyer");
	
	String sql = "insert into DATA (NAME, KIND, PRICE, DATE_, SELLER, BUYER) values (?,?,?,?,?,?)";
	
	PreparedStatement pstmt = db.prepareStatement(sql);
	pstmt.setString(1, input_name);
	pstmt.setString(2, input_kind);
	pstmt.setString(3, input_price);
    pstmt.setString(4, input_date);
    pstmt.setString(5, input_seller);
    pstmt.setString(6, input_buyer);
	
	pstmt.execute();
	db.close();
	
%>


{
	"result" : true

}

