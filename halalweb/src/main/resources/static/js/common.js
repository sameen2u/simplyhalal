$(document).ready(function(){
	
	//Localize the address functionality shared for desktop and mobile 
/*	if (navigator.geolocation) {
		console.log('in geolocation');
        navigator.geolocation.getCurrentPosition(function(position) {
        	var lat = position.coords.latitude;
        	var lng = position.coords.longitude;
        	var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?latlng='+lat+','+lng+'&language=en';
            $.getJSON(GEOCODING).done(function(location) {
            	console.log('length-'+location.results.length);
            	if(location.results.length >0 ){
            		var locality =location.results[0].address_components[3].long_name+', '+location.results[0].address_components[4].long_name+', '+location.results[0].address_components[6].long_name;
                	$('.searchLocation').val(locality);
                    console.log('Locality -'+location.results[0].address_components[3].long_name+', '+location.results[0].address_components[4].long_name)
            	}
            })

        	
        }, function(error) {
            alert('Error');         
        },{timeout:5000});
	    }else{
	        alert('no geolocation support');
	    }*/
	
	//Google address auto fill functionality shared for desktop and mobile
    google.maps.event.addDomListener(window, 'load', intilize);
    function intilize() {
        var autocomplete = new google.maps.places.Autocomplete(document.getElementById("txtautocomplete"), {types: ['geocode'],componentRestrictions: {country: "IN"}});
        google.maps.event.addListener(autocomplete, 'place_changed', function () {
        var place = autocomplete.getPlace();
        document.getElementById('lattitude').value = place.geometry.location.lat();
        document.getElementById('longitude').value = place.geometry.location.lng();
        });
    };
    
    
		//Null check for address search field
		$('.form-wrapper').submit(function(){
			if($.trim($('.searchLocation').val()).length == 0){
				$('.form-wrapper input.searchLocation').css("background", "#FFBABA");
	//	            $('.searchLocation').addClass('text_error');
		            alert('Address filed is empty.');
		            return false;
			}
			
			Array.prototype.forEach.call(document.querySelectorAll('.clearable-input>[data-clear-input]'), function(el) {
				  el.addEventListener('click', function(e) {
				    e.target.previousElementSibling.value = '';
				  });
				});
	
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

//logout functionality
function removeLoginToken(){
	deleteCookie("hst");
	deleteCookie("huat");
	deleteCookie("hud");
//	var baseUrl = document.getElementById('baseUrl').value;
//	alert('redirecting - '+baseUrl);
	window.location='/halalweb';
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
