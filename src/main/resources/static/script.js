$(function() {


  // initiate the debit command
  $("#debit-btn").on("click", function() {
    var accountToDebit = $("#debit-account-no").val();
    if (accountToDebit === "null" || accountToDebit === undefined) {
      alert("Choose Account to debit");
      return;
    }
    var amount = $("#debit-amount").val();
    if (!amount || amount === 0) {
      alert("Specify amount to debit");
      return;
    }

    $.ajax({
      url: "/debit",
      method: "GET",
      data: {"acc": accountToDebit,"amount":amount},
      success: function(a) {
        $("#debit-amount").val("");
      },
      error: function(a) {
        console.log(a);
      }
    })
  });


  // initiate the credit command
  $("#credit-btn").on("click", function() {
    var accountToCredit = $("#credit-account-no").val();
    if (accountToCredit === "null" || accountToCredit === undefined) {
      alert("Choose Account to credit");
      return;
    }
    var amount = $("#credit-amount").val();
    if (!amount || amount === 0) {
      alert("Specify amount to credit");
      return;
    }

    $.ajax({
      url: "/credit",
      method: "GET",
      data: {"acc": accountToCredit,"amount":amount},
      success: function(a) {
        $("#credit-amount").val("");
      },
      error: function(a) {
        console.log(a);
      }
    })
  });



});