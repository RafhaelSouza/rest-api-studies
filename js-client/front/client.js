function restaurantsList() {
  $.ajax({
    url: "http://localhost:8080/restaurants",
    type: "get",

    success: function(response) {
      $("#my-content").text(JSON.stringify(response));
    }
  });
}

$("#my-button").click(restaurantsList);