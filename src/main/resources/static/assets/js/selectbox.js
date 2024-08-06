// 카테고리1 초기에 보이기
document.addEventListener("DOMContentLoaded", function(){
    const selectC=document.getElementById("selectCategory1");
    const temp=document.getElementById('writeCategory1');
    const selected=selectC.value;
    if(selected=='direct'){
        temp.hidden=false;
    }
});
// 카테고리1 선택
function directCategory1(category){
    const temp=document.getElementById('writeCategory1');
    if(category=='direct'){
        temp.hidden=false;
    }else{
        temp.hidden=true;
    }
}
// 카테고리2 초기에 보이기
document.addEventListener("DOMContentLoaded", function(){
    const selectC=document.getElementById("selectCategory2");
    const temp=document.getElementById('writeCategory2');
    const selected=selectC.value;
    if(selected=='direct'){
        temp.hidden=false;
    }
});
// 카테고리2 선택
function directCategory2(category){
    const temp=document.getElementById('writeCategory2');
    if(category=='direct'){
        temp.hidden=false;
    }else{
        temp.hidden=true;
    }
}
// 카테고리3 초기에 보이기
document.addEventListener("DOMContentLoaded", function(){
    const selectC=document.getElementById("selectCategory3");
    const temp=document.getElementById('writeCategory3');
    const selected=selectC.value;
    if(selected=='direct'){
        temp.hidden=false;
    }
});
// 카테고리3 선택
function directCategory3(category){
    const temp=document.getElementById('writeCategory3');
    if(category=='direct'){
        temp.hidden=false;
    }else{
        temp.hidden=true;
    }
}














// 단위 초기에 보이기
document.addEventListener("DOMContentLoaded", function(){
    const selectM=document.getElementById("selectMeasure");
    const temp=document.getElementById('writeMeasure');
    const selected=selectM.value;
    if(selected=='direct'){
        temp.hidden=false;
    }
});
// 단위 선택
function directMeasure(measure){
    const temp=document.getElementById('writeMeasure');
    if(measure=='direct'){
        temp.hidden=false;
    }else{
        temp.hidden=true;
    }
}

// 색상 초기에 보이기
document.addEventListener("DOMContentLoaded", function(){
    const selectC=document.getElementById("selectColor");
    const temp=document.getElementById('writeColor');
    const selected=selectC.value;
    if(selected=='direct'){
        temp.hidden=false;
    }
});
// 색상 선택
function directColor(color){
    const temp=document.getElementById('writeColor');
    if(color=='direct'){
        temp.hidden=false;
    }else{
        temp.hidden=true;
    }
}