$(document).ready(function(){
    var Lcode = $('#preMaterL').text();
    var Mcode = $('#preMaterM').text();
    var Scode = $('#preMaterS').text();

    Lcode=(Lcode==="")?null:Lcode;
    Mcode=(Mcode==="")?null:Mcode;
    Scode=(Scode==="")?null:Scode;

    console.log(Lcode+"/"+Mcode+"/"+Scode);

    if(Lcode===null && Mcode===null && Scode===null){
        console.log("비었다")
        initMater1();
    }
    else{
        console.log("채웠다")
        searchMaterCode(Lcode, Mcode, Scode);
    }

    initMater2();

});

// 초기값1
function initMater1(){
    $.ajax({
        url:'/select/getMater',
        method:'GET',
        success:function(data){
            $('#selectMaterL').empty().append("<option value=''>전체보기</option>");
            $('#selectMaterM').empty().append('<option value="">전체보기</option>');
            $('#selectMaterS').empty().append('<option value="">전체보기</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                        .prop('selected', materL.materLcode == data.l)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                        .prop('selected', materM.materMcode == data.m)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                        .prop('selected', materS.materScode == data.s)
                );
            });

            document.getElementById('selectMaterL').addEventListener('change', function() {
                const selectedOption = this.options[this.selectedIndex];
                const selectedValue = selectedOption.textContent;
                const inputField = document.getElementById('inputMaterL');
                inputField.value = selectedValue;
            });
            document.getElementById('selectMaterM').addEventListener('change', function() {
                const selectedOption = this.options[this.selectedIndex];
                const selectedValue = selectedOption.textContent;
                const inputField = document.getElementById('inputMaterM');
                inputField.value = selectedValue;
            });
            document.getElementById('selectMaterS').addEventListener('change', function() {
                const selectedOption = this.options[this.selectedIndex];
                const selectedValue = selectedOption.textContent;
                const inputField = document.getElementById('inputMaterS');
                inputField.value = selectedValue;
            });

        }
    });

}
// 초기값2
function initMater2(){
    $.ajax({
        url:'/select/getMater',
        method:'GET',
        success:function(data){
            $('#selectMaterL2').empty().append("<option value=''>전체보기</option>");
            $('#selectMaterM2').empty().append('<option value="">전체보기</option>');
            $('#selectMaterS2').empty().append('<option value="">전체보기</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL2').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                        .prop('selected', materL.materLcode == data.l)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM2').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                        .prop('selected', materM.materMcode == data.m)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS2').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                        .prop('selected', materS.materScode == data.s)
                );
            });
        }
    });

}

// 선택1
function updateMaterCode(changedSelect){
    var Lcode=$('#selectMaterL').val();
    var Mcode=$('#selectMaterM').val();
    var Scode=$('#selectMaterS').val();

    console.log(Lcode, Mcode, Scode);

    if (changedSelect === 'L') {
        Mcode=null;
        Scode=null;
    } else if (changedSelect === 'M') {
        Scode=null;
    }

    $.ajax({
        url:'/select/mater',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectMaterL').empty().append("<option value=''>전체보기</option>");
            $('#selectMaterM').empty().append('<option value="">전체보기</option>');
            $('#selectMaterS').empty().append('<option value="">전체보기</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                        .prop('selected', materL.materLcode == data.l)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                        .prop('selected', materM.materMcode == data.m)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                        .prop('selected', materS.materScode == data.s)
                );
            });

        }
    });

}
// 선택2
function updateMaterCode2(changedSelect){
    var Lcode=$('#selectMaterL2').val();
    var Mcode=$('#selectMaterM2').val();
    var Scode=$('#selectMaterS2').val();

    console.log(Lcode, Mcode, Scode);

    if (changedSelect === 'L') {
        Mcode=null;
        Scode=null;
    } else if (changedSelect === 'M') {
        Scode=null;
    }

    $.ajax({
        url:'/select/mater',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectMaterL2').empty().append("<option value=''>전체보기</option>");
            $('#selectMaterM2').empty().append('<option value="">전체보기</option>');
            $('#selectMaterS2').empty().append('<option value="">전체보기</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL2').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                        .prop('selected', materL.materLcode == data.l)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM2').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                        .prop('selected', materM.materMcode == data.m)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS2').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                        .prop('selected', materS.materScode == data.s)
                );
            });

        }
    });

}
// 초기화면1(검색)
function searchMaterCode(materL1, materM1, materS1){
    var Lcode=materL1;
    var Mcode=materM1;
    var Scode=materS1;

    console.log(Lcode, Mcode, Scode);

    $.ajax({
        url:'/select/mater',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectMaterL').empty().append("<option value=''>전체보기</option>");
            $('#selectMaterM').empty().append('<option value="">전체보기</option>');
            $('#selectMaterS').empty().append('<option value="">전체보기</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                        .prop('selected', materL.materLcode == data.l)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                        .prop('selected', materM.materMcode == data.m)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                        .prop('selected', materS.materScode == data.s)
                );
            });

        }
    });

}