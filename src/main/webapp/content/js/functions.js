function insert(element) {
    document.inputForm.result.value = document.inputForm.result.value + element;
}

function clean() {
    document.inputForm.result.value = "";
}

function backSpace() {
    var expression = document.inputForm.result.value;
    document.inputForm.result.value = expression.substring(0, expression.length - 1);
}

function submit() {
    document.inputForm.submit();
}