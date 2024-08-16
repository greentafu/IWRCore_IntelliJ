document.getElementById('selColor').addEventListener('change', function() {
    const selectedValue = this.value;
    const inputField = document.getElementById('proColor');
    inputField.value = selectedValue;
});