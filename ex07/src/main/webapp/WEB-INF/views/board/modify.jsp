<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../includes/header.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
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
                        
                        	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	                        
	                        <input type="hidden" name="pageNum" value="${cri.pageNum}">
                 		 	<input type="hidden" name="amount" value="${cri.amount}">
                 		 	<input type="hidden" name="type" value="${cri.type}">
                 		 	<input type="hidden" name="keyword" value="${cri.keyword}">
                 		 	
	                        <div class="form-group">
	                        	<label>Bno</label> 
	                        	<input class="form-control" name="bno" value="${board.bno}" readonly>
	                        </div>

	                        <div class="form-group">
	                        	<label>Title</label> 
	                        	<input class="form-control" name="title" value="${board.title}" >
	                        </div>
	                        <div class="form-group">
	                        	<label>Text Area</label>
	                        	<textarea rows="3" class="form-control" name="content" >${board.content}</textarea> 
	                        </div>
	                        <div class="form-group">
	                        	<label>Writer</label> <input class="form-control" name="writer" value="${board.writer}" readonly>
	                        </div>

	                        <div class="form-group">
	                        	<input type="hidden" class="form-control" name="regDate" 
	                        	value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate}"/>' readonly>
	                        </div>

	                        <div class="form-group">
	                        	<input type="hidden" class="form-control" name="updateDate" 
	                        	value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>' readonly>
	                        </div>
	                        
	                         
	                        <sec:authentication property="principal" var="pinfo"/>
	                       
	                        <sec:authorize access="isAuthenticated()"> <!-- 인증성공시 -->
	                        <c:if test="${pinfo.username eq board.writer }"> 
	                        <!-- username과 작성자와  동일할 경우 슈정버튼을 보여준다 -->
	                        
		                        <button type="submit" data-oper='modify' class ="btn btn-primary" >Modify</button>
		                        <button type="submit" data-oper='remove' class ="btn btn-danger" >Remove</button>
		                        <button type="submit" data-oper='list' class ="btn btn-info" >List</button>
                        	
                        	</c:if>
	                        </sec:authorize>
                        
                        </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<script>
	$(document).ready(function(){
		var formObj = $("form");
		
		$('button').on("click",function(e){
			e.preventDefault();
			var operation = $(this).data("oper");
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list'){
				formObj.attr("action", "/board/list").attr("method","get");
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var typeTag = $("input[name='type']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(typeTag);
				formObj.append(keywordTag);
			}
			formObj.submit();
			
		});
	});
</script>

<%@ include file="../includes/footer.jsp" %>
    