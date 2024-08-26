$(document).ready(function(){
    var Lcode = $('#prePartL').text();
    var Mcode = $('#prePartM').text();
    var Scode = $('#prePartS').text();

    Lcode=(Lcode==="")?null:Lcode;
    Mcode=(Mcode==="")?null:Mcode;
    Scode=(Scode==="")?null:Scode;

    console.log(Lcode+"/"+Mcode+"/"+Scode);

    if(Lcode===null && Mcode===null && Scode===null){
        console.log("비었다")
        initPart1();
    }
    else{
        console.log("채웠다")
        searchPartCode(Lcode, Mcode, Scode);
    }
    initPart2();
});


// 초기값1
function initPart1(){
    $.ajax({
        url:'/select/getPart',
        method:'GET',
        success:function(data){
            $('#selectPartL').empty().append("<option value=''>전체보기</option>");
            $('#selectPartM').empty().append('<option value="">전체보기</option>');
            $('#selectPartS').empty().append('<option value="">전체보기</option>');

            data.partLDTOs.forEach(function(partL) {
                $('#selectPartL').append(
                    $('<option></option>')
                        .attr('value', partL.partLcode)
                        .text(partL.lname)
                );
            });
            data.partMDTOs.forEach(function(partM) {
                $('#selectPartM').append(
                    $('<option></option>')
                        .attr('value', partM.partMcode)
                        .text(partM.mname)
                );
            });
            data.partSDTOs.forEach(function(partS) {
                $('#selectPartS').append(
                    $('<option></option>')
                        .attr('value', partS.partScode)
                        .text(partS.sname)
                );
            });

            const tf=document.getElementById('inputPartL');
            if(tf) addSelectChangeListeners();

        }
    });

}
// 초기값2
function initPart2(){
    $.ajax({
        url:'/select/getPart',
        method:'GET',
        success:function(data){
            console.log("정보가져옴");
            $('#selectPartL2').empty().append("<option value=''>전체보기</option>");
            $('#selectPartM2').empty().append('<option value="">전체보기</option>');
            $('#selectPartS2').empty().append('<option value="">전체보기</option>');

            data.partLDTOs.forEach(function(partL) {
                $('#selectPartL2').append(
                    $('<option></option>')
                        .attr('value', partL.partLcode)
                        .text(partL.lname)
                );
            });
            data.partMDTOs.forEach(function(partM) {
                $('#selectPartM2').append(
                    $('<option></option>')
                        .attr('value', partM.partMcode)
                        .text(partM.mname)
                );
            });
            data.partSDTOs.forEach(function(partS) {
                $('#selectPartS2').append(
                    $('<option></option>')
                        .attr('value', partS.partScode)
                        .text(partS.sname)
                );
            });
        }
    });

}

// 선택1
function updatePartCode(changedSelect){
    var Lcode=$('#selectPartL').val();
    var Mcode=$('#selectPartM').val();
    var Scode=$('#selectPartS').val();

    console.log(Lcode, Mcode, Scode);

    if (changedSelect === 'L') {
        Mcode=null;
        Scode=null;
    } else if (changedSelect === 'M') {
        Scode=null;
    }

    $.ajax({
        url:'/select/part',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectPartL').empty().append("<option value=''>전체보기</option>");
            $('#selectPartM').empty().append('<option value="">전체보기</option>');
            $('#selectPartS').empty().append('<option value="">전체보기</option>');

            data.partLDTOs.forEach(function(partL) {
                $('#selectPartL').append(
                    $('<option></option>')
                        .attr('value', partL.partLcode)
                        .text(partL.lname)
                        .prop('selected', partL.partLcode == data.l)
                );
            });
            data.partMDTOs.forEach(function(partM) {
                $('#selectPartM').append(
                    $('<option></option>')
                        .attr('value', partM.partMcode)
                        .text(partM.mname)
                        .prop('selected', partM.partMcode == data.m)
                );
            });
            data.partSDTOs.forEach(function(partS) {
                $('#selectPartS').append(
                    $('<option></option>')
                        .attr('value', partS.partScode)
                        .text(partS.sname)
                        .prop('selected', partS.partScode == data.s)
                );
            });

            const tf=document.getElementById('inputPartL');
            if(tf) addSelectChangeListeners();

        }
    });

}
// 선택2
function updatePartCode2(changedSelect){
    var Lcode=$('#selectPartL2').val();
    var Mcode=$('#selectPartM2').val();
    var Scode=$('#selectPartS2').val();

    console.log(Lcode, Mcode, Scode);

    if (changedSelect === 'L') {
        Mcode=null;
        Scode=null;
    } else if (changedSelect === 'M') {
        Scode=null;
    }

    $.ajax({
        url:'/select/part',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectPartL2').empty().append("<option value=''>전체보기</option>");
            $('#selectPartM2').empty().append('<option value="">전체보기</option>');
            $('#selectPartS2').empty().append('<option value="">전체보기</option>');

            data.partLDTOs.forEach(function(partL) {
                $('#selectPartL2').append(
                    $('<option></option>')
                        .attr('value', partL.partLcode)
                        .text(partL.lname)
                        .prop('selected', partL.partLcode == data.l)
                );
            });
            data.partMDTOs.forEach(function(partM) {
                $('#selectPartM2').append(
                    $('<option></option>')
                        .attr('value', partM.partMcode)
                        .text(partM.mname)
                        .prop('selected', partM.partMcode == data.m)
                );
            });
            data.partSDTOs.forEach(function(partS) {
                $('#selectPartS2').append(
                    $('<option></option>')
                        .attr('value', partS.partScode)
                        .text(partS.sname)
                        .prop('selected', partS.partScode == data.s)
                );
            });

        }
    });

}

// input
function addSelectChangeListeners() {
    $('#selectPartL').off('change').on('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        const selectedValue = selectedOption.textContent;
        $('#inputPartL').val(selectedValue);

        const selectMValue = '';
        $('#inputPartM').val(selectMValue);
        const selectSValue = '';
        $('#inputPartS').val(selectSValue);
    });

    $('#selectPartM').off('change').on('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        const selectedValue = selectedOption.textContent;
        $('#inputPartM').val(selectedValue);

        const selectedL=document.getElementById('selectPartL');
        const selectedOptionL = selectedL.options[selectedL.selectedIndex];
        const selectLText = selectedOptionL.textContent;
        console.log("selectL:", selectLText);
        $('#inputPartL').val(selectLText);

        const selectSValue = '';
        $('#inputPartS').val(selectSValue);
    });

    $('#selectPartS').off('change').on('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        const selectedValue = selectedOption.textContent;
        $('#inputPartS').val(selectedValue);

        const selectedL=document.getElementById('selectPartL');
        const selectedOptionL = selectedL.options[selectedL.selectedIndex];
        const selectLText = selectedOptionL.textContent;
        console.log("selectL:", selectLText);
        $('#inputPartL').val(selectLText);

        const selectedM=document.getElementById('selectPartM');
        const selectedOptionM = selectedM.options[selectedM.selectedIndex];
        const selectMText = selectedOptionM.textContent;
        console.log("selectM:", selectMText);
        $('#inputPartM').val(selectMText);
    });
}

// 초기화면1(검색)
function searchPartCode(partL1, partM1, partS1){
    var Lcode=partL1;
    var Mcode=partM1;
    var Scode=partS1;

    console.log(Lcode, Mcode, Scode);

    $.ajax({
        url:'/select/part',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectPartL').empty().append("<option value=''>전체보기</option>");
            $('#selectPartM').empty().append('<option value="">전체보기</option>');
            $('#selectPartS').empty().append('<option value="">전체보기</option>');

            data.partLDTOs.forEach(function(partL) {
                $('#selectPartL').append(
                    $('<option></option>')
                        .attr('value', partL.partLcode)
                        .text(partL.lname)
                        .prop('selected', partL.partLcode == data.l)
                );
            });
            data.partMDTOs.forEach(function(partM) {
                $('#selectPartM').append(
                    $('<option></option>')
                        .attr('value', partM.partMcode)
                        .text(partM.mname)
                        .prop('selected', partM.partMcode == data.m)
                );
            });
            data.partSDTOs.forEach(function(partS) {
                $('#selectPartS').append(
                    $('<option></option>')
                        .attr('value', partS.partScode)
                        .text(partS.sname)
                        .prop('selected', partS.partScode == data.s)
                );
            });

        }
    });

}