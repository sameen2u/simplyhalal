$(document).ready(function(){
	
	/*	Location related functions start*/
	//Localize the address functionality shared for desktop and mobile 
	$('.searchLocation').val('Pune, Maharashtra');//***IMPORTANT : remove this line if uncommenting below code
	/*if (navigator.geolocation) {
		console.log('in geolocation');
        navigator.geolocation.getCurrentPosition(function(position) {
        	var lat = position.coords.latitude;
        	var lng = position.coords.longitude;
        	//manually assigning the address to camp pune, replace it later with actual lat and lng
        	var GEOCODING = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=18.5122306,73.88600999999994&language=en';
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
    /*	Location related functions ends*/
    
    /*	Login related functions starts*/
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
	 /*	Login related functions starts*/
	
	/*	search page start*/
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
	
	//filter update ajax call
	$('.distance-filter').on('click',function(){
		console.log(window.location.search);
		var distance = $("input[name=distance-filter]:checked").val();
		query = replaceQueryParam('page', '1', window.location.search);
		 $.ajax({
		        type: "GET",
		        //appending the distance in the query param to update filter
		        url: "/halalweb/search/ajax"+query+"&distance="+distance,
		        beforeSend: function(){
		        	$('.loading-icon').show();
		        },
		        success: function(response){
		        	 var str = replaceQueryParam('distance', distance, query);
		        	 window.location = window.location.pathname + str;
			        // we have the response
			        $('.Popular-Restaurants-content').html(response);
		        },
		        
		        error: function(e){
		        	console.log('Error: ' + e);
		        }
		  });
	});
	
	/*	search page start*/
});

function replaceQueryParam(param, newval, searchUrl) {
    var regex = new RegExp("([?;&])" + param + "[^&;]*[;&]?");
    var query = searchUrl.replace(regex, "$1").replace(/&$/, '');
    return (query.length > 2 ? query + "&" : "?") + (newval ? param + "=" + newval : '');
}



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
