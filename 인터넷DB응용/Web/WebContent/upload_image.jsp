<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.naming.*,javax.sql.*"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="java.sql.*"%>



<!DOCTYPE html>
<html>
<head>
<%
	// request.getRealPath("상대경로") 를 통해 파일을 저장할 절대 경로를 구해온다. 
	// 운영체제 및 프로젝트가 위치할 환경에 따라 경로가 다르기 때문에 아래처럼 구해오는게 좋음 
	String uploadPath = request.getSession().getServletContext().getRealPath("/images/post");

	int maxSize = 1024 * 1024 * 10; // 한번에 올릴 수 있는 파일 용량 : 10M로 제한 

	String b_no = "";

	String P_CA = "";
	String p_po = "";

	MultipartRequest multi = null;
	File file = null;

	try {
		// request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책 
		multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

		P_CA = multi.getParameter("P_CA");
		p_po = multi.getParameter("P_PO");
		int B_NO = ((Integer) session.getAttribute("B_NO")).intValue();
		int P_PO = Integer.parseInt(p_po);
		file = multi.getFile("file");

		// 전송한 전체 파일이름들을 가져옴 
		//Enumeration files = multi.getFileNames(); 

		/* while(files.hasMoreElements()){ 
		
		originalName1 = multi.getOriginalFileName(file1); 
		// 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데 
		// 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리) 
		fileName1 = multi.getFilesystemName(file1); 
		// 파일 타입 정보를 가져옴 
		fileType = multi.getContentType(file1); 
		// input file name에 해당하는 실재 파일을 가져옴 
		File file = multi.getFile(file1); 
		// 그 파일 객체의 크기를 알아냄 
		fileSize = file.length(); 
		} */
		// 커넥션 연결 
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273",
				"201630273");
		PreparedStatement pstmt = null;
		String filename = multi.getOriginalFileName("file");
		byte[] buf = FileUtils.readFileToByteArray(file);
		pstmt = conn.prepareStatement("INSERT INTO POST  VALUES (POST_SEQ.nextval,0,?,?,?,500,?)");

		for (int i = 0; i <= 10; i++) {
			pstmt.setInt(1, B_NO);
			pstmt.setInt(2, P_PO);
			pstmt.setBytes(3, buf);
			pstmt.setString(4, P_CA);
			pstmt.addBatch();
			pstmt.clearParameters();
			if (i % 2 == 0) {				
				System.out.println("batch-excute:" + i);
				pstmt.executeBatch();
				pstmt.executeUpdate();
			}
		}
		pstmt.executeBatch();
		pstmt.executeUpdate();		
	} catch (Exception e) {
		e.printStackTrace();
	}
	String h1 = "파일업로드 성공";
%>
<script>alert("<%=h1%>");
	document.location.href = "/Postman/busi_login.jsp"
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload_image</title>
</head>
<body>
</body>
</html>
