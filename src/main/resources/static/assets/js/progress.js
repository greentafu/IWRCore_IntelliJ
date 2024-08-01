// document.addEventListener("DOMContentLoaded", function(){
//     const progressContainers = document.querySelectorAll(".progress");
//     const count = 3;
//     const widthPercentage = 100 / count;

//     progressContainers.forEach(function(container) {
//         const progressBar = container.querySelector(".progress-bar");

//         container.style.width = widthPercentage + '%';
        
//         progressBar.setAttribute('aria-valuenow', widthPercentage);
//     });
// });

document.addEventListener("DOMContentLoaded", function(){
    const progressRows = document.querySelectorAll(".row");
    
    progressRows.forEach(function(row){
        const progressContainers=row.querySelectorAll(".progress");
        
        progressContainers.forEach(function(container){
            const progressBar = container.querySelector(".progress-bar");
            const countElement= container.querySelector(".count");
            
            if(countElement){
                // const widthPercentage=100/count;

                // container.style.width = widthPercentage + '%';
                
                // progressBar.setAttribute('aria-valuenow', widthPercentage);



                const count = parseInt(countElement.textContent); // .count 요소의 텍스트를 정수로 변환하여 count 변수에 할당
                const widthPercentage = 100 / count;

                container.style.width = widthPercentage + '%';
                progressBar.setAttribute('aria-valuenow', widthPercentage.toFixed(2));

            }
            
        });

    });
});

  