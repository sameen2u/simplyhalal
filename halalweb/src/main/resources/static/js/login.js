

$(document).ready(function(){
	$(".email-signup").hide();
	$("#signup-box-link").click(function(){
	  $(".email-login").fadeOut(100);
	  $(".email-signup").delay(100).fadeIn(100);
	  $("#login-box-link").removeClass("active");
	  $("#signup-box-link").addClass("active");
	});
	$("#login-box-link").click(function(){
	  $(".email-login").delay(100).fadeIn(100);;
	  $(".email-signup").fadeOut(100);
	  $("#login-box-link").addClass("active");
	  $("#signup-box-link").removeClass("active");
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
	       url: $('#loginApiUrl').val(),
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
	    	   			$('.login-error').append('Some internal error, please refresh page and try again Later');
	    	   		}
	    	   		else{
	    	   			$('.login-error').append(data.description);
	    	   		}		    	   		
	    	   		console.log('append error msg = '+data.description);
	    	   		$('#ajax_loading').hide();
	    	   	}
	       },
	       beforeSend: function(){
	    	   console.log('BeforeSend url -'+$('#loginApiUrl').val());
	    	   if(navigator.cookieEnabled ==false){
	    		   window.location='/HalalWeb/account/enableCookie';
	    	   }		    	   
	       },
	       error: function(jqXHR, textStatus, errorThrown){
	    	   $('.login-error').append('Some internal error, please refresh page and try again Later');
	    	   console.log("Something really bad happened- "+textStatus+", ERROR - " + errorThrown);
	       }
	   });
}

function signUp(){
	var email = $('#user-email').val();
	var password = $('#user-password').val();
	var formObject =  {"fullName": $('#user-full-name').val(), "email": email, "password": password};
	var jsonString = JSON.stringify(formObject);
   	$.ajax({
	       url: 'http://localhost:9191/HalalApi/v1/account/signup',
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
	    	   		if(data.email == $('#username').val()){
	    	   			$('.login-error').append('Some internal error, please refresh page and try again Later');
	    	   		}
	    	   		else{
	    	   			$('.login-error').append(data.description);
	    	   		}		    	   		
	    	   		console.log('append error msg = '+data.description);
	    	   		$('#ajax_loading').hide();
	    	   	}
	       },
	       beforeSend: function(){
	    	   console.log('BeforeSend jsonString -'+jsonString);
	    	   if(navigator.cookieEnabled ==false){
	    		   window.location='/HalalWeb/account/enableCookie';
	    	   }		    	   
	       },
	       error: function(jqXHR, textStatus, errorThrown){
	    	   $('.login-error').append('Some internal error, please refresh page and try again Later');
	    	   console.log("Something really bad happened- "+textStatus+", ERROR - " + errorThrown);
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

