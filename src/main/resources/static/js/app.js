var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function(greeting) {
            console.log("Playe_obj", JSON.parse(greeting.body))
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({ 'firstName': $("#name").val() }));
}

function showGreeting(playersData) {

    $("#userinfo").empty();
    $.each(playersData, function(index, item) {
        // Create a new row
        var row = $("<tr>");

        // Append cells with data
        row.append($("<td class='p-3'>").text(item.id));
        row.append($("<td class='p-3'>").text(item.name));
        row.append($("<td class='p-3'>").text(item.points));

        // Append the row to the table body
        $("#userinfo").append(row);
    });
}

$(function() {
    $("form").on('submit', function(e) {
        e.preventDefault();
    });
    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { sendName(); });
});