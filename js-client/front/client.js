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
    var line = $("<tr>");

    line.append(
      $("<td>").text(paymentWay.id),
      $("<td>").text(paymentWay.description)
    );

    line.appendTo("#my-table");
  });
}

$("#btn-find").click(findPaymentWay);