console.log("Reply Module..........");

var replyService  =  ( function(){

   function add(reply, callback, error){
        console.log("add reply.......");

        $.ajax({
            type : 'post',
            url : "/replies/new",
            data  : JSON.stringify(reply),
            contentType : "application/json; charset=utf-8",
        
            success : function(result, status, xhr){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error)
                error(er);
            }
        });
   } //end add
   
   function getList(param, callback, error) {
    
    		var bno = param.bno;
    		var page = param.page || 1;
    
    		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
    				function(data) {
    					if (callback) {
    						callback(data);
    					}
    				}).fail(function(xhr, status, err) {
    			if (error) {
    				error();
    			}
    		});  
   } //end getList
    
  function remove(rno, replyer, callback, error)   {
    
    $.ajax({
        type : "delete",
        url : "/replies/" + rno,    //        /replies/2
        data: JSON.stringify({rno:rno, replyer:replyer}),
        contentType : "application/json; charset=utf-8",
        success : function(deleteResult, status, xhr){
            if(callback){
                callback(deleteResult);
            }
            
            location.reload(); //댓글이 하나가 남으면 새로고침하기 전까지는 삭제가 안됨, reload를 넣어줘서 페이지를 새로고침 할 수 있게 함
        },
        error : function(xhr, status,err){
            if(error){
                error(err);
            }
        }
    });  
  }  //end remove

    function update(reply, callback, error){
        console.log(reply);
        $.ajax({
            type : "put",
            url : "/replies/" + reply.rno, 
            data : JSON.stringify(reply) ,    
            contentType : "application/json; charset=utf-8",

            success : function(Result, status, xhr){
                if(callback){
                    callback(Result);
                }
            },
            error : function(xhr, status,err){
                if(error){
                    error(err);
                }
            }
        }); 
    }  //end update

    function get(rno, callback, error){
        $.get( "/replies/" + rno + ".json", function(result){
            if (callback){
                callback(result);
            }
        }).fail(function(xhr, status, err){
            if(error){
                error(err);
            }
        });
    } //end get
  
    function displayTime(timeValue){
        var today = new Date();
        var gap = today.getTime() - timeValue;
        var dateObj = new Date(timeValue);
        var str= "";

        if(gap < (1000*60* 60*24)){
            var hh = dateObj.getHours();
            var mi = dateObj.getMinutes();
            var ss = dateObj.getSeconds();

            return [(hh>9? ' ' : '0')+hh, ':' , (mi>9? '' : '0')+mi, ':' , (ss>9? '' : '0')+ss ].join('');
        }else{
            var yy = dateObj.getFullYear();
            var mm = dateObj.getMonth() +1;
            var dd = dateObj.getDate();
            return [yy, '/', (mm>9? '' : '0')+mm, '/', (dd>9? '' : '0')+dd ].join('');
        }
    }  //end displayTime
    
    function getList(param, callback, error){
    	var bno = param.bno;
    	var page = param.page || 1;
    	
    	$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
    		function(data){
    			if(callback){
    				callback(data.replyCnt, data.list);
    			}
    		}).fail(function(xhr, status, err){
    			if(error){
    				error(err);
    			}
    		});
    }  //end getList


   return {
        add : add,
        getList : getList,
        remove : remove,
        update : update,
        get : get,
        displayTime : displayTime
   }

})();
