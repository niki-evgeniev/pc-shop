function sendMessage() {
    const fullName = document.getElementById('fullName').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const message = document.getElementById('message').value;
    // const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    // const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    const data = {
        fullName: fullName,
        email: email,
        phoneNumber: phoneNumber,
        message: message
    };

    fetch('http://localhost:8080/api/send', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch(error => console.log('error', error))
}

