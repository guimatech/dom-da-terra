var el = document.getElementById('element');

if (document.fullscreenEnabled || document.webkitFullscreenEnabled || document.msFullscreenEnabled) {
    if (el.requestFullscreen) {
        el.requestFullscreen();
    } else if (el.webkitRequestFullscreen) {
        el.webkitRequestFullscreen();
    } else if (el.msRequestFullscreen) {
        el.msRequestFullscreen();
    }
} else {
    alert('Modo de tela cheia não suportado pelo navegador');
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

$(document).ready(function() {
    $('.dropdown-toggle').click(function() {
        $(this).next('.dropdown-menu').slideToggle();
    });

    $(document).click(function(event) {
        var target = event.target;
        if (!$(target).is('.dropdown-toggle') && !$(target).parents().is('.dropdown')) {
            $('.dropdown-menu').slideUp();
        }
    });
});