const config = {
  clientId: "client-oauth2-authorization-code",
  authorizeUrl: "http://localhost:8080/oauth/authorize",
  tokenUrl: "http://localhost:8080/oauth/token",
  callbackUrl: "http://localhost:8082",
  kitchensUrl: "http://localhost:8080/v1/kitchens"
};

let accessToken = "";

function generateCodeVerifier() {
  let codeVerifier = generateRandomString(128);
  localStorage.setItem("codeVerifier", codeVerifier);

  return codeVerifier;
}

function generateRandomString(length) {
  let text = "";
  let possible = "XqD7Ljb22TCmNDV4fX_cZP6NhQaW-2JqtXnsGGsXwWY";
  
  for (let i = 0; i < length; i++) {
    text += possible.charAt(Math.floor(Math.random() * possible.length));
  }
  
  return text;
}

function generateCodeChallenge(codeVerifier) {
  return base64URL(CryptoJS.SHA256(codeVerifier));
}

function getCodeVerifier() {
  return localStorage.getItem("codeVerifier");
}

function base64URL(string) {
  return string.toString(CryptoJS.enc.Base64).replace(/=/g, '').replace(/\+/g, '-').replace(/\//g, '_');
}

function find() {
  alert("Findind resource with access token " + accessToken);

  $.ajax({
    url: config.kitchensUrl,
    type: "get",

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Bearer " + accessToken);
    },

    success: function(response) {
      var json = JSON.stringify(response);
      $("#result").text(json);
    },

    error: function(error) {
      alert("Error when finding resource");
    }
  });
}

function accessTokenGenerate(code) {
  alert("Generating access token with code: " + code);
 
  let codeVerifier = getCodeVerifier();

  let params = new URLSearchParams();
  params.append("grant_type", "authorization_code");
  params.append("code", code);
  params.append("redirect_uri", config.callbackUrl);
  params.append("client_id", config.clientId);
  params.append("code_verifier", codeVerifier);

  $.ajax({
    url: config.tokenUrl,
    type: "post",
    data: params.toString(),
    contentType: "application/x-www-form-urlencoded",

    success: function(response) {
      accessToken = response.access_token;

      alert("Access token generated: " + accessToken);
    },

    error: function(error) {
      alert("Error generating access token");
    }
  });
}

function login() {
  let codeVerifier = generateCodeVerifier();
  let codeChallenge = generateCodeChallenge(codeVerifier);

  window.location.href = `${config.authorizeUrl}?response_type=code&client_id=${config.clientId}&redirect_uri=${config.callbackUrl}&code_challenge_method=s256&code_challenge=${codeChallenge}`;
}

$(document).ready(function() {
  let params = new URLSearchParams(window.location.search);

  let code = params.get("code");

  if (code) {
    accessTokenGenerate(code);
  }
});

$("#btn-find").click(find);
$("#btn-login").click(login);
