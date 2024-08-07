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

            $('#selectMaterL').empty().append("<option value=''>선택</option>");
            $('#selectMaterM').empty().append('<option value="">선택</option>');
            $('#selectMaterS').empty().append('<option value="">선택</option>');

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