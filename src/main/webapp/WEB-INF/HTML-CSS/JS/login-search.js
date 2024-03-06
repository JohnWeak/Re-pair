$(document).ready(function () {

    $("#login").click(function () {
        $("#container").toggleClass("log-in");
    });

    $("#loginButton").click(function () {
        $("#container").toggleClass("active");
    });

    $("#cerca").click(function () {
        $("#container").toggleClass("log-in");
    });

    $("#searchButton").click(function () {
        $("#container").toggleClass("active");
    });

});