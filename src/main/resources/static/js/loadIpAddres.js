// let reloadBooksButton = document.getElementById('reloadBooks');
//
// reloadBooksButton.addEventListener('click', reloadBooks)
//
// function reloadBooks() {
//
//     let booksContainer = document.getElementById('books-container');
//     booksContainer.innerHTML = ''
//
//     fetch('http://localhost:8080/api/viewIp')
//         .then(response => response.json())
//         .then(json => json.forEach(ip => {
//
//             let bookRow = document.createElement('tr')
//
//             let titleCol = document.createElement('td')
//             // let authorCol	= document.createElement('td')
//             // let isbnCol	= document.createElement('td')
//             // let actionCol= document.createElement('td')
//
//             titleCol.textContent = ip.title
//             debugger;
//
//             let deleteBookBtn = document.createElement('button')
//
//             // actionCol.appendChild(deleteBookBtn)
//
//             bookRow.appendChild(titleCol)
//             // bookRow.appendChild(authorCol)
//             // bookRow.appendChild(isbnCol)
//             // bookRow.appendChild(actionCol)
//
//             booksContainer.append(bookRow)
//         }))
//
// }
let reloadBooksButton = document.getElementById('reloadBooks');

reloadBooksButton.addEventListener('click', reloadBooks)

function reloadBooks() {

    let booksContainer = document.getElementById('books-container');
    booksContainer.innerHTML = ''

    fetch('http://localhost:8080/api/viewIp')
        .then(response => response.json())
        .then(json => json.forEach(ip => {
            console.log(ip)

            let bookRow = document.createElement('div')

            let titleCol = document.createElement('h2')
            // let authorCol	= document.createElement('td')
            // let isbnCol	= document.createElement('td')
            // let actionCol= document.createElement('td')

            titleCol.textContent = ip.title

            // actionCol.appendChild(deleteBookBtn)

            bookRow.appendChild(titleCol)
            // bookRow.appendChild(authorCol)
            // bookRow.appendChild(isbnCol)
            // bookRow.appendChild(actionCol)

            booksContainer.append(bookRow)
        }))

}

