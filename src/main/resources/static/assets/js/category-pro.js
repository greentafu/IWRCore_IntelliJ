$(document).ready(function(){
    initPro1();
    initPro2();
});

// 초기값1
function initPro1(){
    $.ajax({
        url:'/select/getPro',
        method:'GET',
        success:function(data){
            $('#selectProL').empty().append("<option value=''>전체보기</option>");
            $('#selectProM').empty().append('<option value="">전체보기</option>');
            $('#selectProS').empty().append('<option value="">전체보기</option>');

            data.proLDTOs.forEach(function(proL) {
                $('#selectProL').append(
                    $('<option></option>')
                        .attr('value', proL.proLcode)
                        .text(proL.lname)
                        .prop('selected', proL.proLcode == data.l)
                );
            });
            data.proMDTOs.forEach(function(proM) {
                $('#selectProM').append(
                    $('<option></option>')
                        .attr('value', proM.proMcode)
                        .text(proM.mname)
                        .prop('selected', proM.proMcode == data.m)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#selectProS').append(
                    $('<option></option>')
                        .attr('value', proS.proScode)
                        .text(proS.sname)
                        .prop('selected', proS.proScode == data.s)
                );
            });
        }
    });

}
// 초기값2
function initPro2(){
    $.ajax({
        url:'/select/getPro',
        method:'GET',
        success:function(data){
            $('#selectProL2').empty().append("<option value=''>전체보기</option>");
            $('#selectProM2').empty().append('<option value="">전체보기</option>');
            $('#selectProS2').empty().append('<option value="">전체보기</option>');

            data.proLDTOs.forEach(function(proL) {
                $('#selectProL2').append(
                    $('<option></option>')
                        .attr('value', proL.proLcode)
                        .text(proL.lname)
                        .prop('selected', proL.proLcode == data.l)
                );
            });
            data.proMDTOs.forEach(function(proM) {
                $('#selectProM2').append(
                    $('<option></option>')
                        .attr('value', proM.proMcode)
                        .text(proM.mname)
                        .prop('selected', proM.proMcode == data.m)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#selectProS2').append(
                    $('<option></option>')
                        .attr('value', proS.proScode)
                        .text(proS.sname)
                        .prop('selected', proS.proScode == data.s)
                );
            });
        }
    });

}

// 선택1
function updateProCode(changedSelect){
    var Lcode=$('#selectProL').val();
    var Mcode=$('#selectProM').val();
    var Scode=$('#selectProS').val();

    console.log(Lcode, Mcode, Scode);

        if (changedSelect === 'L') {
            Mcode=null;
            Scode=null;
        } else if (changedSelect === 'M') {
            Scode=null;
        }

    $.ajax({
        url:'/select/pro',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectProL').empty().append("<option value=''>전체보기</option>");
            $('#selectProM').empty().append('<option value="">전체보기</option>');
            $('#selectProS').empty().append('<option value="">전체보기</option>');

            data.proLDTOs.forEach(function(proL) {
                $('#selectProL').append(
                    $('<option></option>')
                        .attr('value', proL.proLcode)
                        .text(proL.lname)
                        .prop('selected', proL.proLcode == data.l)
                );
            });
            data.proMDTOs.forEach(function(proM) {
                $('#selectProM').append(
                    $('<option></option>')
                        .attr('value', proM.proMcode)
                        .text(proM.mname)
                        .prop('selected', proM.proMcode == data.m)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#selectProS').append(
                    $('<option></option>')
                        .attr('value', proS.proScode)
                        .text(proS.sname)
                        .prop('selected', proS.proScode == data.s)
                );
            });

        }
    });

}
// 선택2
function updateProCode2(changedSelect){
    var Lcode=$('#selectProL2').val();
    var Mcode=$('#selectProM2').val();
    var Scode=$('#selectProS2').val();

    console.log(Lcode, Mcode, Scode);

        if (changedSelect === 'L') {
            Mcode=null;
            Scode=null;
        } else if (changedSelect === 'M') {
            Scode=null;
        }

    $.ajax({
        url:'/select/pro',
        method:'GET',
        data:{lcode:Lcode, mcode:Mcode, scode:Scode},
        success:function(data){

            console.log(data);

            $('#selectProL2').empty().append("<option value=''>전체보기</option>");
            $('#selectProM2').empty().append('<option value="">전체보기</option>');
            $('#selectProS2').empty().append('<option value="">전체보기</option>');

            data.proLDTOs.forEach(function(proL) {
                $('#selectProL2').append(
                    $('<option></option>')
                        .attr('value', proL.proLcode)
                        .text(proL.lname)
                        .prop('selected', proL.proLcode == data.l)
                );
            });
            data.proMDTOs.forEach(function(proM) {
                $('#selectProM2').append(
                    $('<option></option>')
                        .attr('value', proM.proMcode)
                        .text(proM.mname)
                        .prop('selected', proM.proMcode == data.m)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#selectProS2').append(
                    $('<option></option>')
                        .attr('value', proS.proScode)
                        .text(proS.sname)
                        .prop('selected', proS.proScode == data.s)
                );
            });

        }
    });

}