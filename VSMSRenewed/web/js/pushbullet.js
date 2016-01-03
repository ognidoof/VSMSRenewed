            alert("This is done");
            
            var PushBullet = require('pushbullet');
            var pusher = new PushBullet('jyYKkUH7J9W5JWtYv1saoy89cvSJWgIw');

            var email = 'vincentt.2013@sis.smu.edu.sg';
            var content = "New order comes in from supplier \n Beef \n Steak \n Niu rou"

            alert("New here");
            sendOrder(content, email, pusher, PushBullet);
            alert("After sendorder");
   
            function sendOrder(content, email, pusher, PushBullet) {
                alert("Inside sendorder");
                pusher.note(email, 'New Order ID # ...', content, function(error, response) {
                    console.log(error);
                });
            }

            function sendListOrder(contentList, emailList, pusher, PushBullet) {
                //for different email list, contents and new order id will be populated.
                //sendOrder(content,email,pusher,PushBullet);
            }            
