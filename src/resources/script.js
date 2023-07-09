window.center = [13.7372621, 51.0504088];
window.gasStations = [[13.7372621, 51.0504088]];
window.zoom = 5;

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

function updateCenter() {
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

let Geographic  = new OpenLayers.Projection("EPSG:4326");
let Mercator = new OpenLayers.Projection("EPSG:900913");


function distanceBetweenPoints(latlng1, latlng2){
    var point1 = new OpenLayers.Geometry.Point(latlng1.lon, latlng1.lat).transform(Geographic, Mercator);
    var point2 = new OpenLayers.Geometry.Point(latlng2.lon, latlng2.lat).transform(Geographic, Mercator);
    return point1.distanceTo(point2);
}

function getRadius() {
    let sourceProj = map.getProjectionObject();
    let extent = map.getExtent();

    let topLeft = new OpenLayers.LonLat(extent.left, extent.top).transform(sourceProj, Geographic);
    let center = new OpenLayers.LonLat(map.center.lon, map.center.lat).transform(sourceProj, Geographic);

    return distanceBetweenPoints(center, topLeft)
}

map.events.register('moveend', map, function (e) {
    let center = [map.center.lon, map.center.lat];
    center = new OpenLayers.LonLat(center[0], center[1]).transform(map.getProjectionObject(), Geographic);
    window.center = [center.lon, center.lat]
    app.onMapCenterChange(window.center, getRadius()/1000);
})

updateCenter();