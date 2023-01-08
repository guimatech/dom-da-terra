var imgToggle = true
document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId, logoId) =>{
        const toggle = document.getElementById(toggleId),
        nav = document.getElementById(navId),
        bodypd = document.getElementById(bodyId),
        headerpd = document.getElementById(headerId)
        logo = document.getElementById(logoId)
        var base_url = window.location.origin;

        // Validate that all variables exist
        if(toggle && nav && bodypd && headerpd && logo){
            toggle.addEventListener('click', ()=>{
                // show navbar
                nav.classList.toggle('show-menu')
                // change icon
                toggle.classList.toggle('bx-x')
                // add padding to body
                bodypd.classList.toggle('body-pd')
                // add padding to header
                headerpd.classList.toggle('body-pd')
                // add big logo
                logo.classList.toggle('logo')

                imgToggle = !imgToggle
                if (imgToggle) {
                    logo.src = base_url + '/images/logo-min.png'
                } else {
                    logo.src = base_url + '/images/logo.png'
                }
            })
        }
    }

    showNavbar('header-toggle','nav-bar','body-pd','header','logo')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink(){
        if(linkColor){
            linkColor.forEach(l=> l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))
});

$(function() {
    var url = window.location.href;

    $(".nav_list a").each(function() {
        if (url == (this.href)) {
            $(this).closest("a").addClass("active");
//            $(this).closest("a").parent().parent().addClass("active");
        }
    });
});