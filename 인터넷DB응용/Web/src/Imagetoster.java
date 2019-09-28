package pknu.it;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Imagetoster
 */
@WebServlet("/Imagetoster")
public class Imagetoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Imagetoster() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 	{
		// TODO Auto-generated method stub
		// 이미지 토스터 실패...ㅠㅠ
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@db.pknu.ac.kr:1521:xe", "db201630273", "201630273");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		String post = request.getParameter("post");
		int p_no = 0;
		if (post == null || post.trim().equals("")) {
			// 인자가 없거나 공백인 경우 전송하지 않는다.
		} else {
			try {
				p_no = Integer.parseInt(post);

				st = conn.prepareStatement("select image from POST where p_no=?");
				st.setInt(1, p_no);
				rs = st.executeQuery();
				if (rs.next()) {
					InputStream in = rs.getBinaryStream("image");
					ServletOutputStream sout = response.getOutputStream();
					int c;
					while ((c = in.read()) != -1) {
						sout.write(c);
					}
					sout.close();
					in.close();
				}
				rs.close();
				st.close();
			} catch (Exception e) {
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
