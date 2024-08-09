function setDeleteMember(button){
    const m_dep=button.getAttribute('m_dep');
    const m_name=button.getAttribute('m_name');
    const m_mno=button.getAttribute('m_mno');
    document.getElementById('modalDeleteText').innerHTML=m_dep+" "+m_name+"("+m_mno+")를 삭제합니다<br/>삭제시 복구가 불가능합니다";
    document.getElementById('modalDeleteBtn').setAttribute('m_mno', m_mno);
}
function deleteMember(button){
    const m_mno=button.getAttribute('m_mno');
    $.ajax({
        url:'/manage/delete_member',
        method:'POST',
        data:{mno:m_mno},
        success:function(){
            alert("삭제완료");
        window.location.reload()
        }
    });
}

function setDeletePartner(button){
    const p_reg=button.getAttribute('p_reg');
    const p_name=button.getAttribute('p_name');
    const p_pno=button.getAttribute('p_pno');
    console.log(p_reg+p_name+p_pno);
    document.getElementById('modalDeleteText').innerHTML=p_reg+" "+p_name+"("+p_pno+")를 삭제합니다<br/>삭제시 복구가 불가능합니다";
    document.getElementById('modalDeleteBtn').setAttribute('p_pno', p_pno);
}
function deletePartner(button){
    const p_pno=button.getAttribute('p_pno');
    $.ajax({
        url:'/manage/delete_partner',
        method:'POST',
        data:{pno:p_pno},
        success:function(){
            alert("삭제완료");
        window.location.reload()
        }
    });
}