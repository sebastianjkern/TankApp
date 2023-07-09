window.center = [13.7372621, 51.0504088];
window.gasStations = [[13.7372621, 51.0504088]];
window.zoom = 5;

map = new OpenLayers.Map("mapdiv");
map.addLayer(new OpenLayers.Layer.OSM());

markers = new OpenLayers.Layer.Markers("Markers");
map.addLayer(markers);

// Set the two main Projection methods
let Geographic  = new OpenLayers.Projection("EPSG:4326");
let Mercator = new OpenLayers.Projection("EPSG:900913");

// Converting points from geo position to projected position
// only needed for markers, everything else uses special
// projection methods
function getPosition(position) {
    let lonlat = new OpenLayers.LonLat(position[0], position[1])
                    .transform(
                         Geographic,
                         map.getProjectionObject()
                    );
    return lonlat;
}

// callback function for setting the center of the map from java
function updateCenter() {
    var zoom=15;
    map.setCenter(getPosition(window.center), zoom);
}

// Callback function for updating the visible markers from java
function updateMarkers() {
    markers.clearMarkers();

    for (const element of window.gasStations) {
        let lonlat = getPosition(element);
        markers.addMarker(new OpenLayers.Marker(lonlat));
    }
}


// Calculates the distance between to points through the OpenLayers API
function distanceBetweenPoints(latlng1, latlng2){
    let point1 = new OpenLayers.Geometry.Point(latlng1.lon, latlng1.lat).transform(Geographic, Mercator);
    let point2 = new OpenLayers.Geometry.Point(latlng2.lon, latlng2.lat).transform(Geographic, Mercator);
    return point1.distanceTo(point2);
}

// Get current visible radius from the center point and extent
function getRadius() {
    let sourceProj = map.getProjectionObject();
    let extent = map.getExtent();

    let topLeft = new OpenLayers.LonLat(extent.left, extent.top).transform(sourceProj, Geographic);
    let center = new OpenLayers.LonLat(map.center.lon, map.center.lat).transform(sourceProj, Geographic);

    return distanceBetweenPoints(center, topLeft)
}

// Register callback to java for updating content if map is dragged
map.events.register('moveend', map, function (e) {
    let center = [map.center.lon, map.center.lat];
    center = new OpenLayers.LonLat(center[0], center[1]).transform(map.getProjectionObject(), Geographic);
    window.center = [center.lon, center.lat]
    app.onMapCenterChange(window.center, getRadius()/1000);
})

updateCenter();