function addToLowerTable(){
    const materialRows=document.querySelectorAll('#materialList tbody tr');
    const materialSelTable=document.querySelector('#materialSel');

    materialRows.forEach(x=>{
        const checkbox=x.querySelector('input[type="checkbox"]');
        if(checkbox&&checkbox.checked){
            const cells=x.querySelectorAll('td');

            const code=cells[1].innerText;
            const name=cells[2].innerText;
            const matL=cells[3].innerText;
            const matM=cells[4].innerText;
            const matS=cells[5].innerText;
            const standard=cells[6].innerText;
            const unit=cells[7].innerText;
            const color=cells[8].innerText;
            const box=cells[9].innerText;
            const file=cells[10].innerText;

            const newRow = document.createElement('tr');

            [code, name].forEach(text => {
                const td = document.createElement('td');
                td.innerText = text;
                newRow.appendChild(td);
            });

            const inputTd = document.createElement('td');
            const input = document.createElement('input');
            input.type = 'number';
            input.min='0';
            input.style.border = '0px';
            input.style.width = '80px';
            input.id = `quantity${code}`;
            inputTd.appendChild(input);
            newRow.appendChild(inputTd);

            [matL, matM, matS, standard, unit, color, box, file].forEach(text => {
                const td = document.createElement('td');
                td.innerText = text;
                newRow.appendChild(td);
            });

            const deleteTd = document.createElement('td');
            const button = document.createElement('button');
            button.type = 'button';
            button.className = 'btn btn-sm btn-secondary';
            button.innerText = '삭제';
            button.onclick = function() {
                newRow.remove(); // Remove the row when the button is clicked
            };
            deleteTd.appendChild(button);
            newRow.appendChild(deleteTd);

            const tdEmpty = document.createElement('td');
            tdEmpty.innerText = '';
            tdEmpty.style.display = 'none';
            newRow.appendChild(tdEmpty);

            const tdEmpty1 = document.createElement('td');
            tdEmpty1.innerText = '';
            tdEmpty1.style.display = 'none';
            newRow.appendChild(tdEmpty1);

            materialSelTable.appendChild(newRow);
        }
    });
}