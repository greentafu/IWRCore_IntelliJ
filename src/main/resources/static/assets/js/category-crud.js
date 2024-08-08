function setDeleteCategory(button){
    const Lname=button.getAttribute('data-Lname');
    const Mname=button.getAttribute('data-Mname');
    const Sname=button.getAttribute('data-Sname');
    const Scode=button.getAttribute('data-Scode');
    const type=button.getAttribute('data-btn');
    document.getElementById('modalDeleteText').textContent="["+Lname+"] - ["+Mname+"] - ["+Sname+"]를 삭제하시겠습니까?";
    document.getElementById('modalDeleteBtn').setAttribute('data-Scode', Scode)
    document.getElementById('modalDeleteBtn').setAttribute('data-btn', type);
}

function deleteCategory(button){
    const Scode=button.getAttribute('data-Scode');
    const type=button.getAttribute('data-btn');
    console.log("scode:"+Scode+", type:"+type);

    $.ajax({
        url:'/deleteCategory',
        method:'GET',
        data:{scode:Scode, type:type},
        success:function(){
            alert('삭제 성공');
            if(type==='Part'){
                refreshPart();
            }else if(type==='Mater'){
                refreshMater();
            }else if(type==='Pro'){
                refreshPro();
            }
        },
        error:function(){
            alert('삭제 실패\n참조하는 항목이 있는지 확인하세요');
        }
    });
}

function refreshPart(){
    $.ajax({
        url:'/refreshPart',
        method:'GET',
        success:function(data){
            $('#tablePart').empty();
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
            data.partSDTOs.forEach(function(partS) {
                $('#tablePart').append(
                    $('<tr></tr>')
                        .append($('<td></td>').text(partS.partMDTO.partLDTO.lname))
                        .append($('<td></td>').text(partS.partMDTO.mname))
                        .append($('<td></td>').text(partS.sname))
                        .append(
                            $('<td></td>')
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalModifyCompany')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-scode', partS.partScode)
                                        .text('수정')
                                )
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalDelete')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-Lname', partS.partMDTO.partLDTO.lname)
                                        .attr('data-Mname', partS.partMDTO.mname)
                                        .attr('data-Sname', partS.sname)
                                        .attr('data-Scode', partS.partScode)
                                        .attr('data-btn', 'Part')
                                        .text('삭제')
                                        .attr('onclick', 'setDeleteCategory(this)')
                                )

                        )
                );
            });
        }
    });
}

function refreshMater(){
    $.ajax({
        url:'/refreshMater',
        method:'GET',
        success:function(data){
            $('#tableMater').empty();
            $('#selectMaterL').empty().append("<option value=''>선택</option>");
            $('#selectMaterM').empty().append('<option value="">선택</option>');
            $('#selectMaterS').empty().append('<option value="">선택</option>');

            data.materLDTOs.forEach(function(materL) {
                $('#selectMaterL').append(
                    $('<option></option>')
                        .attr('value', materL.materLcode)
                        .text(materL.lname)
                );
            });
            data.materMDTOs.forEach(function(materM) {
                $('#selectMaterM').append(
                    $('<option></option>')
                        .attr('value', materM.materMcode)
                        .text(materM.mname)
                );
            });
            data.materSDTOs.forEach(function(materS) {
                $('#selectMaterS').append(
                    $('<option></option>')
                        .attr('value', materS.materScode)
                        .text(materS.sname)
                );
            });

            data.materSDTOs.forEach(function(materS) {
                $('#tableMater').append(
                    $('<tr></tr>')
                        .append($('<td></td>').text(materS.materMDTO.materLDTO.lname))
                        .append($('<td></td>').text(materS.materMDTO.mname))
                        .append($('<td></td>').text(materS.sname))
                        .append(
                            $('<td></td>')
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalModifyCompany')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-scode', materS.materScode)
                                        .text('수정')
                                )
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalDelete')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-Lname', materS.materMDTO.materLDTO.lname)
                                        .attr('data-Mname', materS.materMDTO.mname)
                                        .attr('data-Sname', materS.sname)
                                        .attr('data-Scode', materS.materScode)
                                        .attr('data-btn', 'Mater')
                                        .text('삭제')
                                        .attr('onclick', 'setDeleteCategory(this)')
                                )

                        )
                );
            });

        }
    });
}

function refreshPro(){
    $.ajax({
        url:'/refreshPro',
        method:'GET',
        success:function(data){
            $('#tablePro').empty();
            $('#selectProL').empty().append("<option value=''>선택</option>");
            $('#selectProM').empty().append('<option value="">선택</option>');
            $('#selectProS').empty().append('<option value="">선택</option>');

            data.proLDTOs.forEach(function(proL) {
                $('#selectProL').append(
                    $('<option></option>')
                        .attr('value', proL.proLcode)
                        .text(proL.lname)
                );
            });
            data.proMDTOs.forEach(function(proM) {
                $('#selectProM').append(
                    $('<option></option>')
                        .attr('value', proM.proMcode)
                        .text(proM.mname)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#selectProS').append(
                    $('<option></option>')
                        .attr('value', proS.proScode)
                        .text(proS.sname)
                );
            });
            data.proSDTOs.forEach(function(proS) {
                $('#tablePro').append(
                    $('<tr></tr>')
                        .append($('<td></td>').text(proS.proMDTO.proLDTO.lname))
                        .append($('<td></td>').text(proS.proMDTO.mname))
                        .append($('<td></td>').text(proS.sname))
                        .append(
                            $('<td></td>')
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalModifyCompany')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-scode', proS.proScode)
                                        .text('수정')
                                )
                                .append(
                                    $('<button></button>')
                                        .addClass('btn btn-outline-primary btn-sm')
                                        .attr('data-bs-target', '#modalDelete')
                                        .attr('data-bs-toggle', 'modal')
                                        .attr('data-Lname', proS.proMDTO.proLDTO.lname)
                                        .attr('data-Mname', proS.proMDTO.mname)
                                        .attr('data-Sname', proS.sname)
                                        .attr('data-Scode', proS.proScode)
                                        .attr('data-btn', 'Pro')
                                        .text('삭제')
                                        .attr('onclick', 'setDeleteCategory(this)')
                                )

                        )
                );
            });

        }
    });
}