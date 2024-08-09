$(document).ready(function(){
    initPart1();
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