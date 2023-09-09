function findPaymentWay() {
  $.ajax({
    url: "http://localhost:8080/payment-ways",
    type: "get",

    success: function(response) {
      tableFill(response);
    }
  });
}

function tableFill(paymentWays) {
  $("#my-table tbody tr").remove();

  $.each(paymentWays, function(i, paymentWay) {
    let line = $("<tr>");

    line.append(
      $("<td>").text(paymentWay.id),
      $("<td>").text(paymentWay.description)
    );

    line.appendTo("#my-table");
  });
}

function registerPaymentWay() {
  let paymentWayJson = JSON.stringify({
    "description": $("#description-field").val()
  });

  console.log(paymentWayJson);

  $.ajax({
    url: "http://localhost:8080/payment-ways",
    type: "post",
    data: paymentWayJson,
    contentType: "application/json",

    success: function(response) {
      alert("Payment method added!");
      findPaymentWay();
    },

    error: function(error) {
      if (error.status == 400) {
        let problem = JSON.parse(error.responseText);
        alert(problem.user_message);
      } else {
        alert("Error registering payment method!");
      }
    }
  });
}

$("#btn-find").click(findPaymentWay);
$("#btn-send").click(registerPaymentWay);