document.getElementById('selColor').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('proColor');
    inputField.value = selectedValue;
});
document.getElementById('selUnit').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('proUnit');
    inputField.value = selectedValue;
});

document.getElementById('selectPartL').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('inputPartL');
    inputField.value = selectedValue;
});
document.getElementById('selectPartM').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('inputPartM');
    inputField.value = selectedValue;
});
document.getElementById('selectPartS').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('inputPartS');
    inputField.value = selectedValue;
});