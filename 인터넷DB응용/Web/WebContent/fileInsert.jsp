<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>파일 업로드 테스트 페이지</title>
</head>
<body style="padding: 30px;">
        <form action="upload.jsp" method="post" enctype="multipart/form-data">
            <div class="card" style="margin-bottom: 10px;">
                <div class="card-header">
                    상세 이미지
                </div>
                <div class="card-body">
                    <table>
                        <tr>
                            <td>
                                <img style="margin: 10px;" id="preview" src="#" alt="업로드 전단"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                   	카테고리 선택:<select class="form-control-file" name="P_CA">
   					<option value="all food">식당</option>
		  		    <option value="all hair">헤어샵</option>
					<option value="all toiec">토익</option>
  					</select>
                    <br>
					사업자등록번호:<input type="text" class="form-control-file" name="B_NO"><br>
					총 포인트:<input type="text" class="form-control-file" name="P_PO"><br> 
					전단지 파일업로드 : <input type="file" class="form-control-file" onchange="previewImage(this)" name="file"><br>
					<input style="margin-bottom: 5px;" type="submit" class="btn btn-primary btn-lg btn-block" name="전단사진 업로드"/>
     </td>
                        </tr>
                    </table>
                </div>
            </div>
            
	</form>
	<script>
	function previewImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview')
                        .attr('src', e.target.result)
                        .width(300)
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
	</script>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</html>
