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