function sendMessage() {
    const fullName = document.getElementById('fullName').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const message = document.getElementById('message').value;
    debugger;

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
            window.location.href = 'http://localhost:8080/product/all'
        })
        .catch(error => console.log('error', error))
}
