window.center = [13.7372621, 51.0504088];
window.gasStations = [[13.7372621, 51.0504088]];

map = new OpenLayers.Map("mapdiv");
map.addLayer(new OpenLayers.Layer.OSM());

markers = new OpenLayers.Layer.Markers("Markers");
map.addLayer(markers);

function getPosition(position) {
    let lonlat = new OpenLayers.LonLat(position[0], position[1])
                    .transform(
                         new OpenLayers.Projection("EPSG:4326"),
                         map.getProjectionObject()
                    );
    return lonlat;
}

function updateGasStations() {
    var zoom=15;
    map.setCenter(getPosition(window.center), zoom);
}

function updateMarkers() {
    markers.clearMarkers();

    for (const element of window.gasStations) {
        let lonlat = getPosition(element);
        markers.addMarker(new OpenLayers.Marker(lonlat));
    }
}

updateMarkers();
updateGasStations();