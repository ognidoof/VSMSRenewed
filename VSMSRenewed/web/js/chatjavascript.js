function submitFunction(supplierName) {
    document.getElementById("hiddenvalue").setAttribute("value", supplierName);
    document.getElementById("chat").submit();
}

function initializeChat($sender, $receiver) {
// CREATE A REFERENCE TO FIREBASE
    var messagesRef = new Firebase('https://vsms.firebaseio.com/');

// REGISTER DOM ELEMENTS
    var messageField = $('#messageInput');
    var messageList = $('#messages');

// LISTEN FOR KEYPRESS EVENT 
    messageField.keypress(function(e) {
        if (e.keyCode == 13) {
            //FIELD VALUES
            var message = messageField.val();

            //SAVE DATA TO FIREBASE AND EMPTY FIELD
            messagesRef.push({sender: $sender, receiver: $receiver, text: message});
            messageField.val('');

            //Type"Clear" to remove the message
            if (message == "clear") {
                messagesRef.remove();
            }
        }
    });

// Add a callback that is triggered for each chat message created.
    messagesRef.limitToLast(10).on('child_added', function(snapshot) {
        //GET DATA
        var data = snapshot.val();
        var sender = data.sender || "anonymous";
        var receiver = data.receiver;
        var message = data.text;
        var key = data.key;
        var ordercondition = data.ordercondition;
        var orderid = data.orderid;
        //CREATE ELEMENTS MESSAGE & SANITIZE TEXT
        //var messageElement = $("<li>");
        //messageElement.text(message).prepend(senderElement);      
        //var receiverElement = $("<i class='chat-name'></i>");

        var senderElement = $("<strong class='chat-name'></strong>");
        senderElement.text(sender);
        //ADD MESSAGE only if sender and receiver are relevant
        if (sender === $sender && receiver === $receiver || sender === $receiver && receiver === $sender) {
            messageList.append(senderElement)
            messageList.append(message)
            messageList.append("<li>")
            if (key != null) {
                messageList.append("Order key: " + key)
            }
            messageList.append("<li>")
        }

        //SCROLL TO BOTTOM OF MESSAGE LIST
        messageList[0].scrollTop = messageList[0].scrollHeight;
    });
}

