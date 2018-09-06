function insertNumber(element) {
    document.inputForm.resultArea.value = document.inputForm.resultArea.value+element;
}

function equal() {
    var expression = document.inputForm.resultArea.value;
    if (expression) {
        document.inputForm.resultArea.value = eval(expression);
    }
}