<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>업로드 ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	
	<script
		  src="https://code.jquery.com/jquery-3.6.3.min.js"
		  integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
		  crossorigin="anonymous"></script>
	
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#uploadBtn").on("click", function(e){
			
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			
			console.log(files);
			
			
			for(var i = 0; i < files.length; i++) {
				
				formData.append("uploadFile", files[i]);
			
			}
			
			
			
			$.ajax({
		          url:'/uploadAjaxAction',
		          processData:false,
		          contentType:false,
		          data:formData,
		          type:'POST',
		          success:function(result){
		             alert("Uploaded");
		          }
		      
			});
		
		});
	});
	
	</script>
</body>
</html>