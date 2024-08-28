if(document.getElementById('selColor')){
    document.getElementById('selColor').addEventListener('change', function() {
        const selectedValue = this.value;
        const inputField = document.getElementById('proColor');
        inputField.value = selectedValue;
    });
}
if(document.getElementById('selColor2')){
    document.getElementById('selColor2').addEventListener('change', function() {
        const selectedValue = this.value;
        console.log("색색색", selectedValue);
        const inputField = document.getElementById('proColor2');
        inputField.value = selectedValue;
    });
}
if(document.getElementById('selUnit')){
    document.getElementById('selUnit').addEventListener('change', function() {
        const selectedValue = this.value;
        const inputField = document.getElementById('proUnit');
        inputField.value = selectedValue;
    });
}