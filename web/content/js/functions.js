function insertNumber(element) {
    document.inputForm.result.value = document.inputForm.result.value + element;
}

function equal() {
    var expression = document.inputForm.result.value;
    if (expression) {
        document.inputForm.result.value = eval(expression);
    }
}

function clean() {
    document.inputForm.result.value = "";
}

function backSpace() {
    var expression = document.inputForm.result.value;
    document.inputForm.result.value = expression.substring(0, expression.length - 1);
}