// Retrieve random inputs
var a = parseInt(input["@random"][0] * 25 + 5);
var b = parseInt(input["@random"][1] * 7 + 2);

// Retrieve HTML tag we placed in the statement
// and replace them by the generated random value
document.getElementById("ipr1").innerHTML = a;
document.getElementById("ipr2").innerHTML = b;

// Draw the triangle
var canvas = document.getElementById("canvas");
var size = 12;
var margin = 20;
canvas.width  = a*size+margin*3;
canvas.height = b*size+margin*3;

var context = canvas.getContext("2d");
context.moveTo(margin,margin);
context.lineTo(margin+a*size,margin+b*size);
context.moveTo(margin,margin);
context.lineTo(margin,margin+b*size);
context.moveTo(margin,margin+b*size);
context.lineTo(margin+a*size,margin+b*size);
context.stroke();

context.font = "30px Garamond";
context.fillText(a, margin+a*size/2 - 10, margin+b*size + 30);
context.fillText(b, 0, margin+b*size/2 + 10);
    
