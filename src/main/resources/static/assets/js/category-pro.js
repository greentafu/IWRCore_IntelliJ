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

            $('#selectProL').empty().append("<option value=''>선택</option>");
            $('#selectProM').empty().append('<option value="">선택</option>');
            $('#selectProS').empty().append('<option value="">선택</option>');

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