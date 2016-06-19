$(document).ready(function(){
	
	$('.form-wrapper').submit(function(){
		if($.trim($('.searchLocation').val()).length == 0){
			$('.form-wrapper input.searchLocation').css("background", "#FFBABA");
//	            $('.searchLocation').addClass('text_error');
	            alert('Address filed is empty.');
	            return false;
		}
//		alert("length = "+$.trim($('.searchLocation').val()).length);
//		$('.searchLocation').blur(function() {
//			alert('value='+$.trim(this.value).length);
//		     if(!$.trim(this.value).length) { // zero-length string AFTER a trim
//		    	 alert('Text-field is empty.');
//		         return false;
////		            $(this).addClass('warning');
//		     }
//		});
	});
	
	
	
	//logic to display User's Profile and Logout buttons on header
	var hud = readCookie('hud');
	if(hud != -1 && hud !=null && hud.length >3){
		var arr = hud.split("|");
		console.log(arr[0]);
		$('.user-profile-link a').prepend(arr[0]);
		$('.login-link').hide();
		$('.user-profile-link').show();
		$('.logout-link').show();
	}
	else{
		$('.login-link').show();
		$('.user-profile-link').hide();
		$('.logout-link').hide();
	}
});

function search(){
	window.location='/HalalWeb/search';
}

function removeLoginToken(){
	deleteCookie("hst");
	deleteCookie("huat");
	deleteCookie("hud");
	window.location='/HalalWeb';
}

function readCookie(name) {
	 var nameEQ = encodeURIComponent(name) + "=";
	    var ca = document.cookie.split(';');
	    for (var i = 0; i < ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
	        if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
	    }
	    return null;
}

function createCookie(name , value, expiry, urlPath){
	document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value)+ expiry + "; path="+urlPath;
}

function deleteCookie(name){
	createCookie(name, "", -1,"/");
}
