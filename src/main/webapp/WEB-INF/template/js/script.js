// preloader

var hellopreloader = document.getElementById("hellopreloader_preload");

function fadeOutnojquery(el) {
    el.style.opacity = 1;
    var interhellopreloader = setInterval(function () {
        el.style.opacity = el.style.opacity - 0.05;
        if (el.style.opacity <= 0.05) {
            clearInterval(interhellopreloader);
            hellopreloader.style.display = "none";
        }
    }, 16);
}

window.onload = function () {
    setTimeout(function () {
        fadeOutnojquery(hellopreloader);
    }, 0);
};

//datatables
$(document).ready(function() {
    $('#statisticDataTable').DataTable();
} );

//choose test
$().ready(
    function () {
        $("#themes").change(function (event) {
            $.ajax({
                url: "/choose/userChoose",
                type: "GET",
                dataType: "json",
                data: {topic: $(event.target).val()},
            })
                .done(function(data) {
                    setTests(data)
                })
                .fail(function(xhr, status, error) {
                    alert(xhr.responseText + '|\n' + status + '|\n'
                        +error);
                });
        });
    });
var setTests = function (data) {
    $('#tests').find('option').remove();
    $.each(data, function (index, value) {
        $('#tests').append(new Option(value, value));
    });
};