function bindAutocomplete() {

  var acService = new google.maps.places.AutocompleteService(),
    placesService = new google.maps.places.PlacesService(document.createElement('div'));

  $("input#location").autocomplete({
    source: function(req, resp) {
    	alert("inside auto complete");
      acService.getPlacePredictions({
        input: req.term,
        types:['(regions)']
      }, function(places, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          var _places = [];
          for (var i = 0; i < places.length; ++i) {
            _places.push({
              id: places[i].place_id,
              value: places[i].description,
              label: places[i].description
            });
          }
          resp(_places);
        }
      });
    },
    select: function(e, o) {
      placesService.getDetails({
        placeId: o.item.id
      }, function(place, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          alert(o.item.value +
            '\n is located at \n ' +
            place.geometry.location.toUrlValue());
        }
      });


    }
  });
}
$(window).load(bindAutocomplete);