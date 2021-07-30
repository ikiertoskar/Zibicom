function validate() {
    var pieces = document.getElementById("pieces-input");
    var price = document.getElementById("price-input");
    var code = document.getElementById("code-input");

    var flag = true;
    if(code.value == "") {
            code.style.background = "#ebb5b5";
            flag = false;
        }

    if(pieces.value == "") {
        pieces.style.background = "#ebb5b5";
        flag = false;
        }

    if(isNaN(pieces.value)){
            pieces.style.background = "#ebb5b5";
            flag = false;
        }

    if(price.value != "") {
        if(isNaN(price))
        {
            price.style.background = "#ebb5b5";
            flag = false;
        }
    }

    flag true;
}