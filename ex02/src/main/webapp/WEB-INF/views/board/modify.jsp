<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<%@ include file = "../includes/header.jsp" %>>
    
			<div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"> Board Modify Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                          	
                          	<form role="form" action="/board/modify" method="post">
                          	
                          	<!-- 수정 삭제처리 추가 -->
                          	 <input type="hidden" name="pageNum" value="${cri.pageNum}">
                          	 <input type="hidden" name="amount" value="${cri.amount}">
                          	
                          	<!-- 수정 삭제처리 추가 -->
                          	
                          	<div class="form-group">
	                            <label>Bno</label> <input name="bno" class="form-control" 
	                            value="${board.bno}" readonly="readonly">
	                        </div>   
	                        
	                        <div class="form-group">
	                            <label>Title</label> <input name="title" class="form-control" 
	                            value="${board.title}">
	                        </div>  
	                           
	                        <div class="form-group">
	                            <label>Text Area</label> <textarea rows="3" name="content" 
	                            class="form-control">${board.content}</textarea>
	                        	
	                        </div>   
	                           
	                        <div class="form-group">
	                            <label>Writer</label><input name="writer" class="form-control"
	                            value="${board.writer}" readonly="readonly">
	                        </div>  
	                        
	                        <div class="form-group">
	                           <!--  <label>Update Date</label> --><input name="hidden"  class="form-control"
	                            value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.updateDate}"/>' readonly="readonly">
	                        </div>    
	                            
	                            <button type = "submit" data-oper='modify' class="btn btn-default">Modify</button>
	                           	<button type = "submit" data-oper='remove' class="btn btn-danger">Remove</button>
	                            <button type = "submit" data-oper='list' class="btn btn-info">List</button>
	                            <!-- onclick를 쓰지 않는 이유 -->
	                            <!-- 현재 post방식으로 전송을 하는데 링크전송은 get방식을 사용함 -->
	                            <!-- Boardcontroller에 post를 하나 더 생성해야하는 번거로움 -->
	                            <!-- 마지막 list방식은 get방식이지만 통일성을 위해서 자바스크립트로 빼놓음 -->
	                            
	                            
                            
                            </form>
                           
                         </div>   
                        </div>
                        <!-- /.panel-body -->
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            
   <script>
   
   $(document).ready(function() { //ready 다음에 실행될 함수를 익명으로 function() 정의함 
	   
	   var formObj = $("form");
	   
	   $('button').on("click", function (e) {//function (e) 익명함수 / (e)는 매개함수 이벤트에 대한 정보가 담겨있음
		
		   e.preventDefault(); //버튼을 눌러도 화면이 넘어가지 않도록 함 => 정보가 바뀌기전에 화면이 바뀌는걸 방지하기 위함
		   
		   var operation = $(this).data("oper");
		   
		   console.log(operation);
		   
		   if(operation === 'remove') {
			   formObj.attr("action","/board/remove");
		   }else if(operation === 'list'){
			   
			  // self.location ="/board/list";
			  
			  formObj.attr("action","/board/list").attr("method","get");
			  var pageNumTag = $("input[name='pageNum']").clone(); //clone();으로 복사를 해둔다 
			  var amountTag = $("input[name='amount']").clone();
			  
			  formObj.empty(); //empty는 비우는 것
			  formObj.append(pageNumTag); // clone();으로 복사한 것을 붙여넣기 한다
			  formObj.append(amountTag);
			  
		   }
		   
		   formObj.submit();
		   
		});
	
});
   </script>
          
            
 <%@ include file="../includes/footer.jsp" %>>
