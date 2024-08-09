function setDeleteMember(button){
    const m_dep=button.getAttribute('m_dep');
    const m_name=button.getAttribute('m_name');
    const m_mno=button.getAttribute('m_mno');
    document.getElementById('modalDeleteText').textContent=m_dep+" "+m_name+"("+m_mno+")를 삭제합니다\n삭제시 복구가 불가능합니다";
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