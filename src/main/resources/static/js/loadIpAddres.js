let reloadIpButton = document.getElementById('loadIpAddress');
reloadIpButton.addEventListener('click', loadIp)

function loadIp() {

    fetch('http://localhost:8080/api/viewIp')
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error fetching IP addresses');
            }
        })
        .then(json => {
            let ipContainer = document.getElementById('ip-container');
            ipContainer.innerHTML = '';
            json.forEach(ip => {
                console.log(ip);
                let ipRow = document.createElement('tr');
                let titleCol = document.createElement('td');
                titleCol.textContent = ip.ip;
                ipRow.appendChild(titleCol);
                ipContainer.append(ipRow);
            });
        })
        .catch(error => console.error('Error:', error));

}



