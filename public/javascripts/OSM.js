var myMarker;
var map;

function initMap() {
    map = L.map('map').setView([48.86, 2.35], 5);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
}

function searchPlace() {
    if (map) map.remove();
    initMap();
    var place = document.getElementById('street').value,
        city = document.getElementById("city").value,
        postal = document.getElementById("postal").value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            document.getElementById('coords').innerHTML =
                '<input type="hidden" id="lon" name="incidents.longitude" value="' + myObj[0].lon + '">\n' +
                '<input type="hidden" id="lat" name="incidents.latitude" value="' + myObj[0].lat + '">\n' +
                '<input type="hidden" id="adr" name="incidents.adresse" value="' + myObj[0].display_name + '">';
            initMarker([myObj[0].lat, myObj[0].lon], myObj[0].display_name);
        }
    };
    xmlhttp.open("GET", 'https://nominatim.openstreetmap.org/search?format=json&street=' + place + '&city=' + city + '&postalcode=' + postal, true);
    xmlhttp.send();
}

function initMarker(coords, name) {
    map.setView(new L.LatLng(coords[0], coords[1]), 12);
    myMarker = new L.marker(coords).addTo(map)
        .bindPopup(name)
        .openPopup();
}

function initMarkerInfo(coords) {
    map.setView(new L.LatLng(coords[0], coords[1]), 12);
    myMarker = new L.marker(coords).addTo(map);
}

function showPosition(position) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            setPlace(position.coords.latitude, position.coords.longitude, myObj.display_name);
            addRadius([position.coords.latitude, position.coords.longitude], (document.getElementById('dist').value * 1000));
        }
    };
    xmlhttp.open("GET", 'https://open.mapquestapi.com/nominatim/v1/reverse.php?key=hAjT08RhLAtcTZfZhbS0h6Ejevr4q7wG&format=json&lat=' + position.coords.latitude + '&lon=' + position.coords.longitude);
    xmlhttp.send();
}

var circle;

function addRadius(coords, range) {
    //10 pour 20km 8 pour 50km 11 pour 10km 12 pour 5km
    if (range => 5000) {
        map.setView(new L.LatLng(coords[0], coords[1]), 12);
    } else if (range => 10000) {
        map.setView(new L.LatLng(coords[0], coords[1]), 11);
    } else if (range => 20000) {
        map.setView(new L.LatLng(coords[0], coords[1]), 10);
    } else {
        map.setView(new L.LatLng(coords[0], coords[1]), 8);
    }
    if (typeof (circle) === 'undefined') {
        circle = new L.circle(coords, {
            color: 'black',
            fillColor: '#000000',
            fillOpacity: 0.1,
            radius: range
        }).addTo(map);
    } else {
        circle
            .setLatLng(coords)
            .setRadius(range)
            .addTo(map);
    }
}

function setPlace(lat, lon, name) {
    document.getElementById('lat').value = lat;
    document.getElementById('lon').value = lon;
    document.getElementById('place').innerText = name;
}


function MajRadius() {
    let lat= document.getElementById("lat").value;
    let lon= document.getElementById("lon").value;
    addRadius([lat,lon], document.getElementById("rayon").value*1000);
}

function MajPlace(){
    searchPlace();
    setTimeout(MajRadius, 200);
}

initMap();