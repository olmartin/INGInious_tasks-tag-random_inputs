$.getJSON("m03Q5/data.json", function(data) {

//data is the JSON string
var i = parseInt(input["@random"][0] * data.shapes.length);    
document.getElementById("method_name").innerHTML = data.shapes[i].name;
document.getElementById("shape_name").innerHTML = data.shapes[i].name;
document.getElementById("parameters").innerHTML = data.shapes[i].parameters;



});
    


