<html>
<head>
  <script type="application/javascript" src="/webjars/jquery/2.1.4/jquery.min.js">
  </script>
  <link rel="stylesheet" href="/webjars/bootstrap-css-only/3.3.4/css/bootstrap.min.css">
  <title>Exploring Axon</title>
</head>

<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Explore Axon Framework</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container" style="margin-top: 70px">
  <div class="row">
    <div class="col-md-6">
      <div>
        <select id="debit-account-no">
          <option value="null">Choose Account</option>
          <option value="acc-one">Account One</option>
          <option value="acc-two">Account Two</option>
        </select>
        <input id="debit-amount" type="text" /> <button id="debit-btn">Debit Account</button><br/>
        <select id="credit-account-no">
          <option value="null">Choose Account</option>
          <option value="acc-one">Account One</option>
          <option  value="acc-two">Account Two</option>
        </select>
        <input id="credit-amount" type="text" /> <button id="credit-btn">Credit Account</button>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">

    </div>
  </div>

</div>

</body>
<script type="text/javascript" src="/script.js"></script>
</html>