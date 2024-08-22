console.log("this is base file");


const toggleSidebar = ()  => {

     if($(".sidebar").is(":visible")){
            //true
            //band krna ha
            $(".sidebar").css("display","none");
            $(".content").css("margin-left","0%");
     }
     else
     {
        //false
        //show karna ha
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
     }
};


const search = () => {

    let query = $("#search-input").val();
    if(query=='')
    {
        $(".search-result").hide();
    }
    else
    {
        console.log(query);
        //search
        $(".search-result").show();

        //sending request to server
        let url = `http://localhost:8080/search/${query}`;
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) =>{
                //data
                let text = `<div class='list-group'>`;

                data.forEach((contact) => {
                    text+=`<a href='/user/${contact.cId}/contact' class='list-group-item list-group-item -action'> ${contact.name} </a>`
                });

                text+=`</div>`;

                $(".search-result").html(text);
                $(".search-result").show();
            })

    }

};




// first request to server to create order

const paymentStart = () => {
    console.log("Payment started...")
    let amount = $("#payment_field").val();
    if(amount=='' || amount==null)
    {
        //alert("amount is required !!");
        swal("Failed!", "amount is required !!", "error");
        return;
    }
    //code..
    //we will ajax to send request to create order -jquery
    $.ajax(
        {
            url:"/user/create_order",
            data:JSON.stringify({amount:amount,info:"order_request"}),
            contentType:'application/json',
            type:'POST',
            datatype:'json',
            success:function(response){
                 //invoke success function
                 if(response.status=="created")
                 {
                    //open payment form
                    var options = {
                         key: "YOUR_KEY_ID", // Enter the Key ID generated from the Dashboard
                         amount: "50000", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
                         currency: "INR",
                         name: "Smart Contact Manager",
                         description: "Donation",
                         image: "https://pixabay.com/vectors/search/logo/",
                         order_id: "response.id", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
                         handler: function (response){
                            console.log(response.razorpay_payment_id);
                            console.log(response.razorpay_order_id);
                            console.log(response.razorpay_signature);
                            console.log(" Payment successfull !!");

                            updatePaymentOnServer(
                                response.razorpay_payment_id,
                                response.razopay_order_id
                                ,"paid"
                            );

                            //alert("congrats !! Payment successfull !!")
                            swal("Good job!", "congrats !! Payment successfull !!", "success");
                         },
                         prefill: {
                             "name": "",
                             "email": "",
                             "contact": ""
                         },
                         notes: {
                            address: "Leran with vedant"
                         },
                         theme: {
                            color   : "#3399cc"
                         }
                    };

                    let rzp = new Razorpay(options);
                    rzp.on('payment.failed', function (response){
                             console.log(response.error.description);
                             console.log(response.error.source);
                             console.log(response.error.code);
                             console.log(response.error.step);
                             console.log(response.error.reason);
                             console.log(response.error.metadata.order_id);
                             console.log(response.error.metadata.payment_id);
                             alert("Oops payment failed !!");
                             swal("Failed !!", "Oops payment failed  !!", "error");

                    });

                    rzp.open();
                 }
            },
            error:function(error){
                //invoke when error
                console.log(error)
                alert("something went wrong !!")
            }
        }
    )

}

//
function updatePaymentOnServer(payment_id,order_id,status)
{
    $.ajax({
        url:"/user/update_order",
        data:JSON.stringify({
            payment_id:payement_id,
            order_id:order_id,
            status:status,
        }),
        contentType:"application/json",
        type:"POST",
        dataType:"json",
        success: function(response){
            swal("Good job!", "congrats !! Payment successfull !!", "success");
        },
        error:function(error){
            swal("Failed !!", "Your payment is successfull ,but we did not get on server,we will contact you as soon as possible", "error");
        }
    });
}


























