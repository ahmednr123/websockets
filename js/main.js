const client = new WebSocketClient('ws', 'localhost', 8080, '/Socket/endpoint');

client.onmessage(function (event) {
    writeToBoard(event.data)
})

client.connect();

$("#msg").addEventListener("keyup", (ev) => {
    if (ev.keyCode == 13) { // 13 - ENTER
        client.send($("#msg").value)
        $("#msg").value = ""
    }
})

function writeToBoard (msg) {
    $("#response").innerHTML += msg + "<br>"
}