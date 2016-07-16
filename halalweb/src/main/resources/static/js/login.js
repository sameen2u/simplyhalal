

$(document).ready(function(){
	$(".email-signup").hide();
	$("#signup-box-link").click(function(){
	  $(".email-login").fadeOut(100);
	  $(".email-signup").delay(100).fadeIn(100);
	  $("#login-box-link").removeClass("active");
	  $("#signup-box-link").addClass("active");
	  $('.login-error').text("");
	});
	$("#login-box-link").click(function(){
	  $(".email-login").delay(100).fadeIn(100);;
	  $(".email-signup").fadeOut(100);
	  $("#login-box-link").addClass("active");
	  $("#signup-box-link").removeClass("active");
	  $('.login-error').text("");
	});
	
	//Login functionality
	  $('.submitButton').on('click',function(){
			var username = $('#username').val();
			var password = $('#password').val();
			var rememberMe = false;
			if($('#rememberMe').is(":checked")){
		  		rememberMe = true;
		  	}
		  login(username, password, rememberMe);
	    });
});

function login(username, password, rememberMe){
	//$('#ajax_loading').show();
  	var rememberMe=false;
	var formObject =  {"username": username, "password": password, "rememberMe":rememberMe};
	var jsonString = JSON.stringify(formObject);
   	$.ajax({
	       url: $('#loginApiUrl').val()+"/v1/account/login",
	       type: 'post',
	       contentType: "application/json; charset=utf-8",
	       data: jsonString,
	       encode:true,
	       success: function(data) {
	    	   	if(data.email == $('#username').val()){
	    	   		createLoginCookie(data,rememberMe);
	    	   		$('#ajax_loading').hide();
	    	   		window.location='/halalweb';
	    	   	}
	    	   	else {
	    	   		if(data.email == $('#username').val()){
	    	   			$('.login-error').text('Some internal error, please refresh page and try again Later');
	    	   		}
	    	   		else{
	    	   			$('.login-error').text(data.description);
	    	   		}		    	   		
	    	   		console.log('append error msg = '+data.description);
	    	   		$('#ajax_loading').hide();
	    	   	}
	       },
	       beforeSend: function(xhr, opts){
	    	   console.log('url -'+$('#loginApiUrl').val()+'/v1/account/login');
	    	   if($.trim($('#username').val()).length == 0 || $.trim($('#password').val()).length == 0){
	    		   alert('Username or Password is empty');
	    		   xhr.abort();
	    	   }
	    	   if(navigator.cookieEnabled ==false){
	    		   window.location='/HalalWeb/account/enableCookie';
	    	   }		    	   
	       },
	       error: function(xhr, textStatus, errorThrown){
	    	   console.log('error status-'+textStatus+' error -'+errorThrown);
	    	   var errorResponse = $.parseJSON(xhr.responseText);
	    	   if(errorResponse.errorDescription.length != 0){
	    		   $('.login-error').text(errorResponse.errorDescription);
	    	   }
	    	   else{
	    		   $('.login-error').text('Some internal error, please refresh page and try again Later');
	    	   }
	    	  // console.log("Something really bad happened- "+textStatus+", ERROR - " + errorResponse.errorDescription);
	       }
	   });
}

function signUp(){
	var email = $('#user-email').val();
	var password = $('#user-password').val();
	var formObject =  {"fullName": $('#user-full-name').val(), "email": email, "password": password};
	var jsonString = JSON.stringify(formObject);
   	$.ajax({
	       url: $('#loginApiUrl').val()+'/v1/account/signup',
	       type: 'post',
	       contentType: "application/json; charset=utf-8",
	       data: jsonString,
	       encode:true,
	       success: function(data) {
	    	   	if(data == 'success'){
	    	   		alert("Email "+email+" has been registered, please login!")
	    	   		window.location='/halalweb/login';
	    	   	}
	    	   	else {
	    	   		if(data.description.length == 0){
	    	   			$('.login-error').text('Some internal error, please refresh page and try again Later');
	    	   		}
	    	   		else{
	    	   			$('.login-error').text(data.description);
	    	   		}		    	   		
	    	   		console.log('append error msg = '+data.description);
	    	   		$('#ajax_loading').hide();
	    	   	}
	       },
	       beforeSend: function(){
	    	   alert($('#loginApiUrl').val()+'/v1/account/signup');
	    	   if(navigator.cookieEnabled ==false){
	    		   window.location='/HalalWeb/account/enableCookie';
	    	   }		
	    	   if($.trim($('#user-full-name').val()).length == 0 || $.trim($('#user-email').val()).length == 0 || $.trim($('#user-password').val()).length == 0){
	    		   $('.login-error').text('Please fill all the field');
	    		   xhr.abort();
	    	   }
	       },
	       error: function(xhr, textStatus, errorThrown){
	    	   var errorResponse = $.parseJSON(xhr.responseText);
	    	   if(errorResponse.description.length != 0){
	    		   $('.login-error').text(errorResponse.description);
	    	   }
	    	   else{
	    		   $('.login-error').text('Some internal error, please refresh page and try again Later');
	    	   }
	       }
	   });
}

function createLoginCookie(data, rememberMe){
	//expiry in mins
	if(data.sessionToken.length >=1){
		createCookie('hst', data.sessionToken, 30, '/');
	}
	//expires in 30 days
	if(rememberMe == true){
		createCookie('huat', data.userActivityToken, 30*24*60*30, '/');
	}
	if(data.name.length >= 1){
		createCookie('hud', data.name+'|'+data.userId, 30, '/');
	}
}

