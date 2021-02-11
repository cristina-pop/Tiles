function calculateCost(costjson) {

    var returnedCost = document.getElementById("serverResponse");
    // var cost = costjson
    returnedCost.innerHTML = "Total tiling cost of your room is " + (costjson) + " EUR";
}

function loadCost() {
    var lengthInput = document.getElementById("length").value;
    var widthInput = document.getElementById("width").value;
    var modelInput = document.getElementById("model").value;
    $.ajax({
        url: 'tiles?length='+lengthInput+'&width='+ widthInput +'&model='+ modelInput
    }).done(function (response) {
        calculateCost(response.costjson);
    });
}
