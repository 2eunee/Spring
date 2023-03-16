<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
    
<%@ include file = "../includes/header.jsp" %>
    

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Board List Page
                       	 <button id='regBtn' type="button" class="btn btn-xs pull-right btn-danger"> Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                       
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="board">
	                                    <tr class="odd gradeX">
	                                        <td>${board.bno }</td>
	                                        <td><a class ="move" href='${board.bno }'>${board.title }</a></td> 
	                                        <!--  /board/get?bno= 경로를 지워준다 -> 경로를 타고 가는걸 지워줌 
	                                        		bno는 LONG이지만 String으로 인식함-->
	                                        <td>${board.writer }</td>
	                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }"/></td>
	                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }"/></td>
	                                        
	                                    </tr>
                                   </c:forEach>
                                </tbody>
                            </table>
                           
                           <!-- 검색조건 시작  -->
                           <div class='row'>
                           		<div class="col-lg-12">
                           		
                           		  <form id ="searchForm" action="/board/list" method='get'>
                           			<select name='type'>
	                           			<option value="" ${pageMaker.cri.type == null?'selected':''}>--</option>
	                           			<option value="T" ${pageMaker.cri.type eq 'T'?'selected':''}>제목</option>
	                           			<option value="C" ${pageMaker.cri.type eq 'C'?'selected':''}>내용</option>
	                           			<option value="W" ${pageMaker.cri.type eq 'W'?'selected':''}>작성자</option>
	                           			<option value="TC" ${pageMaker.cri.type eq 'TC'?'selected':''}>제목 or 내용</option>
	                           			<option value="TW" ${pageMaker.cri.type eq 'TW'?'selected':''}>제목 or 작성자</option>
	                           			<option value="TWC" ${pageMaker.cri.type eq 'TWC'?'selected':''}>제목 or 내용 or 작성자 </option>
                           			</select>
                           			
                           			<input type='text' name='keyword' value="${pageMaker.cri.keyword}">
                           			<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'>
                           			<input type='hidden' name='amount' value='${pageMaker.cri.amount }'>
                           			<button class='btn btn-default'>search</button>
                           		
                           		  </form>
                           		</div>                           
                           </div>
                           
                           
                           <!-- 페이지 처리 시작 -->
                           <div class="pull-right">
	                            <ul class="pagination">
	                              
	                              <c:if test="${pageMaker.prev }">
								    <li class="paginate_button previous">
								    <a href="${pageMaker.startPage -1 }">Previous</a>
								    <!--  ${pageMaker.startPage -1 } 이전페이지를 누르면 -1이 되면서 페이지를 바꿔준다-->
								    </li>
								  </c:if>
								  
								  <c:forEach var="num" begin="${pageMaker.startPage}"
								  				end="${pageMaker.endPage}">
								  <li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":""} ">
								  <a href="${num }">${num }</a>
								  </li>
								  </c:forEach>
		
							 	  <c:if test="${pageMaker.next }">
							 	  	<li class="paginate_button next">
							 	  	<a href="${pageMaker.endPage +1 }">Next</a>
							 	  	 <!--  ${pageMaker.endPage +1 } 다음페이지를 누르면 +1이 되면서 페이지를 바꿔준다-->
							 	  	</li>
							 	</c:if>
							 	</ul>
                           </div>
                           
                           <!-- 페이지 처리 끝 -->
                           
                           <form id="actionForm" action="/board/list" method="get">
                           	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                           	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                           	<!-- 전송을 하면 boardcontroller의 list가 받아준다 -->
                           
                           </form>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
    
    <!-- 모달시작 -->
    
    <!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Modal Header</h4>
	        </div>
	        <div class="modal-body">
	          <p>처리가 완료되었습니다.</p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	          <button type="button" class="btn btn-primary">Save Changes</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
    <!-- 모달 끝 -->
    
<script>
    
	    $(document).ready(function() {
	    		
	    	var result ="${result}"; //""로 감싸야지 공백이여도 괜찮다는 뜻이다.
	    	
	    	
	    	checkModal(result);
	    	
	    	//register에서 글 등록 후 뒤로가기 눌렀을 때 Modal창이 뜨지 않도록!
	    	history.replaceState({},null,null); //브라우저 주소창을 clear하는 기능
	    	
	    	function checkModal(result) {
				if(result === '' || history.state) {
					return;
			}

			if(parseInt(result) > 0) {
				$(".modal-body").html ("게시글" + parseInt(result) + "번이 등록 되었습니다");
			}	
			$("#myModal").modal("show");
			} 	
	    	$("#regBtn" ).on("click", function() { 		
	   			self.location="/board/register"
    	
    		});
	    	
	    	var actionForm = $("#actionForm");
	    	
	    	$(".paginate_button a").on("click",function(e){
	    		e.preventDefault();
	    		actionForm.attr("action","/board/list"); //뒤로가기 하면 get이 아닌 list로 바꿔주기 위해서 추가해줌
	    		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
	    		actionForm.submit();
	    	});
	    	
	    	
	    	$(".move").on("click", function(e) {
		
	    		e.preventDefault();
	    		actionForm.find("input[name='bno']").remove();
	    		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
	    		actionForm.attr("action","/board/get");
	    		actionForm.submit();
    
    		});
	    	
	    	var searchForm = $("#searchForm");
	    	
	    	$("#searchForm button").on("click",function(e) {
	    		
	    		if(!searchForm.find("option:selected").val()) {
	    			alert("검색종류를 선택하세요");
	    			
	    			return false;
	    	}
	    		if(!searchForm.find("input[name='keyword']").val()) {
	    			alert("키워드를 입력하세요");
	    			
	    			return false;
	    	}
	    		
	    		searchForm.find("input[name='pageNum']").val("1") //페이지 번호 1이 되도록 처리
	    			e.preventDefault();
	    		
	    		searchForm.submit();
	    			
  		
	    	});
	    	
});	
</script>
          
            
 <%@ include file="../includes/footer.jsp" %>
