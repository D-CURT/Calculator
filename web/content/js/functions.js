function insertNumber(element) {
    document.inputForm.resultArea.value = document.inputForm.resultArea.value + element;
}

function equal() {
    var expression = document.inputForm.resultArea.value;
    if (expression) {
        document.inputForm.resultArea.value = eval(expression);
    }
}

function clean() {
    document.inputForm.resultArea.value = "";
}

function backSpace() {
    var expression = document.inputForm.resultArea.value;
    document.inputForm.resultArea.value = expression.substring(0, expression.length - 1);
}